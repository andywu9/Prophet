from unittest import TestCase
from prophet import historicalDataFetcher as historical


class TestHistoricalDataFetcher(TestCase):
    """ Test the historical data fetcher class """

    def __init__(self):
        super().__init__()
        self.set_up()

    def set_up(self):
        """ Initialize the historical data fetcher to test """

        self.historical = historical.HistoricalDataFetcher("url")

    def test_filter(self):
        """ Test we filter data as expected, remove none type """

        self.historical.coin_data = [{"circulating_supply": "123",
                                      "market_cap": "1",
                                      "name": "2",
                                      "price": "3",
                                      "price_change_day": None,
                                      "price_change_hour": "4",
                                      "price_change_week": "5",
                                      "rank": "6",
                                      "volume": "7"}]
        self.historical.filter_data()
        self.assertEqual(self.historical.coin_data,
                         [{"circulating_supply": "123",
                           "market_cap": "1",
                           "name": "2",
                           "price": "3",
                           "price_change_day": "0",
                           "price_change_hour": "4",
                           "price_change_week": "5",
                           "rank": "6",
                           "volume": "7"}])

    def test_filter_no_change(self):
        """ Test we filter data as expected, nothing changed """

        self.historical.coin_data = [{"circulating_supply": "123",
                                      "market_cap": "1",
                                      "name": "2",
                                      "price": "3",
                                      "price_change_day": "7",
                                      "price_change_hour": "4",
                                      "price_change_week": "5",
                                      "rank": "6",
                                      "volume": "7"}]
        self.historical.filter_data()
        self.assertEqual(self.historical.coin_data,
                         [{"circulating_supply": "123",
                           "market_cap": "1",
                           "name": "2",
                           "price": "3",
                           "price_change_day": "7",
                           "price_change_hour": "4",
                           "price_change_week": "5",
                           "rank": "6",
                           "volume": "7"}])

    def run_test(self):
        """ Run the test suite """

        self.test_filter()
        self.test_filter_no_change()
