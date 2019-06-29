from django.shortcuts import render
from rest_framework import viewsets, status

import json
from django.core.management import call_command

from .serializers import *
from .models import *
from lxml import etree as ET
import base64

from rest_framework.permissions import IsAuthenticated
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework_simplejwt.tokens import RefreshToken
from django.contrib.auth import authenticate
from django.contrib.auth.models import User
import zeep


from datetime import timedelta
from datetime import datetime
from django.utils.dateparse import parse_date

from django_filters import rest_framework as filters, DateFromToRangeFilter


def return_zeep_client(wsdl_url, token, address):
    prefix = 'http://localhost:8762'
    client = zeep.Client(prefix + wsdl_url)
    client.transport.session.headers.update({'Authorization': 'Bearer ' + token})
    client.service._binding_options['address'] = prefix + address
    return client


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
        
        lu = Update.objects.last()
        if (lu == None):
            lu = Update.objects.create(last_updated = '2000-01-01 00:00:00')

        # deo sa autentifikacijom sa glavnim backom
        client = zeep.Client('http://localhost:8762/auth-service/auth/soap/authentication.wsdl')
        # client.service._binding_options['address'] = 'http://localhost:8762/auth-service/auth/soap'

        try:
            result_token = client.service.authentication(username, password)
            user = authenticate(username=username, password=password)
            last_update_datetime = datetime.now()

            if user is None:
                user = User.objects.create(username=username)
                user.set_password(password)
                user.save()


            print("Getting additional services from main backend")
            client = return_zeep_client('/admin-service/ws/additionalService/AdditionalServices.wsdl', str(result_token), '/admin-service/ws/additionalService')
            result = client.service.getAdditionalServices('')
            print("Saving feched additional services data")
            read_and_save_zeep_result(result, AdditionalService)
            print("Additional services synchronised")


            print("Getting room categories from main backend")
            client = return_zeep_client('/admin-service/ws/roomCategory/roomCategory.wsdl', str(result_token), '/admin-service/ws/roomCategory')
            result = client.service.getRoomCategories('')
            print("Saving feched room categories data")
            read_and_save_zeep_result(result, RoomCategory)
            print("Room categories synchronised")


            print("Getting room types from main backend")
            client = return_zeep_client('/admin-service/ws/roomType/roomType.wsdl', str(result_token), '/admin-service/ws/roomType')
            result = client.service.getRoomTypes('')
            print("Saving feched room types data")
            read_and_save_zeep_result(result, RoomType)
            print("Room types synchronised")


            print("Getting hotel from main backend")
            client = return_zeep_client('/room-service/soap/hotel/hotel.wsdl', str(result_token), '/room-service/soap/hotel')
            result = client.service.getHotel('')
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))

            print("Saving feched hotel data")
            address_dict = output_dict.pop('address', None)
            address, created = Address.objects.update_or_create(id=address_dict.pop('id', None), defaults = {**address_dict})
            hotel , created = Hotel.objects.update_or_create(id=output_dict.pop('id', None), defaults = {**output_dict})
            hotel.address = address
            hotel.save()
            print("Hotel synchronised")


            print("Getting reservation from main backend")
            client = return_zeep_client('/reservations-service/ws/getReservations.wsdl', str(result_token), '/reservations-service/ws/getReservations')
            result = client.service.getReservations(lu.last_updated)
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))

            print("Saving feched reservation data")
            for obj_dict in output_dict:
                customer_dict = obj_dict.pop('user', None)
                obj_dict['roomId_id'] = obj_dict.pop('roomId')
                reservation , created = Resrvation.objects.update_or_create(id=obj_dict.pop('id', None), defaults = {**obj_dict})
                if (customer_dict is not None):
                    customer, created = Customer.objects.update_or_create(id=customer_dict.pop('id', None), defaults = {**customer_dict})
                    reservation.customer = customer
                    reservation.save()
            print("Reservations synchronised")


            print("Getting messages from main backend")
            client = return_zeep_client('/reservations-service/ws/getMessages.wsdl', str(result_token), '/reservations-service/ws/getMessages')
            result = client.service.getMessages(lu.last_updated)
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))

            print("Saving feched message data")
            for obj_dict in output_dict:
                obj_dict['reservationId_id'] = obj_dict.pop('reservationId') 
                obj, created = Message.objects.update_or_create(id=obj_dict.pop('id'), defaults = {**obj_dict})
            print("Messages synchronised")


            print("Getting recensions from main backend")
            client = return_zeep_client('/reservations-service/ws/getRecensions.wsdl', str(result_token), '/reservations-service/ws/getRecensions')
            result = client.service.getRecensions(lu.last_updated)
            

            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
            
            print("Saving feched recension data")
            for obj_dict in output_dict:
                obj_dict['reservationId_id'] = obj_dict.pop('reservationId')
                obj_dict['roomId_id'] = obj_dict.pop('roomId')
                obj, created = Recension.objects.update_or_create(id=obj_dict.pop('id'), defaults = {**obj_dict})
            print("Recensions synchronised")
            
            
            lu = Update.objects.create(last_updated = '2000-01-01 00:00:00')
            token = Token.objects.first()
            if token == None:
                token = Token.objects.create(last_token = str(result_token))
            else:
                token.last_token = str(result_token)
                token.save()


            # pravi nove Django jwt tokene, salje sa njima i token sa glavnog back-a
            token = RefreshToken.for_user(user)
            content = {'active_token': str(token.access_token)}

            return Response(content, status=status.HTTP_200_OK)
        except zeep.exceptions.Fault as fault:           
            return Response(fault.message, status=status.HTTP_400_BAD_REQUEST)


