from django.db import models
from django.core.validators import MaxValueValidator, MinValueValidator 

class Address(models.Model):
    country = models.CharField(max_length=30, default='', blank = False)
    state = models.CharField(max_length=30, default='', blank = False)
    city = models.CharField(max_length=30, default='', blank = False)
    postal_code = models.CharField(max_length=30, default='', blank = False)
    street = models.CharField(max_length=30, default='', blank = False)
    street_number = models.CharField(max_length=30, default='', blank = False)
    lat = models.FloatField()
    lng = models.FloatField()


class Hotel(models.Model):
    address = models.OneToOneField(Address, on_delete=models.CASCADE)
    pib = models.CharField(max_length=30, default='', blank = False)


class AdditionalService(models.Model):
    name = models.CharField(max_length=30, default='')


class RoomType(models.Model):
    name = models.CharField(max_length=30, default='', blank = False, unique = True)


class RoomCategory(models.Model):
    number_of_stars = models.IntegerField(validators=[MinValueValidator(0), MaxValueValidator(7)])
    description = models.CharField(max_length=256, default='', blank = False)


class Room(models.Model):
    hotel = models.ForeignKey(Hotel, blank = False, on_delete = models.CASCADE)
    address = models.OneToOneField(Address, on_delete=models.CASCADE)       #...
    room_type = models.ForeignKey(RoomType, blank = False, on_delete = models.CASCADE)
    room_category = models.ForeignKey(RoomCategory, blank = False, on_delete = models.CASCADE)
    additional_service = models.ForeignKey(AdditionalService, on_delete = models.CASCADE)
    room_number = models.IntegerField()
    default_price = models.FloatField()     #...
    number_of_people = models.IntegerField(validators=[MinValueValidator(1)])
    cancelation_days = models.IntegerField(validators=[MinValueValidator(0)])
    description = models.CharField(max_length=256, default='', blank = False)
    number_of_raitings = models.IntegerField()      #...


class Price(models.Model):
    room = models.ForeignKey(Room, on_delete = models.CASCADE)
    amount = models.FloatField()
    date = models.DateField()


class Resrvation(models.Model):
    room = models.ForeignKey(Room, on_delete = models.CASCADE)
    date_of_reservation = models.DateField()
    has_happend = models.BooleanField(default=False)
    customer_id = models.IntegerField()


class DayResrvation(models.Model):
    date = models.DateField()
    price = models.FloatField()
    reservation = models.ForeignKey(Resrvation, on_delete = models.CASCADE)
    













  