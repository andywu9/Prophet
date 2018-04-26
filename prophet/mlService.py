import datetime
from prophet import subject

# ML Service is responsible for calling a specific strategy to run
# on the data and then updating its state to refelect the new data
# Upon new data update the observers of new predictions
class MLService(subject.Subject):

    my_data = []
    coin_name = ''
    my_observers = []
    my_predictions = []
    my_strategy = None

    def set_strategy(self, strategy):
        self.my_strategy = strategy

    def set_data(self, name, data):
        self.coin_name = name
        self.my_data = data

    # store the new predictions for observer access
    def update_state(self, results):
        self.my_predictions = []
        if results != None:
            for item in results:
                my_predic = {"name": self.coin_name,
                             "predicted_price": float(item[1]),
                             "datetime": datetime.datetime.strptime(item[0], '%Y-%m-%d %H:%M:%S'),
                             "strategy": self.my_strategy.get_type()}
                self.my_predictions.append(my_predic)

    def get_state(self):
        return self.my_predictions

    # method to take the data to run through the ml and convert to a csv
    # and execute the strategy supplying the csv
    def run(self):

        # convert to csv and save data
        with open('data.csv', 'w+') as csvfile:
            for data in self.my_data:
                csvfile.write(self.coin_name + ',')
                for i in range(1, 5):
                    if i != 4:
                        csvfile.write(str(data[i]) + ',')
                    else:
                        csvfile.write(str(data[i]) + '\n')
        csvfile.close()
        results = self.my_strategy.execute('data.csv') # execute strategy

        # update the observers
        self.update_state(results)
        self.update_observers()

    def add_observer(self, obs):
        self.my_observers.append(obs)

    def remove_observer(self, obs):
        self.my_observers.remove(obs)

    def update_observers(self):
        for observer in self.my_observers:
            observer.update()
