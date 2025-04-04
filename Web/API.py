from flask import Flask, jsonify, redirect, render_template, request, session
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
#/api/login
@crickle.route('/login', methods = ['POST'])
    
def APIlogin():
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
        

@crickle.route('/login_fornitori', methods = ['POST','GET'])
    
def loginF():
    if(request.method=='POST'):
        data = request.form
        email = data.get('email')
        password = data.get('password')
        
        query = "SELECT id FROM fornitori WHERE email = %s AND password = %s"
        
        
        with connection.cursor() as cursor:
            cursor.execute(query, (email, password))
            result = cursor.fetchone()
                        
        if result:
            
            #forn = Fornitore(**result)
            session["idFornitore"]=result["id"]
            return "utente loggato"
        else:
            return "utente fallito"
        
    else:
        return render_template('login_fornitori.html')


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
        result = cursor.execute

    if result:
            return jsonify({'userId': result['id'], 'message': 'Login effettuato', 'email': email})
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
    idUtente = data.get('idutente')
    
    query = "INSERT INTO animali (ID_utente, Nome, Peso, Altezza, Note, Sesso, RatingAnimale) VALUES (%s, %s, %s, %s, %s, %s, %s)"
    
    with connection.cursor() as cursor:
        cursor.execute(query, (idUtente, nome, peso, altezza, note, sesso, ratingAnimale))
        
    return jsonify({'message': 'Animale registrato con successo'})

@crickle.route('/registra_fornitori', methods = ['POST','GET'])
def registrazioneFornitore():

    if(request.method=='POST'):
        data = request.form
        nome = data.get('nome')
        cognome = data.get('cognome')
        email = data.get('email')
        password = data.get('password')
        telefono = data.get('telefono')
        passwordCheck= data.get('password_check')

        if(password == passwordCheck):
            query = "INSERT INTO fornitori (nome, cognome, email, password, telefono) VALUES (%s, %s, %s, %s, %s)"
            with connection.cursor() as cursor:
                cursor.execute(query, (nome, cognome, email, password, telefono))   
            return redirect('/login_fornitori')
        
        else:
            return "password non corrisponde con conferma password"
    else:
        return render_template('registra_fornitori.html')

    

@crickle.route('/registra_attività', methods = ['POST'])
def creaAttività():
    if(request.method=='POST'):
        data = request.form
        idFornitore = session["idFornitore"]
        #da cambiare se si vuole usare autocomplete con tabella annessa
        TipoAttivita = data.get('Tipo_attività')
        nome = data.get('nome')
        indirizzo = data.get('indirizzo_attività')
        orario = data.get('orario')
        cap = data.get('cap')
        #la query è da cambiare perche il database non combacia
        query = "INSERT INTO servizi (ID_fornitore,tipo_attività,nome,indirizzo,orario,cap) VALUES (%s, %s, %s, %s, %s, %s)"
        
        with connection.cursor() as cursor:
            #questo idem
            cursor.execute(query, (idFornitore,TipoAttivita,nome,indirizzo,orario,cap))
            
        return 
    else:
        idFornitore = session["idFornitore"]
        return idFornitore




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

@crickle.route('/salvaCartellaClinica', methods = ['POST'])
def salvaCartellaClinica():
    data = request.get_json()
    ID_animale = data.get('idAnimale')
    descrizione = data.get('descrizione')
    titolo = data.get('titolo')
    
    print(data)
    query = "INSERT INTO cartelle_cliniche (ID_animale, titolo, descrizione) VALUES (%s, %s, %s)"
    
    
    with connection.cursor() as cursor:
       # print(f"Inserting data: idAnimale={ID_animale}, desc={descrizione},  titolo={titolo}")
        cursor.execute(query, (ID_animale, titolo, descrizione))
        #result = cursor.fetchone()
        
        
    return jsonify({'message': 'Cartella clinica salvata con successo'})
    

@crickle.route('/listaCartelleCliniche', methods = ['GET'])
def listaCartelleCliniche():
    idAnimali=request.args.get('idAnimale')
    query= "SELECT * from cartelle_cliniche WHERE ID_animale = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (idAnimali))
        values=cursor.fetchall()

    return jsonify (values)

@crickle.route('/cartella_clinica', methods = ['POST'])
def cartellaClinica():
    data = request.get_json()
    title = data.get('title')
    desc = data.get('description')
    date = data.get('date')
    idAnimale = data.get('idAnimale')

    
    query = "INSERT INTO cartelle_cliniche (ID_animale,descrizione,data_appuntamento,titolo) VALUES (%s, %s, %s, %s)"
    
    with connection.cursor() as cursor:
        cursor.execute(query, (idAnimale,desc,date,title))
        
    return jsonify({'message': 'appuntamento registrato con successo'})

@crickle.route('/home_fornitori')
def homeFornitori():
    query = "SELECT * FROM attività WHERE ID_fornitore = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (session["idFornitore"]))
        listaAttività=cursor.fetchall

    return render_template('home_fornitori.html', listaAttività= listaAttività)


    

@crickle.route('/')
def prova():
    #aggiunto questo reder template
    return render_template('login_fornitori.html')


if __name__ == '__main__':
    crickle.run(host = '0.0.0.0', debug=True)

@crickle.route('/modificaAnimale', methods = ['PUT'])
def modificaAnimale():
    data = request.get_json()
    nome = data.get('nome')
    peso = data.get('peso')
    altezza = data.get('altezza')
    note = data.get('note')
    sesso = data.get('sesso')
    ratingAnimale = data.get('ratingAnimale')
    idUtente = data.get('idutente')
    idAnimale = data.get('id')

    query = "UPDATE animali SET ID_utente = %s, Nome = %s, Peso = %s, Altezza = %s, Note = %s, Sesso = %s, RatingAnimale = %s WHERE id = %s"

    with connection.cursor() as cursor:
        cursor.execute(query, (idUtente, nome, peso, altezza, note, sesso, ratingAnimale, idAnimale))


@crickle.route('/modificaUtente', methods = ['PUT'])
def modificaUtente():
    data = request.get_json()
    nome = data.get('nome')
    cognome = data.get('cognome')
    email = data.get('email')
    password = data.get('password')
    telefono = data.get('telefono')

    query = "UPDATE utenti SET Nome = %s, Cognome = %s, Email = %s, Password = %s, Telefono = %s WHERE id = %s"

    with connection.cursor() as cursor:
        cursor.execute(query, (nome, cognome, email , password, telefono))
