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
echo "django-rest-framework" > requirements.in
pip-compile requirements.in
pip install -r requirements.txt
```
Create **django** *project* and *app*:
```bash
mkdir ipc
django-admin startproject app ipc
cd ipc
python manage.py startapp core
```
Run migrations, create superuser and run:
```bash
python manage.py migrate
python manage.py createsuperuser
python manage.py runserver
```