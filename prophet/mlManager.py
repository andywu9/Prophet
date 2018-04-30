from prophet import linearStrategy as linear
from prophet import mlService as ml_serv
from prophet import neuralStrategy as neural
from prophet import predictionObserver as pred


class MLManager():
    """ ML manager class is reponsible for taking python
        data from the MLFetcher and determing the strategy
        that will be run on the data and setting any observers
    """

    historical_data = []

    def store_data(self, data):
        self.historical_data = data

    def run(self):
        """ Alert the ML service of the strategy to be used for the
            data based on the data size
        """

        ml_service = ml_serv.MLService()
        pred.PredictionObserver(ml_service)    # Attach prediction observer
        linear_strategy = linear.LinearStrategy()
        neural_strategy = neural.NeuralStrategy()

        # For each coin we set which strategy should be run
        for coin in self.historical_data:
            # Coins withless than 2000 data points use linreg
            if len(self.historical_data[coin]) <= 3000:
                ml_service.set_strategy(linear_strategy)
            else:
                ml_service.set_strategy(neural_strategy)

            # Set coin name and the data
            ml_service.set_data(self.historical_data[coin][0][0], self.historical_data[coin])
            ml_service.run()    # Execute the service
