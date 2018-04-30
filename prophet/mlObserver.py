from prophet import observer
from prophet import mlFetcher as ml_fetch


class MLObserver(observer.Observer):
    """ ML observer waits for new data updates
        and runs the ML predictions when received
    """

    my_subject = None

    def __init__(self, subject):
        self.my_subject = subject
        self.my_subject.add_observer(self)

    def update(self):
        """ When new data is pulled tell the fetcher
            to go and pull the new data for ml
        """

        ml_fetcher = ml_fetch.MLFetcher()
        ml_fetcher.run()
