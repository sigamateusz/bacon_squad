from flask import Flask
from app.module.beacon import Beacon
app =Flask(__name__)

@app.route('/')
def hello():
    obj = Beacon.create_beacon_object_by_id(1)
    text = obj.hint
    return text