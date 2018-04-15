from prophet import observer
from prophet import mlFetcher as ml_fetch


# ML observer waits for new data updates
# and runs the ML predictions when received
class MLObserver(observer.Observer):

    my_subject = None

    def __init__(self, subject):
        self.my_subject = subject
        self.my_subject.add_observer(self)

    def update(self):
        ml_fetcher = ml_fetch.MLFetcher()
        ml_fetcher.run()
