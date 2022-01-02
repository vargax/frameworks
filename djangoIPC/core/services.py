import logging

from django.conf import settings
from django.utils import timezone
from django.utils.dateparse import parse_duration

from .interfaces import IpapiInterface
from .models import Ip

logger = logging.getLogger(__name__)


class CoreService:
    CACHE_DURATION = parse_duration(settings.IPC['CACHE_EXP'])

    @staticmethod
    def get_ip_payload(*, ip: str) -> dict:
        try:
            record = Ip.objects.get(ip=ip)
            age = timezone.now() - record.modified
            if age > CoreService.CACHE_DURATION:
                logger.info(f"{ip} record is expired! It was cached {age.total_seconds()} seconds ago...")
                record.payload = IpapiInterface.query_ip(ip=ip)
                record.save()
        except Ip.DoesNotExist:
            logger.info(f"{ip} wasn't found locally...")
            payload = IpapiInterface.query_ip(ip=ip)
            record = Ip.objects.create(ip=ip, payload=payload)

        return record.payload

    @staticmethod
    def get_cached_ips() -> list:
        ii = Ip.objects.all()
        logger.info(f"{ii.count()} IPs found in the cache")
        return list(ii.values())
