from flask import Flask, flash, jsonify, redirect, render_template, request, session, url_for
import pymysql

import pymysql.cursors

crickle = Flask(__name__)
crickle.config.update(
    SECRET_KEY='1234'
)

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
        print(f"Login request received: {data}")
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
    idUtente = data.get('idutente')
    
    query = "INSERT INTO animali (ID_utente, Nome, Peso, Altezza, Note, Sesso, RatingAnimale) VALUES (%s, %s, %s, %s, %s, %s, %s)"
    
    with connection.cursor() as cursor:
        cursor.execute(query, (idUtente, nome, peso, altezza, note, sesso, ratingAnimale))
        
    return jsonify({'message': 'Animale registrato con successo'})

@crickle.route('/listaAnimali', methods = ['GET'])
def listaAnimali():
    idUtente=request.args.get('idutente')
    query= "SELECT * from animali WHERE ID_utente = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (idUtente))
        values=cursor.fetchall()

    return jsonify (values)


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

@crickle.route('/rimuoviCartellaClinica', methods = ['DELETE'])
def rimuoviCartellaClinica():
    idLogClinico = request.args.get("idCartella")

    query = "DELETE FROM cartelle_cliniche WHERE id = %s"

    with connection.cursor() as cursor:
        cursor.execute(query, (idLogClinico))

    return jsonify({'message': 'cartella cancellata'}), 200

@crickle.route('/rimuoviUtente', methods = ['DELETE'])
def rimuoviUtente():
    idUtente = request.args.get("idUtente")

    query = "DELETE FROM utenti WHERE id = %s"

    with connection.cursor() as cursor:
        cursor.execute(query, (idUtente))

    return jsonify({'message': 'Utente cancellato'}), 200

@crickle.route('/cercaUtente', methods = ['GET'])
def cercaUtente():
    idUtente = request.args.get("idUtente")

    query = "SELECT * FROM utenti WHERE id = %s"

    with connection.cursor() as cursor:
        cursor.execute(query, (idUtente))
        values = cursor.fetchall()

    return jsonify(values), 200

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

    query = "UPDATE animali SET ID_utente = %s, nome = %s, peso = %s, altezza = %s, note = %s, sesso = %s, ratingAnimale = %s WHERE id = %s"

    with connection.cursor() as cursor:
        cursor.execute(query, (idUtente, nome, peso, altezza, note, sesso, ratingAnimale, idAnimale))

    return jsonify ({'message': 'animale modificato'})


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
        
@crickle.route('/rimuoviAnimale', methods = ['DELETE'])
def rimuoviAnimale():
    idAnimale = request.args.get("idAnimale")
    
    query = "DELETE FROM animali WHERE id = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (idAnimale))
        
    return jsonify({'message': 'Animale cancellato'}), 200


####
####
#### LATO DESKTOP
####     I
####     V


@crickle.route('/home_fornitori')
def homeFornitori():
    query="SELECT attività_fornitori.id AS id_attività, attività_fornitori.nome as nome_attività," \
    "ID_fornitore, tipo_attività.nome as tipo_attività ,indirizzo,orario, cap " \
    "FROM attività_fornitori JOIN tipo_attività ON attività_fornitori.tipo_attività=tipo_attività.id " \
    "WHERE attività_fornitori.ID_fornitore = %s"
    #query = "SELECT * FROM attività_fornitori WHERE ID_fornitore = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (session["idFornitore"]))
        listaAttività=cursor.fetchall()
    print(listaAttività)

    return render_template('home_fornitori.html', listaAttività= listaAttività)



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
            return redirect('/home_fornitori')
        else:
            return "utente fallito"
        
    else:
        return render_template('login_fornitori.html')


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


@crickle.route('/registra_attività', methods = ['POST','GET'])
def creaAttività():
    if(request.method=='POST'):
        data = request.form
        idFornitore = session["idFornitore"]
        idTipoAttivita = int(data.get('tipo_attività'))
        nome = data.get('nome')
        indirizzo = data.get('indirizzo')
        orario = data.get('orario')
        cap = data.get('cap')

        #queryTipoAttività = "SELECT * FROM tipo_attività"
        #with connection.cursor() as cursor:
        #    cursor.execute(queryTipoAttività)
        #    tipiAttività=cursor.fetchall()

        #for x in tipiAttività:
        #    if(x['id']==idTipoAttivita):
        #        categoria=x['nome']
  

        query = "INSERT INTO attività_fornitori (ID_fornitore,tipo_attività,nome,indirizzo,orario,cap) VALUES (%s, %s, %s, %s, %s, %s)"
        
        with connection.cursor() as cursor:

            cursor.execute(query, (idFornitore,idTipoAttivita,nome,indirizzo,orario,cap))
            
        print("attività registrata")
        return redirect('/home_fornitori')
    else:
        
        queryTipoAttività = "SELECT * FROM tipo_attività"
        with connection.cursor() as cursor:
            cursor.execute(queryTipoAttività)
            tipoAttività=cursor.fetchall()

        return render_template('registra_attività.html',tipoAttività=tipoAttività)



@crickle.route('/modifica_attività', methods = ['GET'])
def ritornahome():
    return redirect('/home_fornitori')

@crickle.route('/modifica_attività/<int:attivitaId>', methods = ['GET','POST'])
def modificaAttività(attivitaId):
    if(request.method=='POST'):
    #idAttività = nell'url
        data=request.form
        tipoAttività = data.get('tipo_attività')
        nome = data.get('nome')
        indirizzo = data.get('indirizzo')
        orario = data.get('orario')
        cap = data.get('cap')

        queryAggiornaAttività = "UPDATE attività_fornitori SET tipo_attività = %s, nome = %s, indirizzo = %s, orario = %s, cap = %s WHERE id = %s"

        with connection.cursor() as cursor:
           cursor.execute(queryAggiornaAttività, (tipoAttività,nome,indirizzo,orario,cap,attivitaId))


        return redirect('/home_fornitori')

    else:

        queryrecuperaAttività = "SELECT * FROM attività_fornitori WHERE id = %s"
        with connection.cursor() as cursor:
            cursor.execute(queryrecuperaAttività, (attivitaId))
            attività=cursor.fetchone()

        queryTipoAttività = "SELECT * FROM tipo_attività"
        with connection.cursor() as cursor:
            cursor.execute(queryTipoAttività)
            tipoAttività=cursor.fetchall()

        return render_template('modifica_attività.html',attività=attività,tipoAttività=tipoAttività)
    
    
@crickle.route('/elimina_attività/<int:attivitaId>', methods = ['GET'])
def eliminaAttività(attivitaId):
    query = "DELETE FROM attività_fornitori WHERE id = %s"
    with connection.cursor() as cursor:
        cursor.execute(query,attivitaId)

    return redirect(url_for("homeFornitori"))






####
####
####Start APP
####
####

@crickle.route('/')
def prova():
    #aggiunto questo reder template
    return redirect('/login_fornitori')


if __name__ == '__main__':
    crickle.run(host = '0.0.0.0', debug=True)


