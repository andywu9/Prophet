import csv
import datetime
from unittest.mock import MagicMock
from unittest import TestCase
from prophet import mlService as serv
from prophet import linearStrategy as linear


class TestMLService(TestCase):
    """ Test that the MLService class works as expected """

    def __init__(self):
        super().__init__()
        self.set_up()

    def set_up(self):
        self.service = serv.MLService()

    def test_update(self):
        """ Test updates are stored internally in the correct manner """

        self.service.coin_name = "bitcoin"
        self.service.update_state([["2018-03-21 15:34:27", "123"],
                                   ["2018-03-22 15:34:27", "345"]])

        result = [{"name": "bitcoin", "predicted_price": 123,
                   "datetime": datetime.datetime(2018, 3, 21, 15, 34, 27)},
                  {"name": "bitcoin", "predicted_price": 345,
                   "datetime": datetime.datetime(2018, 3, 22, 15, 34, 27)}]
        self.assertEqual(self.service.my_predictions, result)

    def test_csv(self):
        """  Test that the csv file sent to the ml is corretcly generated """

        self.service.my_data = [['bitcoin', '1520053200', 123.45, 12345.67, 345.67, 10.0]]
        self.service.coin_name = 'bitcoin'
        self.service.my_strategy = linear.LinearStrategy()
        self.service.my_strategy.execute = MagicMock(return_value=[])
        self.service.run()
        expected = [["bitcoin", '1520053200', '123.45', '12345.67', '345.67']]
        with open('data.csv', 'r') as myfile:
            result = [row for row in csv.reader(myfile.read().splitlines())]
        self.assertEqual(expected, result)

    def run_test(self):
        """ Run the test suite """

        self.test_update()
        self.test_csv()
