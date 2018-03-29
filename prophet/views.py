from django.core import serializers
from django.shortcuts import render
from prophet import models
import simplejson as json

def home(request):  
	json_serializer = serializers.get_serializer("json")()
	coins = json_serializer.serialize(models.Coin.objects.all().order_by('rank'), ensure_ascii=True)
	
	historical_data = models.Historical.objects.all().order_by('datetime');

	historical_data = historical_data.values()

	graph_data = {}

	for row in historical_data:
		#del row['datetime']
		if row['name'] in graph_data:
			graph_data[row['name']].append(row)
		else:
			graph_data[row['name']] = [row]

	return render(request, 'pages/home.html', {"coins": coins, "historical": json.dumps(graph_data, default=str)})