from django.shortcuts import render
from rest_framework import viewsets, status

import json
from django.core.management import call_command

from .serializers import *
from .models import *
from lxml import etree as ET

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth import authenticate
from django.contrib.auth.models import User
import zeep

from django_filters import rest_framework as filters, DateFromToRangeFilter


def read_and_save_zeep_result(result, model): 
    input_dict = zeep.helpers.serialize_object(result)
    output_dict = json.loads(json.dumps(input_dict))
    for obj_dict in output_dict:
        id = obj_dict.pop('id', None)
        obj, created = model.objects.update_or_create(id=id, defaults = {**obj_dict})

    # print("Mesec")
    # print(output_dict)
    # result = serializer(data=output_dict, many=isinstance(result, list), partial=True)
    # print(result.initial_data)
    
    # if result.is_valid(raise_exception=True):
    #     result.save()


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

            # updejtuje podatke sa glavnim backom
            client = zeep.Client('http://localhost:8762/admin-service/ws/additionalService/AdditionalServices.wsdl')
            result = client.service.getAdditionalServices('')
            read_and_save_zeep_result(result, AdditionalService)

            # client = zeep.Client('http://localhost:8762/admin-service/ws/roomCategory/roomCategory.wsdl')
            # result = client.service.getRoomCategories('')
            # read_and_save_zeep_result(result, RoomCategory)

            # client = zeep.Client('http://localhost:8762/admin-service/ws/roomType/roomType.wsdl')
            # result = client.service.getRoomTypes('')
            # read_and_save_zeep_result(result, RoomType)

            client = zeep.Client('http://localhost:8762/room-service/soap/hotel/hotel.wsdl')
            result = client.service.getHotel('')
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
            address_dict = output_dict.pop('address', None)
            address, created = Address.objects.update_or_create(id=address_dict.pop('id', None), defaults = {**address_dict})
            hotel , created = Hotel.objects.update_or_create(id=output_dict.pop('id', None), defaults = {**output_dict})
            hotel.address = address
            hotel.save()




            
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


class RoomView(viewsets.ModelViewSet):
    queryset = Room.objects.all()
    http_method_names = ['get', 'post', 'delete', 'head', 'options']

    def get_serializer_class(self):
        if self.action == 'create':
            return RoomSerializerInput
        return RoomSerializer
    
    def create(self, request, *args, **kwargs):

        client = zeep.Client('http://localhost:8762/room-service/soap/room/newRoom.wsdl')
        factory = client.type_factory('ns0')

        d = request.data
        room_category = factory.RoomCategory(id=d['roomCategory'])
        room_type = factory.RoomType(id=d['roomType'])
        lineitem = []
        for item in d.getlist('additionalService'):
	        line = {'id': item}
        	lineitem.append(line)
        room_address = factory.Address(country=d['address.country'], state=d['address.state'], city=d['address.city'], postalCode=d['address.postalCode'], street=d['address.street'], streetNumber=d['address.streetNumber'], lng=d['address.lng'], lat=d['address.lat'])
        
        room = factory.Room(hotelId=d['hotel'], address=room_address, additionalServices=lineitem, category=room_category, type=room_type, roomNumber=d['roomNumber'], defaultPrice=d['defaultPrice'], numberOfPeople=d['numberOfPeople'], cancelationDays=d['cancelationDays'], description=d['description'], totalRating=d['totalRating'], numberOfRatings=d['numberOfRaitings'])
        hotel_id = Hotel.objects.all()[:1][0].id

        node = client.create_message(client.service, 'newRoom', id=hotel_id, room=room)
        tree = ET.ElementTree(node)
        tree.write('test.xml',pretty_print=True)

        result = client.service.newRoom(id=hotel_id, room=room)
        input_dict = zeep.helpers.serialize_object(result)
        output_dict = json.loads(json.dumps(input_dict))

        serializer = RoomSerializerInput(data=request.data, context={'request': request, 'address_id': output_dict.pop('addressId', None), 'id':output_dict.pop('roomId', None)})
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class RoomBetweenDatesFilter(filters.FilterSet):
    date = DateFromToRangeFilter()
    class Meta:
        model = Price
        fields = ['date','room']


class PriceView(viewsets.ModelViewSet):
    queryset = Price.objects.all()
    filterset_class = RoomBetweenDatesFilter
    http_method_names = ['get', 'post', 'delete', 'head', 'options']

    def get_serializer_class(self):
        if self.action == 'create':
            return PriceSerializerInput
        return PriceSerializer

    def create(self, request, *args, **kwargs):
        serializer = PriceSerializerInput(data=request.data)
        if serializer.is_valid():
            validated_data = serializer.validated_data
            date_from = validated_data.pop('dateFrom')
            date_to = validated_data.pop('dateTo')
            delta = date_to - date_from

            ret = []
            for i in range(delta.days + 1):
                date = date_from + timedelta(days=i)
                obj, created = Price.objects.update_or_create(date=date, defaults = {**validated_data})
                ret.append(obj)

            serializer = PriceSerializer(ret, many=True, context={'request': request})
 
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ResrvationView(viewsets.ModelViewSet):
    queryset = Resrvation.objects.all()
    
    def get_serializer_class(self):
        if self.action == 'update':
            return ResrvationSerializerPut
        return ResrvationSerializer






