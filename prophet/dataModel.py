from prophet import models
import json
import requests


# Save data returned from CoinMarketCap
class DataModel():
    data = {}

    # Store response from call to coin market cap
    def __init__(self):
        url = "https://api.coinmarketcap.com/v1/ticker/"
        response = requests.get(url)
        self.data = json.loads(response.text)

    # Access specific coins attribute
    def get(self, descriptor, index):
        return self.data[index][descriptor]

    def size(self):
        return len(self.data)


class Data():
    def save(): pass


# Adapt data retrieved from CoinMarketCap to store in database
class DataAdapter(Data):

    def __init__(self, data_model):
        self.my_data = data_model

    # For a coin we save the coin's information in the
    # historical data table and current data table
    def save_indiv(self, i):
        self.name = self.my_data.get("name", i)
        self.datetime = int(self.my_data.get("last_updated", i))
        self.historical_price = float(self.my_data.get("price_usd", i))
        self.market_cap = float(self.my_data.get("market_cap_usd", i))
        self.circulating_supply = float(
                self.my_data.get("available_supply", i))
        self.volume = float(self.my_data.get("total_supply", i))

        historical_data = models.Historical(
                name=self.name,
                datetime=self.datetime,
                historical_price=self.historical_price,
                market_cap=self.market_cap,
                circulating_supply=self.circulating_supply,
                volume=self.volume)
        historical_data.save()

        self.symbol = self.my_data.get("symbol", i)
        updates = {"circulating_supply": self.circulating_supply,
                   "market_cap": self.circulating_supply,
                   "name": self.name,
                   "price": self.historical_price,
                   "volume": self.volume}
        models.Coin.objects.update_or_create(symbol=self.symbol,
                                             defaults=updates)

    # Save method will save new data for every coin found
    def save(self):
        for index in range(self.my_data.size()):
            self.save_indiv(index)
