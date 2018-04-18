from lstm import trainPredict

def make_prediction(filename):
    # Make prediction
    return trainPredict.train_predict(filename)

def execute(filename, steps):
    # Makes prediction for n-steps ahead
    predictions = []
    for _ in range(steps):
        predictions.append(make_prediction(filename))
    return predictions
