import logging

from django.http import JsonResponse
from rest_framework import serializers
from rest_framework.decorators import api_view
from rest_framework.request import Request

from .services import CoreService

logger = logging.getLogger(__name__)


@api_view(['GET'])
def get_all_ips(r: Request) -> JsonResponse:
    logger.info("All IPs requested...")
    ii = CoreService.get_cached_ips()
    s = CachedIpsSerializer(ii, many=True)
    return JsonResponse(s.data, safe=False)


@api_view(['GET'])
def get_ip(r: Request, ip: str) -> JsonResponse:
    logger.info(f"{ip} requested...")
    p = CoreService.get_ip_payload(ip=ip)
    return JsonResponse(data=p)


class CachedIpsSerializer(serializers.Serializer):
    ip = serializers.CharField(max_length=45)
    modified = serializers.DateTimeField()
    payload = serializers.JSONField()

    def create(self, validated_data):
        raise NotImplementedError(
            "Not intended to be used for creating Ip instances"
        )

    def update(self, instance, validated_data):
        raise NotImplementedError(
            "Not intended to be used for updating Ip instances"
        )
