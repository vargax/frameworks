from django.db import models
from django.utils import timezone


class Country(models.Model):
    name = models.CharField(max_length=20)
    code = models.CharField(max_length=2)


class Ip(models.Model):
    ip = models.CharField(primary_key=True, max_length=45)
    country = models.ForeignKey(Country, on_delete=models.RESTRICT)

    payload = models.JSONField()
    modified = models.DateTimeField()

    def save(self, *args, **kwargs):
        self.modified = timezone.now()
        return super(Ip, self).save(*args, **kwargs)


