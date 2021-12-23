from django.db import models
from django.utils import timezone


class Ip(models.Model):
    ip = models.CharField(primary_key=True, max_length=45)
    payload = models.JSONField()
    modified = models.DateTimeField()

    def __str__(self):
        return self.ip

    def save(self, *args, **kwargs):
        self.modified = timezone.now()
        return super(Ip, self).save(*args, **kwargs)
