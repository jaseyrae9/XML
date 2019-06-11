from django.shortcuts import render
from rest_framework import viewsets, status

import json
from django.core.management import call_command

from .serializers import *
from .models import *

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth import authenticate
from django.contrib.auth.models import User
import zeep


def read_and_save_zeep_result(result, ser): 
    input_dict = zeep.helpers.serialize_object(result)
    output_dict = json.loads(json.dumps(input_dict))
    result = ser(data=output_dict, many=isinstance(result, list))
    if result.is_valid(raise_exception=True):
        result.save()


class Login(APIView):
    def post(self, request, format=None):
        data = request.data
        username = data.get('username', None)
        password = data.get('password', None)

        # deo sa autentifikacijom sa glavnim backom
        client = zeep.Client('http://localhost:8762/auth-service/auth/soap/authentication.wsdl')

        try:
            result_token = client.service.authentication(username, password)
            user = authenticate(username=username, password=password)

            # TESTIRATI DA LI RADI
            if user is None:
                user = User(username=username,
                            password=password,
                            is_staff=True,
                            is_superuser=True,
                            is_active=True)
                user.save()
            # OVAJ DEO

            # brise podtke iz baze u kupi ih sa glavnog back-a
            AdditionalService.objects.all().delete()
            client = zeep.Client('http://localhost:8762/admin-service/ws/additionalService/AdditionalServices.wsdl')
            result = client.service.getAdditionalServices('')
            read_and_save_zeep_result(result, AdditionalServiceSerializer)

            RoomCategory.objects.all().delete()
            client = zeep.Client('http://localhost:8762/admin-service/ws/roomCategory/roomCategory.wsdl')
            result = client.service.getRoomCategories('')
            read_and_save_zeep_result(result, RoomCategorySerializer)

            RoomType.objects.all().delete()
            client = zeep.Client('http://localhost:8762/admin-service/ws/roomType/roomType.wsdl')
            result = client.service.getRoomTypes('')
            read_and_save_zeep_result(result, RoomTypeSerializer)

            
            # pravi nove Django jwt tokene, salje sa njima i token sa glavnog back-a
            token = RefreshToken.for_user(user)
            content = {
                'active_token': str(token.access_token),
                'refresh_token': str(token),
                'main_backend_token': str(result_token),
            }

            return Response(content, status=status.HTTP_200_OK)

        except zeep.exceptions.Fault as fault:
            return Response(fault.message, status=status.HTTP_404_NOT_FOUND)


class RoomCategoryView(viewsets.ReadOnlyModelViewSet):
    queryset = RoomCategory.objects.all()
    serializer_class = RoomCategorySerializer


class RoomTypeView(viewsets.ReadOnlyModelViewSet):
    queryset = RoomType.objects.all()
    serializer_class = RoomTypeSerializer


class AdditionalServiceView(viewsets.ReadOnlyModelViewSet):
    queryset = AdditionalService.objects.all()
    serializer_class = AdditionalServiceSerializer


class HotelView(viewsets.ReadOnlyModelViewSet):
    queryset = Hotel.objects.all()
    serializer_class = HotelSerializer


# class AddressView(viewsets.ModelViewSet):
#     queryset = Address.objects.all()
#     serializer_class = AddressSerializer


class RoomView(viewsets.ModelViewSet):
    queryset = Room.objects.all()
    serializer_class = RoomSerializer





class PriceView(viewsets.ModelViewSet):
    queryset = Price.objects.all()
    serializer_class = PriceSerializer


class ResrvationView(viewsets.ModelViewSet):
    queryset = Resrvation.objects.all()
    serializer_class = ResrvationSerializer






