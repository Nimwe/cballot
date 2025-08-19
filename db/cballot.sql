CREATE TABLE binome_candidat(
   id_binome_candidat SERIAL,
   nom_equipe VARCHAR(250) ,
   nom_delegue VARCHAR(250) ,
   prenom_delegue VARCHAR(250) ,
   nom_suppleant VARCHAR(250) ,
   prenom_suppleant VARCHAR(250) ,
   PRIMARY KEY(id_binome_candidat)
);

CREATE TABLE organisateur(
   id_organisateur SERIAL,
   nom VARCHAR(250) ,
   email VARCHAR(250) ,
   role INTEGER,
   PRIMARY KEY(id_organisateur)
);

CREATE TABLE role_organisateur(
   id_role_organisateur SERIAL,
   role VARCHAR(250) ,
   PRIMARY KEY(id_role_organisateur)
);

CREATE TABLE formation(
   id_formation SERIAL,
   nom_ VARCHAR(250) ,
   id_organisateur INTEGER NOT NULL,
   PRIMARY KEY(id_formation),
   FOREIGN KEY(id_organisateur) REFERENCES organisateur(id_organisateur)
);

CREATE TABLE session_formation(
   id_session_formation SERIAL,
   debut DATE,
   fin DATE,
   id_formation INTEGER NOT NULL,
   PRIMARY KEY(id_session_formation),
   FOREIGN KEY(id_formation) REFERENCES formation(id_formation)
);

CREATE TABLE stagiaire(
   id_stagiaire SERIAL,
   email VARCHAR(250) ,
   nom VARCHAR(250) ,
   prenom VARCHAR(250) ,
   id_binome_candidat INTEGER,
   id_session_formation INTEGER NOT NULL,
   PRIMARY KEY(id_stagiaire),
   FOREIGN KEY(id_binome_candidat) REFERENCES binome_candidat(id_binome_candidat),
   FOREIGN KEY(id_session_formation) REFERENCES session_formation(id_session_formation)
);

CREATE TABLE scrutin(
   id_scrutin SERIAL,
   equipe_gagnante VARCHAR(250) ,
   id_session_formation INTEGER NOT NULL,
   PRIMARY KEY(id_scrutin),
   FOREIGN KEY(id_session_formation) REFERENCES session_formation(id_session_formation)
);

CREATE TABLE tour(
   id_tour SERIAL,
   date_heure TIMESTAMP,
   id_scrutin INTEGER NOT NULL,
   PRIMARY KEY(id_tour),
   FOREIGN KEY(id_scrutin) REFERENCES scrutin(id_scrutin)
);

CREATE TABLE vote(
   id_vote SERIAL,
   token_unique INTEGER,
   id_binome_candidat INTEGER NOT NULL,
   id_tour INTEGER NOT NULL,
   PRIMARY KEY(id_vote),
   FOREIGN KEY(id_binome_candidat) REFERENCES binome_candidat(id_binome_candidat),
   FOREIGN KEY(id_tour) REFERENCES tour(id_tour)
);

CREATE TABLE organisateur-session_formation(
   id_session_formation INTEGER,
   id_organisateur INTEGER,
   PRIMARY KEY(id_session_formation, id_organisateur),
   FOREIGN KEY(id_session_formation) REFERENCES session_formation(id_session_formation),
   FOREIGN KEY(id_organisateur) REFERENCES organisateur(id_organisateur)
);

CREATE TABLE organisateur-scrutin(
   id_organisateur INTEGER,
   id_scrutin INTEGER,
   PRIMARY KEY(id_organisateur, id_scrutin),
   FOREIGN KEY(id_organisateur) REFERENCES organisateur(id_organisateur),
   FOREIGN KEY(id_scrutin) REFERENCES scrutin(id_scrutin)
);

CREATE TABLE status(
   id_organisateur INTEGER,
   id_role_organisateur INTEGER,
   PRIMARY KEY(id_organisateur, id_role_organisateur),
   FOREIGN KEY(id_organisateur) REFERENCES organisateur(id_organisateur),
   FOREIGN KEY(id_role_organisateur) REFERENCES role_organisateur(id_role_organisateur)
);

CREATE TABLE stagiaire-vote(
   id_stagiaire INTEGER,
   id_vote INTEGER,
   PRIMARY KEY(id_stagiaire, id_vote),
   FOREIGN KEY(id_stagiaire) REFERENCES stagiaire(id_stagiaire),
   FOREIGN KEY(id_vote) REFERENCES vote(id_vote)
);

CREATE TABLE binome_candidat_tour(
   id_binome_candidat INTEGER,
   id_tour INTEGER,
   PRIMARY KEY(id_binome_candidat, id_tour),
   FOREIGN KEY(id_binome_candidat) REFERENCES binome_candidat(id_binome_candidat),
   FOREIGN KEY(id_tour) REFERENCES tour(id_tour)
);
