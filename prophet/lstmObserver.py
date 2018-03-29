from prophet import observer


# LSTM observer waits for new data updates
# and runs the ML predictions when received
class LSTMObserver(observer.Observer):

    my_subject = None

    def __init__(self, subject):
        self.my_subject = subject
        self.my_subject.add_observer(self)

    def update(self):
        pass
