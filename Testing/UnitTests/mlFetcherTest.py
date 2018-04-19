import datetime
from unittest.mock import MagicMock
from unittest import TestCase
from prophet import mlFetcher as fetch
from prophet import models

# Mock query object for database lookups
class MockQuerySet(object):

    def __init__(self):
        self.name = "bitcoin"
        self.datetime = datetime.datetime(2018, 3, 3, 0, 0)
        self.historical_price = "123.45"
        self.circulating_supply = "12345.67"
        self.market_cap = "345.67"
        self.volume = "10"

# Test that the MLFetcher class works as expected
class TestMLFetcher(TestCase):

    def set_up(self):
        self.fetcher = fetch.MLFetcher()

    # check that collect data function sets internal variable correctly
    def test_collect_data(self):
        # mock the return from db lookup
        models.Historical.objects.all = MagicMock(return_value=[MockQuerySet()])
        self.fetcher.collect_data()
        self.assertEquals(self.fetcher.historical_data,
                          {'bitcoin': [['bitcoin', '1520053200', 123.45, 12345.67, 345.67, 10.0]]})

    def run_test(self):
        self.set_up()
        self.test_collect_data()
