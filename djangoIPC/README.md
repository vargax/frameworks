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
echo "django-rest-framework" >> requirements.in
pip-compile requirements.in
pip install -r requirements.txt
```
Then add it to *INSTALLED_APPS* in [*app/settings.py*](app/settings.py).

# Models
[Models](https://docs.djangoproject.com/en/4.0/topics/db/models/) are created in [*models.py*](core/models.py).
To make them available on the *admin* interface register them on the [*admin.py*](core/admin.py) file. Then 
make and run *migrations*:
```bash
python manage.py makemigrations
python manage.py migrate
```

# Expose API
To expose a [RESTful API](https://www.django-rest-framework.org/):
1. [Add](#adding-apps) the *api* app and remove *migrations*, *models.py* and *admin.py*.
```bash
python manage.py startapp api
rm -fr api/migrations api/models.py api/admin.py
```
2. Create [hyperlinked](https://www.django-rest-framework.org/tutorial/5-relationships-and-hyperlinked-apis/#hyperlinking-our-api) 
   [*serializers*](api/serializers.py) for your classes.
3. Define [generic class-based](https://www.django-rest-framework.org/tutorial/3-class-based-views/#using-generic-class-based-views) 
   [*views*](api/views.py) for your endpoints.
4. Register [named URLs](https://www.django-rest-framework.org/tutorial/5-relationships-and-hyperlinked-apis/#making-sure-our-url-patterns-are-named)
   on [*url.py*](api/urls.py).
5. Register your *api* path on [*app/urls.py*](app/urls.py). 


