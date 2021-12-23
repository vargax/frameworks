from django.urls import path

from .apis import get_ip, get_all_ips

urlpatterns = [
    path('', get_all_ips),
    path('<str:ip>', get_ip),
]
