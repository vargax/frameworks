# Generated by Django 4.0 on 2021-12-23 10:11

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Ip',
            fields=[
                ('ip', models.CharField(max_length=45, primary_key=True, serialize=False)),
                ('payload', models.JSONField()),
                ('modified', models.DateTimeField()),
            ],
        ),
    ]
