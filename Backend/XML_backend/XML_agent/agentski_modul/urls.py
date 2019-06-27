from django.urls import path, include
from . import views
from rest_framework import routers
from django.conf.urls.static import static
from django.conf import settings

router = routers.DefaultRouter()
router.register('room_category', views.RoomCategoryView)
router.register('room_type', views.RoomTypeView)
router.register('additional_services', views.AdditionalServiceView)
router.register('hotel', views.HotelView)
router.register('room', views.RoomView)
router.register('room_image', views.ImageView)
router.register('price', views.PriceView)
router.register('reservation', views.ResrvationView)
router.register('messages', views.MessageView)
router.register('recensions', views.RecensionView)

urlpatterns = [
    path('login/', views.Login.as_view(), name='login'),
    path('', include(router.urls)),
]+static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)