## Start from scratch
Create a virtual environment:
```bash
pip install --user virtualenv
mkdir djangoIPC && cd djangoIPC
virtualenv --python=/usr/bin/python3.10 .venv
source .venv/bin/activate
```
Install *django*, *pip-tools* and dependencies:
```bash
pip install django pip-tools
```
Create **django** *project* and *app*:
```bash
mkdir ipc
django-admin startproject app ipc
```
Run migrations, create superuser and run:
```bash
cd ipc
python manage.py migrate
python manage.py createsuperuser
python manage.py runserver
```

## Adding apps:
To add a new app, create the app:
```bash
python manage.py startapp core
```
And then add it to *INSTALLED_APPS* on *app/settings.py*.

## Adding packages
Add the package name to the *requirements.in* file in the project's root folder, 
generate *requirements.txt*, and install the dependencies:  
```bash
cd ..
echo "django-rest-framework" >> requirements.in
pip-compile requirements.in
pip install -r requirements.txt
```
Then add it to *INSTALLED_APPS* on *app/settings.py*.

## Models
Models are created on the *models.py* file. To make them available on the *admin* interface
register them on the *admin.py* file. Then make and run *migrations*:
```bash
python manage.py makemigrations
python manage.py migrate
```
