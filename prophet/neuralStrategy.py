import strategy as strat
import lstm.runPrediction as runPrediction


class NeuralStrategy(strat.Strategy):
    """ NeuralStrategy will execute the neural net """

    def execute(self, filename):
        """ Run the neural net and pass back data to mlService """

        return runPrediction.execute(filename, 12)

    def get_type(self):
        """ Return the type of strategy """

        return "neural"
