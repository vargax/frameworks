from django.urls import path

from .views import *

urlpatterns = [
    path('', api_root),
    path('countries/', CountriesList.as_view(), name='countries-list'),
    path('countries/<int:pk>/', CountyDetail.as_view(), name='country-detail'),
]
