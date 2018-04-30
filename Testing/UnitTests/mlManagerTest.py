from unittest.mock import MagicMock
from unittest import TestCase
from prophet import mlManager as manager
from prophet import mlService as ml_serv
from prophet import linearStrategy as lin
from prophet import neuralStrategy as neural


class TestMLManager(TestCase):
    """ Test that the MLManager class works as expected """

    def __init__(self):
        super().__init__()
        self.set_up()

    def set_up(self):
        self.mlManager = manager.MLManager()
        self.ml_service = ml_serv.MLService()
        self.linearStrategy = lin.LinearStrategy()
        self.neuralStrategy = neural.NeuralStrategy()

    def test_collect_data_linear(self):
        """  Test that we use linear stratgegy with limited data """

        self.mlManager.historical_data = {"bitcoin": [range(10), range(10)]}
        ml_serv.MLService = MagicMock(return_value=self.ml_service)
        lin.LinearStrategy = MagicMock(return_value=self.linearStrategy)
        self.ml_service.run = MagicMock(return_value=True)
        self.mlManager.run()
        self.assertEqual(self.ml_service.my_strategy, self.linearStrategy)

    def test_collect_data_neural(self):
        """ Test that we use neural strategy with ample data """

        self.mlManager.historical_data = {"bitcoin": [range(10) for _ in range(3001)]}
        ml_serv.MLService = MagicMock(return_value=self.ml_service)
        neural.NeuralStrategy = MagicMock(return_value=self.neuralStrategy)
        self.ml_service.run = MagicMock(return_value=True)
        self.mlManager.run()
        self.assertEqual(self.ml_service.my_strategy, self.neuralStrategy)

    def test_collect_data_linear_boundry(self):
        """  Test that we use linear stratgegy with limited data close to the boundry """

        self.mlManager.historical_data = {"bitcoin": [range(10) for _ in range(2000)]}
        ml_serv.MLService = MagicMock(return_value=self.ml_service)
        lin.LinearStrategy = MagicMock(return_value=self.linearStrategy)
        self.ml_service.run = MagicMock(return_value=True)
        self.mlManager.run()
        self.assertEqual(self.ml_service.my_strategy, self.linearStrategy)

    def test_collect_data_neural_boundry(self):
        """ Test that we use neural strategy with ample data close to boundry """

        self.mlManager.historical_data = {"bitcoin": [range(10) for _ in range(2001)]}
        ml_serv.MLService = MagicMock(return_value=self.ml_service)
        neural.NeuralStrategy = MagicMock(return_value=self.neuralStrategy)
        self.ml_service.run = MagicMock(return_value=True)
        self.mlManager.run()
        self.assertEqual(self.ml_service.my_strategy, self.neuralStrategy)

    def run_test(self):
        """ Run the test suite """

        self.test_collect_data_linear()
        self.test_collect_data_neural()
        self.test_collect_data_linear_boundry()
        self.test_collect_data_neural_boundry()
