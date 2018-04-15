from prophet import strategy as strat


# LinearStrategy will execute the linear regression
class LinearStrategy(strat.Strategy):

    # call the tensorflow linear regression and pass back
    # the predictions
    def execute(self, filename):
        pass
