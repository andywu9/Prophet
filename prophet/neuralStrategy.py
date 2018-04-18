import strategy as strat
import lstm.runPrediction as runPrediction

# NeuralStrategy will execute the neural net
class NeuralStrategy(strat.Strategy):
    # run the neural net and pass back data
    def execute(self, filename):
        return runPrediction.execute(filename, 12)
