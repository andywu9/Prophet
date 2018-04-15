from prophet import observer
from prophet import models


# Prediction observer waits for updates that
# new predictions are ready and then stores them in
# the predictions table
class PredictionObserver(observer.Observer):

    my_subject = None

    def __init__(self, subject):
        self.my_subject = subject
        self.my_subject.add_observer(self)

    # update the predictions table to reflect the new predictions
    def update(self):
        predictions = self.my_subject.get_state()
        for prediction in predictions:
            pred = models.Prediction(name=prediction["name"],
                                     datetime=prediction["datetime"],
                                     predicted_price=prediction["predicted_price"])
            pred.save()
