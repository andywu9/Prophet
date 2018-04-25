import simplejson as json
from django.core import serializers
from django.shortcuts import render
from django.db.models import F
from prophet import models


# home view takes the information from the backend and sends it to the
# front end
def home(request):

    # Obtain data from the back end
    json_serializer = serializers.get_serializer("json")()
    coins = json_serializer.serialize(
        models.Coin.objects.all().order_by('rank'), ensure_ascii=True)
    symbols = models.Coin.objects.all()
    historical_data = models.Historical.objects.annotate(
        idmod2=F('id') % 3).filter(idmod2=0).order_by('datetime').values()
    predictive_data = models.Prediction.objects.all().order_by('datetime').values()
    descriptions = models.Description.objects.all()

    # Store pairings of coin symbol name to description
    descript = {}
    for item in descriptions.values():
        descript[item["name"]] = item["description"]

    # Store pairings of coin name to symbols
    symbol = {}
    for row in symbols.values():
        symbol[row['name']] = row['symbol']

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

    # Store price and volume changes for 1,6,12,24 hours
    changes = {}
    minutes = [12, 72, 144, 288]

    # for each coin, calculate the volume and price change
    # store result in changes
    for row in symbols.values():
        coin_name = row['name']
        changes[coin_name] = [
            str(row['price_change_day']), str(row['price_change_hour'])]

        for minute in minutes:
            if coin_name in graph_data.keys() and len(graph_data[coin_name]) > minute:
                changes[coin_name].append(str(graph_data[coin_name][-1]["volume"] -
                                              graph_data[coin_name][-minute]["volume"]))
            else:
                changes[coin_name].append("NA")

            # we need to calculate the price change for these increments
            # which are not provided by the api
            if minute == 72 or minute == 144:
                if coin_name in graph_data.keys() and len(graph_data[coin_name]) > minute:
                    current_price = graph_data[
                        coin_name][-1]["historical_price"]
                    old_price = graph_data[
                        coin_name][-minute]["historical_price"]
                    changes[coin_name].append(
                        str(round((current_price - old_price) / old_price * 100, 2)))
                else:
                    changes[coin_name].append("NA")

    # send the information to the front end
    return render(request, 'pages/home.html',
                  {"coins": coins, "data": json.dumps(changes, default=str),
                   "descriptions": json.dumps(descript, default=str),
                   "symbols": json.dumps(symbol, default=str),
                   "historical": json.dumps(graph_data, default=str),
                   "predictive": json.dumps(pred_data, default=str)})
