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

If you do not have pip3::

    $ sudo apt-get install python3-pip
    
Install some requirements::

    $ pip3 install -r requirements.txt
    $ pip3 install django-debug-toolbar
    $ pip3 install django-extensions
    
Create postgres user and DB::

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

You should now have the website running. Check at **localhost:8000** to view the website.


Basic Commands
--------------

Setting Up Your Users
^^^^^^^^^^^^^^^^^^^^^

* To create a **normal user account**, just go to Sign Up and fill out the form. Once you submit it, you'll see a "Verify Your E-mail Address" page. Go to your console to see a simulated email verification message. Copy the link into your browser. Now the user's email should be verified and ready to go.

* To create an **superuser account**, use this command::

    $ python manage.py createsuperuser

For convenience, you can keep your normal user logged in on Chrome and your superuser logged in on Firefox (or similar), so that you can see how the site behaves for both kinds of users.

Test coverage
^^^^^^^^^^^^^

To run the tests, check your test coverage, and generate an HTML coverage report::

    $ coverage run manage.py test
    $ coverage html
    $ open htmlcov/index.html

Running tests with py.test
~~~~~~~~~~~~~~~~~~~~~~~~~~

::

  $ py.test

Live reloading and Sass CSS compilation
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Moved to `Live reloading and SASS compilation`_.

.. _`Live reloading and SASS compilation`: http://cookiecutter-django.readthedocs.io/en/latest/live-reloading-and-sass-compilation.html





Sentry
^^^^^^

Sentry is an error logging aggregator service. You can sign up for a free account at  https://sentry.io/signup/?code=cookiecutter  or download and host it yourself.
The system is setup with reasonable defaults, including 404 logging and integration with the WSGI application.

You must set the DSN url in production.


Deployment
----------

The following details how to deploy this application.


Heroku
^^^^^^

See detailed `cookiecutter-django Heroku documentation`_.

.. _`cookiecutter-django Heroku documentation`: http://cookiecutter-django.readthedocs.io/en/latest/deployment-on-heroku.html




