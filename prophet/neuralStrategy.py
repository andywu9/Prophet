from prophet import strategy as strat


# NeuralStrategy will execute the neural net
class NeuralStrategy(strat.Strategy):
    def execute(self, filename):
        pass # run the NEURAL net and pass back data
