from flask import Flask, jsonify, redirect, render_template, request
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
    data  = request.get_json()
    ID_utente = data.get("ID_utente")
    nome = data.get("nome")
    sesso = data.get("sesso")
    peso = data.get("peso")
    altezza = data.get("altezza")
    caratteristiche = data.get("caratteristiche")


    query = "INSERT INTO Animali (ID_utente,nome,sesso,peso,altezza,caratteristiche) VALUES (%s,%s,%s,%s,%s,%s)"
    with connection.cursor() as cursor:
        cursor.execute(query,(ID_utente,nome,sesso,peso,altezza,caratteristiche))
    return jsonify({"message":"animale registrato"}), 200

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
