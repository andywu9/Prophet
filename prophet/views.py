from django.core import serializers
from django.shortcuts import render
from prophet import models
import json

def home(request):  
	json_serializer = serializers.get_serializer("json")()
	coins = json_serializer.serialize(models.Coin.objects.all().order_by('id'), ensure_ascii=True)
	return render(request, 'pages/home.html', {"coins": coins})