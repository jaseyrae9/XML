from django.urls import path, include
from . import views
from rest_framework import routers

router = routers.DefaultRouter()
router.register('room_category', views.RoomCategoryView)
router.register('room_type', views.RoomTypeView)
router.register('additional_services', views.AdditionalServiceView)

router.register('hotel', views.HotelView)

router.register('room', views.RoomView)
router.register('price', views.PriceView)
router.register('reservation', views.ResrvationView)

urlpatterns = [
    path('login/', views.Login.as_view(), name='login'),
    path('', include(router.urls)),
]