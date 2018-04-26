# interface all strategy types must implement
class Strategy():
    def execute(self, filename):
        pass
    def get_type(self):
        pass
