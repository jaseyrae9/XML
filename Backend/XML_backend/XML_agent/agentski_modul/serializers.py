from rest_framework import serializers
from .models import *


class RoomCategorySerializer(serializers.ModelSerializer):
    class Meta:
        model = RoomCategory
        fields = '__all__'


class RoomTypeSerializer(serializers.ModelSerializer):
    class Meta:
        model = RoomType
        fields = '__all__'


class AdditionalServiceSerializer(serializers.ModelSerializer):
    class Meta:
        model = AdditionalService
        fields = '__all__'


class AddressSerializer(serializers.ModelSerializer):
    class Meta:
        model = Address
        fields = '__all__'


class HotelSerializer(serializers.HyperlinkedModelSerializer):
    address = AddressSerializer(read_only=True)
    class Meta:
        model = Hotel
        fields = ('url', 'id', 'pib', 'address')
        

class RoomSerializer(serializers.ModelSerializer):
    address = AddressSerializer()
    roomType_details = RoomTypeSerializer(source='roomType', read_only=True)
    roomType = serializers.PrimaryKeyRelatedField( queryset=RoomType.objects.all(), write_only=True)
    roomCategory_details = RoomCategorySerializer(source='roomCategory', read_only=True)
    roomCategory = serializers.PrimaryKeyRelatedField(queryset=RoomCategory.objects.all(), write_only=True)
    additionalService_details = AdditionalServiceSerializer(source='additionalService', read_only=True, many=True)
    additionalService = serializers.PrimaryKeyRelatedField(queryset=AdditionalService.objects.all(), write_only=True, many=True)

    class Meta:
        model = Room
        fields = ('url', 'id', 'hotel', 'address', 'roomType', 'roomType_details',
                  'roomCategory', 'roomCategory_details', 'additionalService', 'additionalService_details', 
                  'roomNumber', 'defaultPrice', 'numberOfPeople', 'cancelationDays',
                  'description', 'numberOfRaitings')
        

    def create(self, validated_data):
        address_data = validated_data.pop('address')
        address_data = Address.objects.create(**address_data)
        additional_services = validated_data.pop('additionalService')

        room = Room.objects.create(address = address_data, **validated_data)
        room.additionalService.set(additional_services)
        room.save()

        return room



class PriceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Price
        fields = ('url', 'id', 'room', 'amount', 'date')


class ResrvationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Resrvation
        fields = ('url', 'id', 'room', 'date_of_reservation', 'has_happend', 'customer_id')





