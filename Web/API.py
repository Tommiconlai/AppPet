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

@crickle.route('/login', methods = ['POST'])
def login():
    data = request.get_json()
    email = data.get('email')
    password = data.get('password')
    
    query = "SELECT id FROM utenti WHERE Email = %s AND password = %s"
    
    
    with connection.cursor() as cursor:
        cursor.execute(query, (email, password))
        result = cursor.fetchone()
        
    if result:
        return jsonify({'id':result[0]},{'message':'Login effettuato'})
    else:
        return jsonify({'message': 'Login fallito'})
    

@crickle.route('/registrazioneUtente', methods = ['POST'])
def registrazioneUtente():
    data = request.get_json()
    nome = data.get('nome')
    cognome = data.get('cognome')
    email = data.get('email')
    password = data.get('password')
    telefono = data.get('telefono')
    
    query = "INSERT INTO utenti (Nome, Cognome, Email, password, Telefono) VALUES (%s, %s, %s, %s, %s)"
    
    with connection.cursor() as cursor:
        cursor.execute(query, (nome, cognome, email, password, telefono))
        
    return jsonify({'message': 'Utente registrato con successo'})

@crickle.route('/registrazioneAnimale', methods = ['POST'])
def registrazioneA():
    data = request.get_json()
    nome = data.get('nome')
    peso = data.get('peso')
    altezza = data.get('altezza')
    note = data.get('note')
    sesso = data.get('sesso')
    ratingAnimale = data.get('ratingAnimale')
    
    query = "INSERT INTO animali (Nome, Peso, Altezza, Note, Sesso, RatingAnimale) VALUES (%s, %s, %s, %s, %s, %s)"
    
    with connection.cursor() as cursor:
        cursor.execute(query, (nome, peso, altezza, note, sesso, ratingAnimale))
        
    return jsonify({'message': 'Animale registrato con successo'})

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
    crickle.run(host = '0.0.0.0', debug=True)
