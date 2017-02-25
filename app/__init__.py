from flask import Flask, render_template
from app.module.beacon import Beacon
app =Flask(__name__)


@app.route('/')
def hello():
    return render_template('module/index.html')