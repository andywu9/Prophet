import datetime
import json
import requests
from prophet import models
from prophet import subject


class HistoricalDataFetcher(subject.Subject):
    """ Historical Data Fetcher class collects new coin data
        from an api and then alerts it's observers that it's state
        has been changed
    """

    coin_data = {}
    api_url = ""
    my_observers = []
    parsed_data = []

    def __init__(self, url):
        self.api_url = url
        self.circulating_supply = ""
        self.datetime = ""
        self.historical_price = ""
        self.market_cap = ""
        self.name = ""
        self.price_change_day = ""
        self.price_change_hour = ""
        self.price_change_week = ""
        self.rank = ""
        self.symbol = ""
        self.volume = ""

    def filter_data(self):
        """ Set all NoneType data to 0 """

        # Iterate for each coin and data type per coin
        for coin in self.coin_data:
            for value in coin:
                if coin[value] is None:
                    coin[value] = "0"

    def get_state(self):
        """ Return the parsed data from the api """

        return self.parsed_data


    def update_state(self):
        """ Store the new state we found """

        updates = {"circulating_supply": self.circulating_supply,
                   "market_cap": self.market_cap,
                   "name": self.name,
                   "price": self.historical_price,
                   "price_change_day": self.price_change_day,
                   "price_change_hour": self.price_change_hour,
                   "price_change_week": self.price_change_week,
                   "rank": self.rank,
                   "symbol": self.symbol,
                   "volume": self.volume}
        self.parsed_data.append(updates)

    def store_data(self):
        """ Save coin data from api in historical table and update observers """

        # For each coin save the new data and update our internal storage
        for coin in self.coin_data:
            self.circulating_supply = float(coin["available_supply"])
            self.datetime = datetime.datetime.fromtimestamp(
                int(coin["last_updated"]))
            self.historical_price = float(coin["price_usd"])
            self.market_cap = float(coin["market_cap_usd"])
            self.name = coin["name"]
            self.price_change_day = float(coin["percent_change_24h"])
            self.price_change_hour = float(coin["percent_change_1h"])
            self.price_change_week = float(coin["percent_change_7d"])
            self.rank = coin["rank"]
            self.symbol = coin["symbol"]
            self.volume = float(coin["total_supply"])

            # Update historical data table
            historical_data = models.Historical(
                circulating_supply=self.circulating_supply,
                datetime=self.datetime,
                historical_price=self.historical_price,
                market_cap=self.market_cap,
                name=self.name,
                volume=self.volume)


            historical_data.save()
            self.update_state()

        self.update_observers()

    def collect_data(self):
        """ A driver function to collect data from api """

        response = requests.get(self.api_url)
        self.coin_data = json.loads(response.text)
        self.filter_data()
        self.store_data()

    def add_observer(self, obs):
        """ Attach observers to subject """

        self.my_observers.append(obs)

    def remove_observer(self, obs):
        """ Remove observers from subject """

        self.my_observers.remove(obs)

    def update_observers(self):
        """ Send an update to all attached observers """

        for observer in self.my_observers:
            observer.update()
