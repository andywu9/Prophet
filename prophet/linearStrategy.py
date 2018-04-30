import numpy as np
import pandas as pd
import timestring

from sklearn.linear_model import LinearRegression
from sklearn import preprocessing, model_selection
from prophet import strategy as strat


class LinearStrategy(strat.Strategy):
    """ LinearStrategy will execute the linear regression """

    def execute(self, filename):
        """ Call the linear regression and pass back
            the predictions
        """

        return execute_linreg(filename)

    def get_type(self):
        """ Return the type of strategy """

        return "linear"

def execute_linreg(timeseries_csv_filename):
    """ Execute a linear regression for a csv data file
        and return the predicted prices for 5 minute time intervals
    """

    headers = ["Coin name", "Date", "Adj. Close", "circ supply", "market", "volume"]
    df = pd.read_csv(timeseries_csv_filename, sep=",", names=headers)
    original = df
    df = df[["Adj. Close"]]

    # Set the number of predictions to be made
    forecast_out = int(len(df) / 2)
    if forecast_out <= 1:    # No predictions can be made in this case
        return[]

    # Set up the data
    df["Prediction"] = df[["Adj. Close"]].shift(-forecast_out)
    X = np.array(df.drop(["Prediction"], 1))
    X = preprocessing.scale(X)
    X_forecast = X[-forecast_out:]
    X = X[:-forecast_out]
    y = np.array(df["Prediction"])
    y = y[:-forecast_out]

    # Split the data
    X_train, X_test, y_train, y_test = model_selection.train_test_split(X, y, test_size=0.2)

    # Execute linear regression
    clf = LinearRegression()
    clf.fit(X_train, y_train)
    confidence = clf.score(X_test, y_test)
    # print(confidence)
    forecast_prediction = clf.predict(X_forecast)

    # Add timestamps to next prices
    last = list(original["Date"])[-1]
    prediction_timeseries = []
    last = timestring.Date(last) + 300
    for prediction in forecast_prediction:
        l = last.date.strftime("%Y-%m-%d %H:%M:%S")    # Turn datetime into string
        prediction_timeseries.append([l, prediction])
        last = last + 300    # Increment 5 minutes

    return prediction_timeseries
