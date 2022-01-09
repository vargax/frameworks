import logging
from typing import Dict

import requests
from django.conf import settings

logger = logging.getLogger(__name__)


class IpapiInterface:

    @staticmethod
    def query_ip(*, ip: str) -> Dict:
        logger.info(f"Querying IP-API for {ip}")
        r = requests.get(f"{settings.IPC['IP_API_URL']}{ip}")
        return r.json()
