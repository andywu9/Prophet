# 2018-04-05 20:54:27-04,6847.2200000000
# last entry in bitcoin.csv before running script
import lstm.trainPredict as trainPredict

def make_prediction(filename):
	# Make prediction
	return trainPredict.train_predict(filename)

def execute(filename, steps):
	# Makes prediction for n-steps ahead
	predictions = []
	for i in range(steps):
		predictions.append(make_prediction(filename))
	return predictions