class RoomCategoryView(viewsets.ReadOnlyModelViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = RoomCategory.objects.all()
    serializer_class = RoomCategorySerializer
    filterset_fields = ('active',)


class RoomTypeView(viewsets.ReadOnlyModelViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = RoomType.objects.all()
    serializer_class = RoomTypeSerializer
    filterset_fields = ('active',)


class AdditionalServiceView(viewsets.ReadOnlyModelViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = AdditionalService.objects.all()
    serializer_class = AdditionalServiceSerializer
    filterset_fields = ('active',)


class RecensionView(viewsets.ReadOnlyModelViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = Recension.objects.all()
    serializer_class = RecensionSerializer
    filterset_fields = ('reservationId', 'roomId')


class HotelView(viewsets.ReadOnlyModelViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = Hotel.objects.all()
    serializer_class = HotelSerializer


class RoomView(viewsets.ModelViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = Room.objects.all()
    http_method_names = ['get', 'post', 'delete', 'head', 'options']

    def get_serializer_class(self):
        if self.action == 'create':
            return RoomSerializerInput
        return RoomSerializer
    
    def create(self, request, *args, **kwargs):

        client = return_zeep_client('/room-service/soap/room/newRoom.wsdl', Token.objects.first().last_token, '/room-service/soap/newRoom')
        factory = client.type_factory('ns0')

        d = request.data
        room_category = factory.RoomCategory(id=d['roomCategory'])
        room_type = factory.RoomType(id=d['roomType'])
        lineitem = []
        for item in d['additionalService']:
	        line = {'id': item}
        	lineitem.append(line)
        
        add = d['address']
        room_address = factory.Address(country=add['country'], state=add['state'], city=add['city'], postalCode=add['postalCode'], street=add['street'], streetNumber=add['streetNumber'], lng=add['lng'], lat=add['lat'])
        room = factory.Room(address=room_address, additionalServices=lineitem, category=room_category, type=room_type, floor=d['roomFloor'], roomNumber=d['roomNumber'], defaultPrice=d['defaultPrice'], numberOfPeople=d['numberOfPeople'], cancelationDays=d['cancelationDays'], description=d['description'])
       
        node = client.create_message(client.service, 'newRoom', room=room)
        tree = ET.ElementTree(node)
        tree.write('test.xml',pretty_print=True)

        try:
            result = client.service.newRoom(room=room)
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
        except zeep.exceptions.Fault as fault:
            return Response(fault.message, status=status.HTTP_400_BAD_REQUEST)

        data = request.data
        data['hotel'] = Hotel.objects.first().id
        serializer = RoomSerializerInput(data=data, context={'request': request, 'address_id': output_dict.pop('addressId'), 'id':output_dict.pop('roomId')})
        
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
    permission_classes = (IsAuthenticated,)
    queryset = Price.objects.all()
    filterset_class = RoomBetweenDatesFilter
    http_method_names = ['get', 'post', 'delete', 'head', 'options']

    def get_serializer_class(self):
        if self.action == 'create':
            return PriceSerializerInput
        return PriceSerializer

    def create(self, request, *args, **kwargs):
        serializer = PriceSerializerInput(data=request.data)
        if not serializer.is_valid():
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        validated_data = serializer.validated_data
        date_from = validated_data.pop('dateFrom')
        date_to = validated_data.pop('dateTo')
        delta = date_to - date_from

        client = return_zeep_client('/room-service/soap/price/price.wsdl', Token.objects.first().last_token, '/room-service/soap/price')
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


class RoomReservationFilter(filters.FilterSet):
    date = DateFromToRangeFilter()
    class Meta:
        model = Resrvation
        fields = ['dateFrom', 'roomId', 'status']


class ResrvationView(viewsets.ModelViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = Resrvation.objects.all()
    filterset_class = RoomReservationFilter
    
    def get_serializer_class(self):
        if self.action == 'update':
            return ResrvationSerializerPut
        return ResrvationSerializer
    
    def create(self, request, *args, **kwargs):
        serializer = ResrvationSerializer(data=request.data)
        if not serializer.is_valid():
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        validated_data = serializer.validated_data
        print("Connecting to main backend server")
        client = return_zeep_client('/reservations-service/ws/newReservation.wsdl', Token.objects.first().last_token, '/reservations-service/ws/newReservation')
        try:
            result = client.service.newReservation(id=validated_data['roomId'].id, dateTo=validated_data['dateTo'], dateFrom=validated_data['dateFrom'])
            print(result)
            print("Approved form main backend server")
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
            ret = Resrvation.objects.create(roomId = validated_data['roomId'], **output_dict)
            print("Creted")

            serializer = ResrvationSerializer(ret, context={'request': request})
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        except zeep.exceptions.Fault as fault:
            return Response(fault.message, status=status.HTTP_400_BAD_REQUEST)
        


    def update(self, request, pk, *args, **kwargs):
        print("Connecting to main backend server")
        client = return_zeep_client('/reservations-service/ws/reservation.wsdl', Token.objects.first().last_token, '/reservations-service/ws/reservation')
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
            return Response(zeep.exceptions.Fault.message, status=status.HTTP_400_BAD_REQUEST)


class ImageView(viewsets.ModelViewSet):
    permission_classes = (IsAuthenticated,)
    queryset = RoomFotos.objects.all()
    serializer_class = ImageSerializer
    filterset_fields = ('room',)

    def create(self, request, *args, **kwargs):
        serializer = ImageSerializer(data=request.data)
        if not serializer.is_valid():
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        validated_data = serializer.validated_data
        base_64_img = base64.b64encode(validated_data['photo'].read())

        print("Connecting to main backend server")
        client = return_zeep_client('/room-service/soap/addImage/addImage.wsdl', Token.objects.first().last_token, '/room-service/soap/addImage')
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


class MessageView(viewsets.ModelViewSet):
    permission_classes = (IsAuthenticated,)
    http_method_names = ['get', 'post', 'head', 'options']
    queryset = Message.objects.all()
    serializer_class = MessageSerializer
    filterset_fields = ('reservationId',)

    def create(self, request, *args, **kwargs):
        serializer = MessageSerializer(data=request.data)
        if not serializer.is_valid():
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        validated_data = serializer.validated_data

        print("Connecting to main backend server")
        client = return_zeep_client('/reservations-service/ws/newMessage.wsdl', Token.objects.first().last_token, '/reservations-service/ws/newMessage')
        try:
            result = client.service.newMessage(id=validated_data['reservationId'].id, message=validated_data['message'])
            print(result)
            print("Approved form main backend server")
            input_dict = zeep.helpers.serialize_object(result)
            output_dict = json.loads(json.dumps(input_dict))
            output_dict['reservationId_id'] = output_dict.pop('reservationId')
            ret = Message.objects.create(**output_dict)
            print("Creted")

            serializer = MessageSerializer(ret, context={'request': request})
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        except zeep.exceptions.Fault as fault:
            return Response(fault.message, status=status.HTTP_400_BAD_REQUEST)





