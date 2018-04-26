import strategy as strat
import lstm.runPrediction as runPrediction

# NeuralStrategy will execute the neural net
class NeuralStrategy(strat.Strategy):
    # Run the neural net and pass back data to mlService
    def execute(self, filename):
        return runPrediction.execute(filename, 12)
    def get_type(self):
        return "neural"
