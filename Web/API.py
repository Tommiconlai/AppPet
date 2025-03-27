from flask import Flask, jsonify, redirect, render_template, request
import pymysql

import pymysql.cursors

crickle = Flask(__name__)

connection = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    database= 'appet',
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
            return jsonify({'userId': result['id'], 'message': 'Login effettuato', 'email': email})
        else:
            return jsonify({'message': 'Login fallito'}), 401
        


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

@crickle.route('/listaAnimali', methods = ['GET'])
def listaAnimali():
    idUtente=request.args.get('idutente')
    query= "SELECT * from animali WHERE ID_utente = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (idUtente))
        values=cursor.fetchall()

    return jsonify (values)

#da provare
@crickle.route('/animale', methods = ['GET'])
def animale():
    idAnimale=request.args.get('idAnimale')
    query= "SELECT * from animali WHERE ID = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (idAnimale))
        value=cursor.fetchone()

    return jsonify (value)

@crickle.route('/listaCartelleCliniche', methods = ['GET'])
def listaCartelleCliniche():
    idAnimali=request.args.get('idAnimale')
    query= "SELECT * from cartelle_cliniche WHERE ID_animale = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (idAnimali))
        values=cursor.fetchall()

    return jsonify (values)
    

@crickle.route('/')
def prova():
    #aggiunto questo reder template
    return render_template('home.html')


if __name__ == '__main__':
    crickle.run(host = '0.0.0.0', debug=True)
