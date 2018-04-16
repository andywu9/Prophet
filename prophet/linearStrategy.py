import quandl
import pandas as pd
import numpy as np

from sklearn.linear_model import LinearRegression
from sklearn import preprocessing, cross_validation, svm

from prophet import strategy as strat

from datetime import datetime, timedelta
import timestring

from numpy import genfromtxt


# LinearStrategy will execute the linear regression
class LinearStrategy(strat.Strategy):

    # call the tensorflow linear regression and pass back
    # the predictions
    def execute(self, filename):
        pass


    def execute_linreg(self, timeseries_csv_filename):    	
		df = pd.read_csv(timeseries_csv_filename, sep=',', names=['Date', 'Adj. Close'])
		original = df

		# df = quandl.get("WIKI/AMZN")
		# print df

		df = df[['Adj. Close']]

		forecast_out = int(5) # Forecast_out must be < len(data.csv)!!

		df['Prediction'] = df[['Adj. Close']].shift(-forecast_out)
		print df

		X = np.array(df.drop(['Prediction'], 1))
		X = preprocessing.scale(X)

		X_forecast = X[-forecast_out:]
		X = X[:-forecast_out]

		y = np.array(df['Prediction'])
		y = y[:-forecast_out]

		X_train, X_test, y_train, y_test = cross_validation.train_test_split(X, y, test_size = 0.2)

		clf = LinearRegression()
		clf.fit(X_train,y_train)

		confidence = clf.score(X_test, y_test)
		print("confidence: ", confidence)

		forecast_prediction = clf.predict(X_forecast)
		print(forecast_prediction)


		# Add timestamps to next prices
		last = list(original['Date'])[-1]
		prediction_timeseries = []
		last = timestring.Date(last) + 300
		for prediction in forecast_prediction:
			l = last.date.strftime("%Y-%m-%d %H:%M:%S") + '-04' # turn datetime -> string
			prediction_timeseries.append([l, prediction])
			last = last + 300 # increment 5 minutes

		return prediction_timeseries
