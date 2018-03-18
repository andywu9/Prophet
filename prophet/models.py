from django.db import models
from django.urls import reverse
import json
import requests
import datetime


class Historical(models.Model):
    circulating_supply = models.DecimalField(max_digits=19, decimal_places=2,
                                             default=0)
    datetime = models.IntegerField(default=0)
    historical_price = models.DecimalField(max_digits=19, decimal_places=2,
                                           default=0)
    market_cap = models.DecimalField(max_digits=19, decimal_places=2,
                                     default=0)
    name = models.CharField(max_length=30, default="")
    volume = models.DecimalField(max_digits=19, decimal_places=2,
                                 default=0)


class Prediction(models.Model):
    datetime = models.IntegerField(default=0)
    name = models.CharField(max_length=30, default="")
    predicted_price = models.DecimalField(max_digits=19, decimal_places=2)


class Coin(models.Model):
    circulating_supply = models.DecimalField(max_digits=19,
                                             decimal_places=2, default=0)
    market_cap = models.DecimalField(max_digits=19,
                                     decimal_places=2, default=0)
    name = models.CharField(max_length=30, default="")
    price = models.DecimalField(max_digits=19, decimal_places=2, default=0)
    symbol = models.CharField(max_length=6, default="")
    volume = models.DecimalField(max_digits=19, decimal_places=2, default=0)
