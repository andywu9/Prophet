from prophet import observer
from prophet import models


class PredictionObserver(observer.Observer):
    """ Prediction observer waits for updates that
        new predictions are ready and then stores them in
        the predictions table
    """

    my_subject = None

    def __init__(self, subject):
        self.my_subject = subject
        self.my_subject.add_observer(self)

    # Update the predictions table to reflect the new predictions
    def update(self):
        predictions = self.my_subject.get_state()
        for prediction in predictions:
            pred = models.Prediction(name=prediction["name"],
                                     datetime=prediction["datetime"],
                                     predicted_price=prediction["predicted_price"],
                                     strategy=prediction["strategy"])
            pred.save()
