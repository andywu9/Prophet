from django.core.management.base import BaseCommand, CommandError
from prophet import historicalDataFetcher as fetcher
from prophet import coinTableObserver as coin_obs
from prophet import lstmObserver as lstm_obs


class Command(BaseCommand):
    help = 'Collects data'

    def add_argument(self, parser):
        pass

    # Create subject to fetch coin data and attach observers
    # to update database and ML predictions
    def handle(self, *args, **options):
        url = "https://api.coinmarketcap.com/v1/ticker/"
        historical_puller = fetcher.HistoricalDataFetcher(url)
        current_coin_table = coin_obs.CoinTableObserver(historical_puller)
        lstm = lstm_obs.LSTMObserver(historical_puller)
        historical_puller.collect_data()
