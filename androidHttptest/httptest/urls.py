from django.urls import path
from . import views

urlpatterns = [
    path('print_hello', views.print_hello),
    path('accept_hello', views.accept_hello),
    path('catch_json', views.catch_json),
    ]
