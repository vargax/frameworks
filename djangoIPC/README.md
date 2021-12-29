# IP Cache
This sample Django project is based on the [*Django API Domains*](https://phalt.github.io/django-api-domains/) style
guide.

## [API](https://phalt.github.io/django-api-domains/files/#apis)
**APIs** interact exclusively with [*Services*](#services) ([*apis.py*](core/apis.py) shouldn't import
[*models.py*](core/models.py) nor [*interfaces.py*](core/interfaces.py)).

[Serializers](https://www.django-rest-framework.org/api-guide/serializers/#serializers) and
[*Function-based views*](https://www.django-rest-framework.org/api-guide/views/#function-based-views) are in
[*apis.py*](core/apis.py). URL patterns are in [*urls.py*](core/urls.py).

## [Services](https://phalt.github.io/django-api-domains/files/#services)

**Services** contain business logic and interacts with [*Models*](#models) and [*Interfaces*](#interfaces).

## [Models](https://docs.djangoproject.com/en/4.0/topics/db/models/)

**Models** are created in [*models.py*](core/models.py). To make them available on the *admin* interface register them
on the [*admin.py*](core/admin.py) file. Then make and run *migrations*:

```bash
python manage.py makemigrations
python manage.py migrate
```

## [Interfaces](https://phalt.github.io/django-api-domains/files/#interfaces)

Interfaces with other applications are in [*interfaces.py*](core/interfaces.py).

# Start from scratch
Create a virtual environment:
```bash
pip install --user virtualenv
mkdir djangoIPC && cd djangoIPC
virtualenv --python=/usr/bin/python3.10 .venv
source .venv/bin/activate
```
Install *django* and *pip-tools*:
```bash
pip install django pip-tools
```
Create **django** *project* and *app*:
```bash
django-admin startproject app .
```
Run migrations, create superuser and run:
```bash
python manage.py migrate
python manage.py createsuperuser
python manage.py runserver
```

## Adding apps:
Create the app:
```bash
python manage.py startapp core
```
And then add it to *INSTALLED_APPS* in [*app/settings.py*](app/settings.py).

## Adding packages
Add the package name to the [*requirements.in*](requirements.in) file, generate [*requirements.txt*](requirements.txt), 
and install dependencies:  
```bash
pip-compile requirements.in
pip install -r requirements.txt
```
Then add it to *INSTALLED_APPS* in [*app/settings.py*](app/settings.py).

