from rest_framework import serializers

from core.models import Country


class CountrySerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Country
        fields = ['url', 'id', 'name', 'code']
