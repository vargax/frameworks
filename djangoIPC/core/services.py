import logging

from .interfaces import IpapiInterface
from .models import Ip

logger = logging.getLogger(__name__)


class CoreService:

    @staticmethod
    def get_ip_payload(*, ip: str) -> dict:
        try:
            i = Ip.objects.get(ip=ip)
        except Ip.DoesNotExist:
            logger.info(f"{ip} wasn't found locally...")
            q = IpapiInterface.query_ip(ip=ip)
            i = Ip.objects.create(ip=ip, payload=q)
        return i.payload

    @staticmethod
    def get_cached_ips() -> list:
        ii = Ip.objects.all()
        logger.info(f"{ii.count()} IPs found in the cache")
        return list(ii.values())
