from django.shortcuts import render
from rest_framework import viewsets, status

import json
from django.core.management import call_command

from .serializers import *
from .models import *
from lxml import etree as ET
import base64

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth import authenticate
from django.contrib.auth.models import User
import zeep
from datetime import datetime
from datetime import timedelta
from django.utils.dateparse import parse_date

from django_filters import rest_framework as filters, DateFromToRangeFilter


def read_and_save_zeep_result(result, model): 
    input_dict = zeep.helpers.serialize_object(result)
    output_dict = json.loads(json.dumps(input_dict))
    for obj_dict in output_dict:
        id = obj_dict.pop('id', None)
        obj, created = model.objects.update_or_create(id=id, defaults = {**obj_dict})


class Login(APIView):
    def post(self, request, format=None):
        data = request.data
        username = data.get('username', None)
        password = data.get('password', None)
        print(username)
        print(password)
        
        lu = Update.objects.last()
        if (lu == None):
            lu = Update.objects.create(last_updated = '2000-01-01 00:00:00')

        # deo sa autentifikacijom sa glavnim backom
        client = zeep.Client('http://localhost:8762/auth-service/auth/soap/authentication.wsdl')
        # client.service._binding_options['address'] = 'http://localhost:8762/auth-service/auth/soap'

        print()

        try:
            result_token = client.service.authentication(username, password)
            user = authenticate(username=username, password=password)
            last_update_datetime = datetime.now()


            if user is None:
                user = User.objects.create(username=username)
                user.set_password(password)
                user.save()


            print("Getting additional services from main backend")
            client = zeep.Client('http://localhost:8762/admin-service/ws/additionalService/AdditionalServices.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + str(result_token)})
            client.service._binding_options['address'] = 'http://localhost:8762/admin-service/ws/additionalService"' # ne znam da li je ova Milice proveri nisam testirao sa ovom

            result = client.service.getAdditionalServices('')
            print(result)
            print("Saving feched additional services data")
            read_and_save_zeep_result(result, AdditionalService)
            print("Additional services synchronised")

            print("Getting room categories from main backend")
            client = zeep.Client('http://localhost:8762/admin-service/ws/roomCategory/roomCategory.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + str(result_token)})
            client.service._binding_options['address'] = 'http://localhost:8762/admin-service/ws/roomCategory"' # ne znam da li je ova Milice proveri nisam testirao sa ovom

            result = client.service.getRoomCategories('')
            print(result)
            print("Saving feched room categories data")
            read_and_save_zeep_result(result, RoomCategory)
            print("Room categories synchronised")


            print("Getting room types from main backend")
            client = zeep.Client('http://localhost:8762/admin-service/ws/roomType/roomType.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + str(result_token)})
            client.service._binding_options['address'] = 'http://localhost:8762/admin-service/ws/roomType' # ne znam da li je ova Milice proveri nisam testirao sa ovom

            result = client.service.getRoomTypes('')
            print(result)
            print("Saving feched room types data")
            read_and_save_zeep_result(result, RoomType)
            print("Room types synchronised")


            print("Getting hotel from main backend")
            client = zeep.Client('http://localhost:8762/room-service/soap/hotel/hotel.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + str(result_token)})
            client.service._binding_options['address'] = 'http://localhost:8762/room-service/soap/hotel' # ne znam da li je ova Milice proveri nisam testirao sa ovom

            result = client.service.getHotel('')
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
            print(result)
            print("Saving feched hotel data")
            address_dict = output_dict.pop('address', None)
            address, created = Address.objects.update_or_create(id=address_dict.pop('id', None), defaults = {**address_dict})
            hotel , created = Hotel.objects.update_or_create(id=output_dict.pop('id', None), defaults = {**output_dict})
            hotel.address = address
            hotel.save()
            print("Hotel synchronised")


            print("Getting reservation from main backend")
            client = zeep.Client('http://localhost:8762/reservations-service/ws/getReservations.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + str(result_token)})
            client.service._binding_options['address'] = 'http://localhost:8762/reservations-service/ws/getReservations'

            result = client.service.getReservations(lu.last_updated)
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
            print(result)
            print("Saving feched reservation data")
            for obj_dict in output_dict:
                customer_dict = obj_dict.pop('user', None)
                obj_dict['roomId_id'] = obj_dict.pop('roomId')
                customer, created = Customer.objects.update_or_create(id=customer_dict.pop('id', None), defaults = {**customer_dict})
                reservation , created = Resrvation.objects.update_or_create(id=obj_dict.pop('id', None), defaults = {**obj_dict})
                reservation.customer = customer
                reservation.save()
            print("Reservations synchronised")


            print("Getting messages from main backend")
            client = zeep.Client('http://localhost:8762/reservations-service/ws/getMessages.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + str(result_token)})
            client.service._binding_options['address'] = 'http://localhost:8762/reservations-service/ws/getMessages'

            result = client.service.getMessages(lu.last_updated)
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
            print(result)
            print("Saving feched message data")
            for obj_dict in output_dict:
                obj_dict['reservationId_id'] = obj_dict.pop('reservationId') 
                obj, created = Message.objects.update_or_create(id=obj_dict.pop('id'), defaults = {**obj_dict})
            print("Messages synchronised")


            # print("Getting recensions from main backend")
            # client = zeep.Client('http://localhost:8762/reservations-service/ws/getRecensions.wsdl')
            # client.transport.session.headers.update({'Authorization': 'Bearer ' + str(result_token)})
            # client.service._binding_options['address'] = 'http://localhost:8762/reservations-service/ws/getRecensions'

            # result = client.service.getRecensions(lu.last_updated)
            # input_dict = zeep.helpers.serialize_object(result)
            # output_dict = json.loads(json.dumps(input_dict))
            # print(result)
            # print("Saving feched recension data")
            # for obj_dict in output_dict:
            #     obj_dict['reservationId_id'] = obj_dict.pop('reservationId') 
            #     obj, created = Recension.objects.update_or_create(id=obj_dict.pop('id'), defaults = {**obj_dict})
            # print("Recensions synchronised")
            
            
            lu = Update.objects.first()
            if lu == None:
                lu = Update.objects.create(last_updated = '2000-01-01 00:00:00')
            token = Token.objects.first()
            if token == None:
                token = Token.objects.create(last_token = str(result_token))
            token.last_token = str(result_token)
            token.save()


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
    filterset_fields = ('active',)


class RoomTypeView(viewsets.ReadOnlyModelViewSet):
    queryset = RoomType.objects.all()
    serializer_class = RoomTypeSerializer
    filterset_fields = ('active',)


class AdditionalServiceView(viewsets.ReadOnlyModelViewSet):
    queryset = AdditionalService.objects.all()
    serializer_class = AdditionalServiceSerializer
    filterset_fields = ('active',)


class RecensionView(viewsets.ReadOnlyModelViewSet):
    queryset = Recension.objects.all()
    serializer_class = RecensionSerializer
    filterset_fields = ('reservationId',)


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
        print(d['additionalService'])
        for item in d['additionalService']:
	        line = {'id': item}
        	lineitem.append(line)
        
        room_address = factory.Address(country=d['address']['country'], state=d['address']['state'], city=d['address']['city'], postalCode=d['address']['postalCode'], street=d['address']
        ['street'], streetNumber=d['address']['streetNumber'], lng=d['address']['lng'], lat=d['address']['lat'])
        
        # pazi ovde uzimas prvi hotel po pretpostavkom u dogovoru sa timom da ce biti samo 1/ svakako si spreman za promenu jer se salje i hotel id request.data
        hotel_id = Hotel.objects.all()[:1][0].id 
        room = factory.Room(hotelId=hotel_id, address=room_address, additionalServices=lineitem, category=room_category, type=room_type, roomNumber=d['roomNumber'], defaultPrice=d['defaultPrice'], numberOfPeople=d['numberOfPeople'], cancelationDays=d['cancelationDays'], description=d['description'])
       
        node = client.create_message(client.service, 'newRoom', id=hotel_id, room=room)
        tree = ET.ElementTree(node)
        tree.write('test.xml',pretty_print=True)

        result = client.service.newRoom(id=hotel_id, room=room)
        input_dict = zeep.helpers.serialize_object(result)
        output_dict = json.loads(json.dumps(input_dict))

        data = request.data
        data['hotel'] = hotel_id
        serializer = RoomSerializerInput(data=data, context={'request': request, 'address_id': output_dict.pop('addressId', None), 'id':output_dict.pop('roomId', None)})
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

            client = zeep.Client('http://localhost:8762/room-service/soap/price/price.wsdl')
            result = client.service.setPrice(id=validated_data['room'].id, dateTo=date_to, dateFrom=date_from, price=validated_data['amount'])

            # rez vraca success (True) ako prodje
            if result:
                ret = []
                for i in range(delta.days + 1):
                    date = date_from + timedelta(days=i)
                    obj, created = Price.objects.update_or_create(date=date, defaults = {**validated_data})
                    ret.append(obj)

                serializer = PriceSerializer(ret, many=True, context={'request': request})
    
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            else: 
                return Response(zeep.exceptions.Fault.message, status=status.HTTP_400_BAD_REQUEST)

        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class RoomReservationFilter(filters.FilterSet):
    date = DateFromToRangeFilter()
    class Meta:
        model = Resrvation
        fields = ['dateFrom', 'roomId', 'status']


class ResrvationView(viewsets.ModelViewSet):
    queryset = Resrvation.objects.all()
    filterset_class = RoomReservationFilter
    
    def get_serializer_class(self):
        if self.action == 'update':
            return ResrvationSerializerPut
        return ResrvationSerializer
    
    def create(self, request, *args, **kwargs):
        serializer = ResrvationSerializer(data=request.data)
        if serializer.is_valid():
            validated_data = serializer.validated_data
            token = Token.objects.all()[:1][0].last_token

            print("Connecting to main backend server")
            client = zeep.Client('http://localhost:8762/reservations-service/ws/newReservation.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + token})
            client.service._binding_options['address'] = 'http://localhost:8762/reservations-service/ws/newReservation'
            result = client.service.newReservation(id=validated_data['roomId'].id, dateTo=validated_data['dateTo'], dateFrom=validated_data['dateFrom'])
            print(result)
            print("Approved form main backend server")
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
            res = Reservation.objects.create(**output_dict)
            print("Creted")

            serializer = RoomSerializer(ret)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def update(self, request, pk, *args, **kwargs):
        token = Token.objects.all()[:1][0].last_token
        print("Connecting to main backend server")
        client = zeep.Client('http://localhost:8762/reservations-service/ws/reservation.wsdl')
        client.transport.session.headers.update({'Authorization': 'Bearer ' + token})
        client.service._binding_options['address'] = 'http://localhost:8762/reservations-service/ws/newReservation'
        result = client.service.confirmReservation(pk)
        print(result)
        print("Approved form main backend server")
        if(result):
            serializer = ResrvationSerializerPut(instance=Resrvation.objects.get(pk=pk), data={'status': 'HAPPENED'})
            if serializer.is_valid():
                serializer.save()
                print("Updated")
                return Response(serializer.data, status=status.HTTP_200_OK)
            else:
                return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class ImageView(viewsets.ModelViewSet):
    queryset = RoomFotos.objects.all()
    serializer_class = ImageSerializer
    filterset_fields = ('room',)

    def create(self, request, *args, **kwargs):
        serializer = ImageSerializer(data=request.data)
        if serializer.is_valid():
            validated_data = serializer.validated_data
            base_64_img = base64.b64encode(validated_data['photo'].read())
            token = Token.objects.all()[:1][0].last_token

            print("Connecting to main backend server")
            client = zeep.Client('http://localhost:8762/room-service/soap/addImage/addImage.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + token})
            client.service._binding_options['address'] = 'http://localhost:8762/room-service/soap/addImage'
            try:
                result = client.service.addImage(id=validated_data['room'].id, image=base_64_img, mainImage=validated_data['is_cover'])
                print(result)
                if(not result):
                     return Response({"failed to upload file to main backend"}, status=status.HTTP_400_BAD_REQUEST)

                print("Approved form main backend server")
                serializer.save()
                print("Creted")

                return Response(serializer.data, status=status.HTTP_201_CREATED)
            except zeep.exceptions.Fault as fault:
                return Response(fault.message, status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class MessageView(viewsets.ModelViewSet):
    http_method_names = ['get', 'post', 'head', 'options']
    queryset = Message.objects.all()
    serializer_class = MessageSerializer
    filterset_fields = ('reservationId',)

    def create(self, request, *args, **kwargs):
        serializer = MessageSerializer(data=request.data)
        if serializer.is_valid():
            validated_data = serializer.validated_data
            token = Token.objects.all()[:1][0].last_token

            print("Connecting to main backend server")
            client = zeep.Client('http://localhost:8762/reservations-service/ws/newMessage.wsdl')
            client.transport.session.headers.update({'Authorization': 'Bearer ' + token})
            client.service._binding_options['address'] = 'http://localhost:8762/reservations-service/ws/newMessage'
            try:
                result = client.service.newMessage(id=validated_data['reservationId'].id, message=validated_data['message'])
                print(result)
                print("Approved form main backend server")
                input_dict = zeep.helpers.serialize_object(result)
                output_dict = json.loads(json.dumps(input_dict))
                res = Reservation.objects.create(**output_dict)
                print("Creted")

                serializer = RoomSerializer(ret)
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            except zeep.exceptions.Fault as fault:
                return Response(fault.message, status=status.HTTP_404_NOT_FOUND)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)





