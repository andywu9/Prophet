import json
import time
from datetime import datetime, timedelta
from lstm import buildModel
from lstm import dataHelper


def train_predict(train_file_arg):
    """ Train and predict time series data """

    # Load command line arguments
    train_file = train_file_arg
    parameter_file = "prophet/lstm/trainingConfig.json"

    # Load training parameters
    params = json.loads(open(parameter_file).read())

    # Load time series dataset, and split it into train and test
    x_train, y_train, x_test, _, x_test_raw, _,\
        last_window_raw, last_window, last_datetime_epoch = dataHelper.load_timeseries(train_file, params)

    # Build RNN (LSTM) model
    lstm_layer = [1, params["window_size"], params["hidden_unit"], 1]
    model = buildModel.rnn_lstm(lstm_layer, params)

    # Train RNN (LSTM) model with train set
    model.fit(
        x_train,
        y_train,
        batch_size=params["batch_size"],
        epochs=params["epochs"],
        validation_split=params["validation_split"])

    # Check the model against test set
    predicted = buildModel.predict_next_timestamp(model, x_test)
    predicted_raw = []
    for i in range(len(x_test_raw)):
        predicted_raw.append((predicted[i] + 1) * x_test_raw[i][0])

    # Predict next time stamp
    next_timestamp = buildModel.predict_next_timestamp(model, last_window)
    next_timestamp_raw = (next_timestamp[0] + 1) * last_window_raw[0][0]
    print("The next time stamp forecasting is: {}".format(next_timestamp_raw))

    # Add 5 minutes for a new timestamp of predictions
    last_datetime = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime(last_datetime_epoch))
    last_datetime = datetime.strptime(last_datetime, "%Y-%m-%d %H:%M:%S")
    new_datetime = last_datetime + timedelta(seconds=300)
    new_datetime_epoch = time.mktime(new_datetime.timetuple())
    new_datetime = new_datetime.strftime("%Y-%m-%d %H:%M:%S")

    # Concatenate datetime and price forecast
    new_entry = "coin_name," + str(new_datetime_epoch) + "," \
        + str(next_timestamp_raw) + ",coin_supply" + ",coin_mc" + "\n"

    # Write to CSV file of new prediction
    fd = open(train_file, "a")
    fd.write(new_entry)
    fd.close()

    # Return new prediction
    return [new_datetime, str(next_timestamp_raw)]
