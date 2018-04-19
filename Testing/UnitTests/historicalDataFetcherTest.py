from unittest import TestCase
from prophet import historicalDataFetcher as historical

# Test Historical Data Fetcher
class TestHistoricalDataFetcher(TestCase):

    def set_up(self):
        self.historical = historical.HistoricalDataFetcher("url")

    # test we filter data as expected - remove none type
    def test_filter(self):
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
        self.assertEquals(self.historical.coin_data,
                          [{"circulating_supply": "123",
                            "market_cap": "1",
                            "name": "2",
                            "price": "3",
                            "price_change_day": "0",
                            "price_change_hour": "4",
                            "price_change_week": "5",
                            "rank": "6",
                            "volume": "7"}])

    def run_test(self):
        self.set_up()
        self.test_filter()
