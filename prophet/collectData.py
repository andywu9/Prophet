from prophet import dataModel as model


# collection script to save new data to the database
def collect():
    my_data = model.DataModel()
    adapted_data = model.DataAdapter(my_data)
    adapted_data.save()

if __name__ == "__main__":
    collect()
