from prophet import dataModel as model


def collect():
    my_data = model.DataModel()
    adapted_data = model.DataAdapter(my_data)
    adpated_data.save()

if __name__ == "__main__":
    collect()
