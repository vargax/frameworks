import logging

from django.core.validators import validate_ipv46_address
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
    s = IpSerializer(ii, many=True)
    return JsonResponse(s.data, safe=False)


@api_view(['GET'])
def get_ip(r: Request, ip: str) -> JsonResponse:
    logger.info(f"{ip} requested...")
    s = IpSerializer(data={'ip': ip})
    s.is_valid(raise_exception=True)
    p = CoreService.get_ip_payload(ip=s.validated_data['ip'])
    return JsonResponse(data=p)


class IpSerializer(serializers.Serializer):
    ip = serializers.CharField(max_length=45)
    modified = serializers.DateTimeField(required=False)
    payload = serializers.JSONField(required=False)

    def validate_ip(self, value):
        validate_ipv46_address(value)
        return value

    def create(self, validated_data):
        raise NotImplementedError(
            "Not intended to be used for creating Ip instances"
        )

    def update(self, instance, validated_data):
        raise NotImplementedError(
            "Not intended to be used for updating Ip instances"
        )
