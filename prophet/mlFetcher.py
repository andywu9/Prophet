from prophet import mlManager as ml_man
from prophet import models

# ML Fetcher fetches historical data and stores into a python
# data structure and then triggers the ML Manager
class MLFetcher():

    historical_data = {}

    # capture all coin historical data that is in the
    # database and store in a local data structure
    def collect_data(self):
        coins = models.Historical.objects.all() # pull all coin data
        for coin in coins:
            # append as coin already in data structure
            if coin.name in self.historical_data:
                self.historical_data[coin.name].append(
                    [str(coin.name), coin.datetime.strftime('%s'), float(coin.historical_price),
                     float(coin.circulating_supply), float(coin.market_cap), float(coin.volume)])
            else:
                # create new - coin not found
                self.historical_data[coin.name] = []
                self.historical_data[coin.name].append(
                    [str(coin.name), coin.datetime.strftime('%s'), float(coin.historical_price),
                     float(coin.circulating_supply), float(coin.market_cap), float(coin.volume)])

    # pass the stored data to the manager to run the machine learning
    def run(self):
        models.Prediction.objects.all().delete()
        ml_manager = ml_man.MLManager()
        self.collect_data()
        ml_manager.store_data(self.historical_data)
        ml_manager.run()
