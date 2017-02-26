from flask import Flask, render_template, request, redirect, url_for, session, escape
from app.controller.login_controller import LoginController
from app.module.question import Question
from app.module.user import User
from app.module.question import Question
import os

app = Flask(__name__)
app.secret_key = os.urandom(24)


@app.route('/index', methods=['POST', 'GET'])
def login():
    """
    session['user_idx'] (int)!
    :return:
    """
    # todo validation
    error = None
    if request.method == 'POST':
        user = LoginController.create_user_by_login_input(request.form['email'], request.form['password'])
        if user:
            user.event_idx = 1
            session['user_idx'] = user.idx
            session['event_idx'] = 1
            session['mail'] = request.form['email']
            return redirect(url_for('question'))
        else:
            error = 'Invalid. elo 600'
    return render_template('module/index.html', error=error)

@app.route('/question', methods=['POST', 'GET'])
def question():
    user = User.create(session['user_idx'], session['mail'])
    question = Question.random_question_id(session['user_idx'], 1)
    question_idx = question[0]
    table = list(question[1:-2])
    correct = question[-2]
    if request.method == 'POST':
        return redirect((url_for('login')))
    return render_template('module/question.html')

@app.route('/mateusz', methods=['POST', 'GET'])
def mateusz():
    if request.method == 'POST':
        data = request.values
        if LoginController.create_user_by_login_input(data['name'], data['password']):
            return 1
        else:
            return 0

@app.route('/pytanie', methods=['POST', 'GET'])
def pytanie():
    