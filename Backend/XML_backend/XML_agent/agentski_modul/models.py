from django.db import models
from django.core.validators import MaxValueValidator, MinValueValidator


class AdditionalService(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=256)
    active = models.BooleanField()

    def __str__(self):
        return self.name


class RoomType(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=256)
    active = models.BooleanField()

    def __str__(self):
        return self.name


class RoomCategory(models.Model):
    id = models.IntegerField(primary_key=True)
    numberOfStars = models.IntegerField()
    description = models.CharField(max_length=256, null = True)
    active = models.BooleanField()

    def __str__(self):
        return str(self.numberOfStars)


class Address(models.Model):
    id = models.IntegerField(primary_key=True)
    country = models.CharField(max_length=30, default='', blank=False)
    state = models.CharField(max_length=30, default='', blank=True)
    city = models.CharField(max_length=30, default='', blank=False)
    postalCode = models.CharField(max_length=30, default='', blank=False)
    street = models.CharField(max_length=30, default='', blank=False)
    streetNumber = models.CharField(max_length=30, default='', blank=False)
    lat = models.FloatField()
    lng = models.FloatField()


class Hotel(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=30, default='')
    address = models.OneToOneField(Address, on_delete=models.CASCADE, null=True)
    PIB = models.CharField(max_length=30, default='', blank=False)


class Room(models.Model):
    id = models.IntegerField(primary_key=True)
    hotel = models.ForeignKey(Hotel, blank=False, on_delete=models.CASCADE)
    address = models.OneToOneField(Address, on_delete=models.CASCADE)
    roomType = models.ForeignKey(RoomType, blank=False, on_delete=models.PROTECT)
    roomCategory = models.ForeignKey(RoomCategory, blank=False, on_delete=models.PROTECT)
    additionalService = models.ManyToManyField(AdditionalService)
    roomNumber = models.IntegerField()
    roomFloor = models.IntegerField()
    defaultPrice = models.FloatField()
    numberOfPeople = models.IntegerField(validators=[MinValueValidator(1)])
    cancelationDays = models.IntegerField(validators=[MinValueValidator(0)], null=True)
    description = models.CharField(blank=True, max_length=256, default='')
    totalRating = models.FloatField(blank=True, null=True)
    numberOfRaitings = models.IntegerField(blank=True, null=True)


class RoomFotos(models.Model):
    room = models.ForeignKey(Room,on_delete=models.CASCADE)
    is_cover = models.BooleanField()
    photo = models.FileField(upload_to='images/')


class Price(models.Model):
    room = models.ForeignKey(Room, on_delete=models.CASCADE)
    amount = models.FloatField()
    date = models.DateField()


class Customer(models.Model):
    id = models.IntegerField(primary_key=True)
    firstName = models.CharField(max_length=32)
    lastName = models.CharField(max_length=32)
    username = models.CharField(max_length=32)
    

class Resrvation(models.Model):
    id = models.IntegerField(primary_key=True)
    roomId = models.ForeignKey(Room, on_delete=models.PROTECT)
    # dateOfReservation = models.DateField()
    dateFrom = models.DateField()
    dateTo = models.DateField()
    totalPrice = models.FloatField()
    STATUS_CHOICES = (('RESERVED', 'RESERVED'), ('HAPPENED', 'HAPPENED'), ('CANCELED', 'CANCELED'))
    status = models.CharField(choices=STATUS_CHOICES, max_length=8)
    customer = models.ForeignKey(Customer, on_delete=models.PROTECT, null=True, blank=True)


class Message(models.Model):
    id = models.IntegerField(primary_key=True)
    reservationId = models.ForeignKey(Resrvation, on_delete=models.CASCADE)
    dateSent = models.DateTimeField()
    message = models.CharField(max_length=512)
    STATUS_CHOICES = (('TO_CUSTOMER', 'TO_CUSTOMER'), ('TO_AGENT', 'TO_AGENT'))
    status = models.CharField(choices=STATUS_CHOICES, max_length=11)


class Recension(models.Model):
    id = models.IntegerField(primary_key=True)
    reservationId = models.ForeignKey(Resrvation, on_delete=models.CASCADE)
    rating = models.FloatField()
    comment = models.CharField(max_length=512)
    date = models.DateTimeField()
    isApproved = models.BooleanField()


class Update(models.Model):
    last_updated = models.DateTimeField()


class Token(models.Model):
    last_token = models.CharField(max_length=512)