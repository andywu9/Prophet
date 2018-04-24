Prophet
=======

A cryptocurrency price prediction and market evaluation service 

.. image:: https://img.shields.io/badge/built%20with-Cookiecutter%20Django-ff69b4.svg
     :target: https://github.com/pydanny/cookiecutter-django/
     :alt: Built with Cookiecutter Django


:License: MIT


Settings
--------

Moved to settings_.

.. _settings: http://cookiecutter-django.readthedocs.io/en/latest/settings.html

Basic Setup
-----------
Run the following commands **in the Prophet directory** in order to setup Prophet on your local machine (assumes working in bash shell)

If you do not have a version of Python 3, please install it from here https://www.python.org/downloads/ or install using apt-get::

    $ sudo apt-get install python3.6

If you do not have pip3::

    $ sudo apt-get install python3-pip
    
Now let's install some requirements::

    $ pip3 install -r requirements.txt
    $ pip3 install django-debug-toolbar
    $ pip3 install django-extensions
    
If you do not have postgres, please install it with instructions here http://postgresguide.com/setup/install.html::

After installation, create postgres user and DB::

    $ sudo -u postgres -i
    $ createuser <YourLinuxUsername>
    $ createdb prophet
    $ exit

If postgreSQL did not install properly and you are running Ubuntu/Mint, follow https://gist.github.com/alistairewj/8aaea0261fe4015333ddf8bed5fe91f8 to setup postgreSQL on your machine. You may need to run::

    $ sudo apt-get install software-properties-common python-software-properties 

in order to use the add-apt-repository command.

Run PostgreSQL DB

**Ubuntu/Mint/etc users**::

    $ : sudo service postgresql start (This starts the database)
    
**Mac users**::

    $ pg_ctl -D /usr/local/var/postgres -l /usr/local/var/postgres/server.log start
    
Start website::

    $ python3 manage.py runserver

You should now have the website running. Check at **localhost:8000** to view the website.psql prophet


Database Sync
-------------

Migrate Database
^^^^^^^^^^^^^^^^

* Create a password::

    $ psql prophet
    $ \c prophet
    $ \password <yourpassword>
    $ \q 

* Open the file **base.py**. In this file, find the DATABASE dictionary and add a new key:value phrase inside the "default" key. The new pair should be **"PASSWORD":"<yourpassword>"**. Back in your bash shell, time to migrate the data::

    $ python3 manage.py makemigrations prophet
    $ python3 manage.py migrate
    $ python3 manage.py collect
    
* To populate the database with coin descriptions, run::
    
    $ python3 manage.py shell
    $ from prophet import descriptions

* You should now have data in your database to work with. If you ever want to update the database with the newest coin data, simply run::

    $ python3 manage.py collect

Tools
-----

Linters
^^^^^^^

We follow PEP8 and ES6 coding standards for our Python and Javascript, respectively. Please adhere to these standards when commiting new code to the project. The linters that we use to follow these standards can be found below::

* PyLint -> https://www.pylint.org/
* JSLint -> https://www.npmjs.com/package/jslint

We also use CSSLint for our CSS::

* CSSLint -> http://csslint.net/


Basic Commands
--------------

Setting Up Your Users
^^^^^^^^^^^^^^^^^^^^^

* To create a **normal user account**, just go to Sign Up and fill out the form. Once you submit it, you'll see a "Verify Your E-mail Address" page. Go to your console to see a simulated email verification message. Copy the link into your browser. Now the user's email should be verified and ready to go.

* To create an **superuser account**, use this command::

    $ python manage.py createsuperuser

For convenience, you can keep your normal user logged in on Chrome and your superuser logged in on Firefox (or similar), so that you can see how the site behaves for both kinds of users.

Live reloading and Sass CSS compilation
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Moved to `Live reloading and SASS compilation`_.

.. _`Live reloading and SASS compilation`: http://cookiecutter-django.readthedocs.io/en/latest/live-reloading-and-sass-compilation.html


Deployment
----------

The following details how to deploy this application.


Heroku
^^^^^^

See detailed `cookiecutter-django Heroku documentation`_.

.. _`cookiecutter-django Heroku documentation`: http://cookiecutter-django.readthedocs.io/en/latest/deployment-on-heroku.html



