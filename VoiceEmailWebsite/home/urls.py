from django.urls import path, include
from .import views
urlpatterns = [
    path('send/', views.Home, name='home'),
    path('recieve/', views.Recieve, name='recieve'),
]
