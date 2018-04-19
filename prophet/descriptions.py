import json
import re
import requests
from prophet import models

# Script to generate descriptions for each coin and save in db

# Load all coins from cryptocompare api
response = requests.get("https://min-api.cryptocompare.com/data/all/coinlist")
coin_data = json.loads(response.text)

# For each coin find the associated ID
for coin in coin_data["Data"].keys():
    name = coin_data["Data"][str(coin)]["Symbol"]
    idd = coin_data["Data"][str(coin)]["Id"]

    # Using the id find the coin info from the api
    url = "https://www.cryptocompare.com/api/data/coinsnapshotfullbyid/?id=" + str(idd)
    resp = requests.get(url)
    descript = json.loads(resp.text)

    # parse response to obtain description
    description = descript["Data"]["General"]["Description"]
    description = re.sub('<.*?>', '', description)
    description.strip("|")
    description.strip("")

    # save the description in the descriptions table
    descrip = models.Description(name=name,
                                 description=description)
    descrip.save()
