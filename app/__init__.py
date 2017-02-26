from flask import Flask, render_template, request
from app.module.beacon import Beacon
app =Flask(__name__)


@app.route('/', methods=['POST', 'GET'])
def hello():
    if request.method == 'POST':
        email = request.form['email']
        return text
    return render_template('module/index.html')