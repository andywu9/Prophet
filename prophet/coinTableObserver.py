from prophet import models
from prophet import observer


class CoinTableObserver(observer.Observer):
    """ Coin table observer waits for new data updates
        and stores data in the current coin table
    """

    # Define subject to watch for updates from
    my_subject = None

    def __init__(self, subject):
        self.my_subject = subject
        self.my_subject.add_observer(self)

    def update(self):
        """  Upon update we save the data the
             subject found in the coin table
        """

        for update in self.my_subject.get_state():
            models.Coin.objects.update_or_create(name=update["name"],
                                                 defaults=update)
