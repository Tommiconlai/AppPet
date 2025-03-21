from flask import Flask, redirect, render_template, request
import pymysql

import pymysql.cursors

crickle = Flask(__name__)

connection = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    database= 'apppet',
    autocommit=True,
    cursorclass=pymysql.cursors.DictCursor
)

@crickle.route('/registrazioneUtente', methods = ['POST','GET'])
def registrazioneUtente():
    return 

@crickle.route('/registrazioneAnimale', methods = ['POST'])
def registrazioneA():
    return

@crickle.route('/creaAttività', methods = ['POST'])
def creaAttività():
    return "ciao2"

@crickle.route('/registrazioneFornitore', methods = ['POST','GET'])
def registrazioneF():
    return render_template('register.html')

@crickle.route('/')
def prova():
    return "ciao"

if __name__ == '__main__':
    crickle.run(debug=True,port=5000)
