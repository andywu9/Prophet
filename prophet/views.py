import simplejson as json
from django.core import serializers
from django.shortcuts import render
from prophet import models

# home view takes the information from the backend and sends it to the
# front end
def home(request):

    # Obtain data from the back end
    json_serializer = serializers.get_serializer("json")()
    coins = json_serializer.serialize(models.Coin.objects.all().order_by('rank'), ensure_ascii=True)
    symbols = models.Coin.objects.all()
    historical_data = models.Historical.objects.all().order_by('datetime').values()
    predictive_data = models.Prediction.objects.all().order_by('datetime').values()
    #descriptions = models.Description.objects.all()

    # Store pairings of coin symbol name to description
    # descript = {}
    # for item in descriptions.values():
    #     descript[item["name"]] = item["description"]


    # #Store pairings of coin name to symbols
    # symbol = {}
    # for row in symbols.values():
    #     symbol[row['name']] = row['symbol']

    # Reformat database to match usage in front-end.
    # Reorganizes data by grouping rows by coin name 
    # and referencing that group with the coin's name as a key
    graph_data = {}
    for row in historical_data:
        if row['name'] in graph_data:
            graph_data[row['name']].append(row)
        else:
            graph_data[row['name']] = [row]


    # Same process as above but for predictions
    pred_data = {}
    for row in predictive_data:
        if row['name'] in pred_data:
            pred_data[row['name']].append(row)
        else:
            pred_data[row['name']] = [row]

    # send the information to the front end
    return render(request, 'pages/home.html',
                  {"coins": coins, #"symbols": json.dumps(symbol, default=str),
                   #"descriptions": json.dumps(descript, default=str),
                   "historical": json.dumps(graph_data, default=str),
                   "predictive": json.dumps(pred_data, default=str)})
