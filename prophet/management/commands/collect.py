from django.core.management.base import BaseCommand, CommandError
from prophet import coinData as model


# Command to run data collections
class Command(BaseCommand):
    help = 'Collects data'

    def add_argument(self, parser):
        pass

    def handle(self, *args, **options):
            my_data = model.CoinData()
            adapted_data = model.CoinDataAdapter(my_data)
            adapted_data.save()
