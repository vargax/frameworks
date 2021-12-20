import os

from django.conf import settings
from django.utils.crypto import get_random_string


def get_secret() -> str:
    if s := os.environ.get('DJANGO_SECRET_KEY'):
        return s

    p = os.path.join(settings.BASE_DIR, '.django-insecure')

    if os.path.exists(p):
        with open(p) as f:
            return f.read()

    s = get_random_string(50)
    with open(p, 'x') as f:
        f.write(s)
    return s
