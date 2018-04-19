from prophet import linearStrategy as linear
from prophet import mlService as ml_serv
from prophet import neuralStrategy as neural
from prophet import predictionObserver as pred


# ML manager class is reponsible for taking python
# data from the MLFetcher and determing the strategy
# that will be run on the data and setting any observers
class MLManager():

    historical_data = []

    def store_data(self, data):
        self.historical_data = data

    # alert the ML service of the strategy to be used for the
    # data based on the data size
    def run(self):
        ml_service = ml_serv.MLService()
        pred.PredictionObserver(ml_service)
        linear_strategy = linear.LinearStrategy()
        neural_strategy = neural.NeuralStrategy()

        # for each coin we generate predictions
        for coin in self.historical_data:
            # coins withless than 1000 data points use linreg
            if len(self.historical_data[coin]) <= 2000:
                ml_service.set_strategy(linear_strategy)
            else:
                ml_service.set_strategy(neural_strategy)

            # set coin name and the data
            ml_service.set_data(self.historical_data[coin][0][0], self.historical_data[coin])
            ml_service.run()
