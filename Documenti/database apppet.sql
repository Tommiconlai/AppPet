

use Apppet;


create table Utenti (
	id int primary key not null auto_increment,
    Nome varchar(50),
    Cognome varchar(50),
    Email varchar(50),
    password varchar(50),
    Telefono varchar(20)
    );
    
create table Animali (
	id int primary key not null auto_increment,
    ID_utente int,
    foreign key (id_utente) references Utenti(id),
    nome varchar(50),
    sesso boolean,
    peso float,
    altezza float,
    caratteristiche varchar(200)
    );
    
create table Attivita_calendario (
	id int primary key not null auto_increment,
    ID_animale int,
    foreign key (id_animale) references animali(id),
    nome varchar (50),
    descrizione varchar (200)
    );
    
    
create table cartelle_cliniche (
	id int primary key not null auto_increment,
	ID_animale int,
    foreign key (id_animale) references animali(id),
    descrizione varchar(200),
    data_appuntamento date
    );
    
create table datiEmotivi (
	id int primary key not null auto_increment,
    ID_animale int,
    foreign key (id_animale) references animali(id),
    valutazione int,
    data_valutazione date
    );
	

    
create table fornitori (
	id int primary key not null auto_increment,
    nome varchar (50),
    cognome varchar(50),
    email varchar (50),
    password varchar(50),
    telefono varchar(10)
    );
    
create table tipo_attività (
	id int primary key not null auto_increment,
    nome varchar(50)
    );

create table Servizi (
	id int primary key not null auto_increment,
    ID_fornitore int,
    foreign key (id_fornitore) references fornitori(id),
    ID_tipo_attività int,
    foreign key (id_tipo_attività) references tipo_attività(id),
    nome varchar (50),
    indirizzo varchar(100),
    orario varchar(20),
    cap char(5),
    latitudine double,
    longitudine double
    );
    
create table prenotazioni (
	id int primary key not null auto_increment,
    ID_animale int,
    foreign key (id_animale) references animali(id),
    ID_servizio int,
    foreign key (id_servizio) references servizi(id),
    data_prenotazione date,
    orario time
    );
    
create table recensioni (
	id int primary key not null auto_increment,
    ID_prenotazione int,
    foreign key (id_prenotazione) references prenotazioni(id),
    valutazione varchar(200)
    );
    
    
    
    
    
    
    
    
    
    
    

