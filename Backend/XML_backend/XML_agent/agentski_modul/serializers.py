from rest_framework import serializers
from .models import *
from datetime import timedelta


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
        extra_kwargs = {'id': {'validators': []}}
        read_only_fields = ('id',)

    def create(self, validated_data):
        return Address.objects.create(id=self.context['id'], **validated_data)


class HotelSerializer(serializers.HyperlinkedModelSerializer):
    address = AddressSerializer()
    class Meta:
        model = Hotel
        fields = '__all__'
        

class RoomSerializer(serializers.ModelSerializer):
    class Meta:
        model = Room
        fields = '__all__'
        depth = 1


class RoomSerializerInput(serializers.ModelSerializer):
    address = AddressSerializer()
    # roomType = serializers.PrimaryKeyRelatedField( queryset=RoomType.objects.all(), write_only=True)
    # roomCategory = serializers.PrimaryKeyRelatedField(queryset=RoomCategory.objects.all(), write_only=True)
    # additionalService = serializers.PrimaryKeyRelatedField(queryset=AdditionalService.objects.all(), write_only=True, many=True)

    class Meta:
        model = Room
        # fields = ('url', 'id', 'hotel', 'address', 'roomType',
        #           'roomCategory', 'additionalService', 
        #           'roomNumber', 'defaultPrice', 'numberOfPeople', 'cancelationDays',
        #           'description', 'totalRating' ,'numberOfRaitings')
        fields = '__all__'
        read_only_fields = ('id',)
        

    def create(self, validated_data):
        address_data = validated_data.pop('address')
        address_data = Address.objects.create(id = self.context['address_id'], **address_data)
        additional_services = validated_data.pop('additionalService')

        room = Room.objects.create(id=self.context['id'], address = address_data, **validated_data)
        room.additionalService.set(additional_services)
        room.save()

        return room


class ImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = RoomFotos
        fields = '__all__'


class PriceSerializer(serializers.ModelSerializer):
    class Meta:
        model = Price
        fields = ('url', 'id', 'room', 'amount', 'date')


class PriceSerializerInput(serializers.Serializer):
    room = serializers.PrimaryKeyRelatedField(queryset = Room.objects.all())
    amount = serializers.FloatField()
    dateFrom = serializers.DateField()
    dateTo = serializers.DateField()


class ResrvationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Resrvation
        fields = ('url', 'id', 'room', 'customer', 'dateOfReservation', 'status', 'dateFrom', 'dateTo')
        read_only_fields = ('customer', 'dateOfReservation', 'status', 'id')


class ResrvationSerializerPut(serializers.ModelSerializer):
    class Meta:
        model = Resrvation
        fields = ('status',)






