from flask import Flask, render_template, request, redirect, url_for, session, escape
from app.controller.login_controller import LoginController
import os
app = Flask(__name__)
app.secret_key = os.urandom(24)

@app.route('/index', methods=['POST', 'GET'])
def login():
    """
    session['user_idx'] (int)!
    :return:
    """
    #todo validation
    error = None
    if request.method == 'POST':
        user = LoginController.create_user_by_login_input(request.form['email'], request.form['password'])
        if user:
            session['user_idx'] = user.idx
            return redirect(url_for('question'))
        else:
            error = 'Invalid Credentials. Please try again. elo 600'
    return render_template('module/index.html', error=error)

@app.route('/question', methods=['POST', 'GET'])
def question():
    if request.method == 'POST':
        return redirect((url_for('login')))
    return render_template('module/question.html')

@app.route('/mateusz', methods=['POST', 'GET'])
def mateusz():
    if request.method == 'POST':
        data = request.values
        print(data)
        print(data['name'])
        return  data['name']
    return 'LIPA'