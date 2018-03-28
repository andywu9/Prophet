from django.core import serializers
from django.shortcuts import render
from prophet import models
import simplejson as json

def home(request):  
	json_serializer = serializers.get_serializer("json")()
	coins = json_serializer.serialize(models.Coin.objects.all().order_by('rank'), ensure_ascii=True)
	
	historical = models.Historical.objects.all().order_by('datetime');

	historical = historical.values()

	graph_data = {}

	for row in historical:
		del row['datetime']
		if row['name'] in graph_data:
			graph_data[row['name']].append(row)
		else:
			graph_data[row['name']] = [row]



	historical = json_serializer.serialize(models.Historical.objects.all(), ensure_ascii=True)
	return render(request, 'pages/home.html', {"coins": coins, "historical": json.dumps(graph_data)})