class Strategy():
    """ Interface all strategy types must implement """

    def execute(self, filename):
        pass
    def get_type(self):
        pass
