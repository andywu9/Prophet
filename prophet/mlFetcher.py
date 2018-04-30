from prophet import mlManager as ml_man
from prophet import models


class MLFetcher():
    """ ML Fetcher fetches historical data and stores into a python
        data structure and then triggers the ML Manager
    """

    historical_data = {}

    def collect_data(self):
        """ Capture all coin historical data that is in the
            database and store in a local data structure
        """

        # Pull all coin data
        coins = models.Historical.objects.all()
        for coin in coins:

            # Append as coin already in data structure
            if coin.name in self.historical_data:
                self.historical_data[coin.name].append(
                    [str(coin.name), coin.datetime.strftime('%s'), float(coin.historical_price),
                     float(coin.circulating_supply), float(coin.market_cap), float(coin.volume)])
            else:
                # Create new since coin not found
                self.historical_data[coin.name] = []
                self.historical_data[coin.name].append(
                    [str(coin.name), coin.datetime.strftime('%s'), float(coin.historical_price),
                     float(coin.circulating_supply), float(coin.market_cap), float(coin.volume)])

    def run(self):
        """ Pass the stored data to the manager to run the machine learning """

        models.Prediction.objects.all().delete()
        ml_manager = ml_man.MLManager()
        self.collect_data()
        ml_manager.store_data(self.historical_data)
        ml_manager.run()
