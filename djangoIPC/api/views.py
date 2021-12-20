from rest_framework import generics
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.reverse import reverse

from .serializers import CountrySerializer

from core.models import Country


@api_view(['GET'])
def api_root(request, format=None):
    return Response({
        'countries': reverse('countries-list', request=request, format=format)
    })


class CountriesList(generics.ListAPIView):
    queryset = Country.objects.all()
    serializer_class = CountrySerializer


class CountyDetail(generics.RetrieveAPIView):
    queryset = Country.objects.all()
    serializer_class = CountrySerializer
