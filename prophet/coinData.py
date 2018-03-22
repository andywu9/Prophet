from prophet import models
import datetime
import json
import requests


# Save data returned from CoinMarketCap
class CoinData():
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
class CoinDataAdapter(Data):

    def __init__(self, data_model):
        self.my_data = data_model

    # For a coin we save the coin's information in the
    # historical data table and current data table
    def save_indiv(self, i):
        self.circulating_supply = float(
                self.my_data.get("available_supply", i))
        self.datetime = datetime.datetime.fromtimestamp(
                int(self.my_data.get("last_updated", i)))
        self.historical_price = float(self.my_data.get("price_usd", i))
        self.market_cap = float(self.my_data.get("market_cap_usd", i))
        self.name = self.my_data.get("name", i)
        self.volume = float(self.my_data.get("total_supply", i))

        # Update historical data table
        historical_data = models.Historical(
                circulating_supply=self.circulating_supply,
                datetime=self.datetime,
                historical_price=self.historical_price,
                market_cap=self.market_cap,
                name=self.name,
                volume=self.volume)

        historical_data.save()

        # Update current coin data table
        self.price_change_day = float(
                self.my_data.get("percent_change_24h", i))
        self.price_change_hour = float(
                self.my_data.get("percent_change_1h", i))
        self.price_change_week = float(
                self.my_data.get("percent_change_7d", i))
        self.rank = self.my_data.get("rank", i)
        self.symbol = self.my_data.get("symbol", i)
        updates = {"circulating_supply": self.circulating_supply,
                   "market_cap": self.market_cap,
                   "name": self.name,
                   "price": self.historical_price,
                   "price_change_day": self.price_change_day,
                   "price_change_hour": self.price_change_hour,
                   "price_change_week": self.price_change_week,
                   "rank": self.rank,
                   "volume": self.volume}
        models.Coin.objects.update_or_create(symbol=self.symbol,
                                             defaults=updates)

    # Save method will save new data for every coin found
    def save(self):
        for index in range(self.my_data.size()):
            self.save_indiv(index)
