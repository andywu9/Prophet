from prophet import models
from prophet import observer


# Coin table observer waits for new data updates
# and stores data in the current coin table
class CoinTableObserver(observer.Observer):

    my_subject = None # define subject to watch for updates from

    def __init__(self, subject):
        self.my_subject = subject
        self.my_subject.add_observer(self)

    # upon update we save the data the subject found in the coin
    # table
    def update(self):
        for update in self.my_subject.get_state():
            models.Coin.objects.update_or_create(name=update["name"],
                                                 defaults=update)
