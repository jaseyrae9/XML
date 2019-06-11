from django.db import models
from django.core.validators import MaxValueValidator, MinValueValidator


class AdditionalService(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=256)

    def __str__(self):
        return self.name


class RoomType(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=256)

    def __str__(self):
        return self.name


class RoomCategory(models.Model):
    id = models.IntegerField(primary_key=True)
    numberOfStars = models.IntegerField()
    description = models.CharField(max_length=256)

    def __str__(self):
        return str(self.numberOfStars)


class Address(models.Model):
    country = models.CharField(max_length=30, default='', blank=False)
    state = models.CharField(max_length=30, default='', blank=False)
    city = models.CharField(max_length=30, default='', blank=False)
    postalCode = models.CharField(max_length=30, default='', blank=False)
    street = models.CharField(max_length=30, default='', blank=False)
    streetNumber = models.CharField(max_length=30, default='', blank=False)
    lat = models.FloatField()
    lng = models.FloatField()


class Hotel(models.Model):
    name = models.CharField(max_length=30, default='')
    address = models.OneToOneField(Address, on_delete=models.CASCADE)
    pib = models.CharField(max_length=30, default='', blank=False)


class Room(models.Model):
    hotel = models.ForeignKey(Hotel, blank=False, on_delete=models.CASCADE)
    address = models.OneToOneField(Address, on_delete=models.CASCADE)  #...
    roomType = models.ForeignKey(RoomType, blank=False, on_delete=models.CASCADE)
    roomCategory = models.ForeignKey(RoomCategory, blank=False, on_delete=models.CASCADE)
    additionalService = models.ManyToManyField(AdditionalService)
    roomNumber = models.IntegerField()
    defaultPrice = models.FloatField()  #...
    numberOfPeople = models.IntegerField(validators=[MinValueValidator(1)])
    cancelationDays = models.IntegerField(validators=[MinValueValidator(0)])
    description = models.CharField(max_length=256, default='', blank=False)
    numberOfRaitings = models.IntegerField()  #...


class Price(models.Model):
    room = models.ForeignKey(Room, on_delete=models.CASCADE)
    amount = models.FloatField()
    date = models.DateField()


class Resrvation(models.Model):
    room = models.ForeignKey(Room, on_delete=models.CASCADE)
    date_of_reservation = models.DateField()
    has_happend = models.BooleanField(default=False)
    customer_id = models.IntegerField()
