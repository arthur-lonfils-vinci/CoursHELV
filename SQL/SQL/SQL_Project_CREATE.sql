DROP SCHEMA IF EXISTS project_schema CASCADE;
CREATE SCHEMA project_schema;

CREATE TABLE project_schema.festivals
(
    id_festival SERIAL PRIMARY KEY,
    nom         VARCHAR(40) NOT NULL CHECK ( TRIM(nom) <> ' ' )
);


CREATE TABLE project_schema.salles
(
    id_salle SERIAL PRIMARY KEY,
    nom      VARCHAR(40) NOT NULL CHECK ( TRIM(nom) <> ' ' ),
    ville    VARCHAR(40) NOT NULL CHECK ( TRIM(ville) <> ' ' ),
    capacite INTEGER     NOT NULL
);

CREATE TABLE project_schema.clients
(
    id_client       SERIAL PRIMARY KEY,
    nom_utilisateur VARCHAR(40)  NOT NULL UNIQUE CHECK ( TRIM(nom_utilisateur) <> ' ' ),
    email           VARCHAR(120) NOT NULL CHECK ( TRIM(email) <> ' ' ),
    mot_de_passe    VARCHAR(50)  NOT NULL CHECK ( TRIM(mot_de_passe) <> ' ' )
);

CREATE TABLE project_schema.artistes
(
    id_artiste  SERIAL PRIMARY KEY,
    nom         VARCHAR(40) NOT NULL CHECK ( TRIM(nom) <> ' ' ),
    nationalite CHAR(3) CHECK ( TRIM(nationalite) <> ' ' )
);

CREATE TABLE project_schema.evenements
(
    salle               INTEGER          NOT NULL REFERENCES project_schema.salles (id_salle),
    date_evenement      DATE             NOT NULL,
    nom                 VARCHAR(40)      NOT NULL CHECK ( TRIM(nom) <> ' ' ),
    prix                DOUBLE PRECISION NOT NULL,
    nb_places_restantes INTEGER          NOT NULL,
    festival            INTEGER          NOT NULL REFERENCES project_schema.festivals (id_festival),
    CONSTRAINT ev_pkey PRIMARY KEY (salle, date_evenement)
);

CREATE TABLE project_schema.concerts
(
    artiste        INTEGER NOT NULL REFERENCES project_schema.artistes (id_artiste),
    date_evenement DATE    NOT NULL,
    heure_debut    TIME    NOT NULL,
    salle          INTEGER NOT NULL,
    FOREIGN KEY (date_evenement, salle) REFERENCES project_schema.evenements (date_evenement, salle),
    CONSTRAINT co_pkey PRIMARY KEY (artiste, date_evenement),
    UNIQUE (salle, date_evenement, heure_debut)
);

CREATE TABLE project_schema.reservations
(
    salle           INTEGER NOT NULL,
    date_evenement  DATE    NOT NULL,
    num_reservation INTEGER  NOT NULL,
    nb_tickets      INTEGER NOT NULL,
    client          INTEGER NOT NULL REFERENCES project_schema.clients (id_client),
    FOREIGN KEY (salle, date_evenement) REFERENCES project_schema.evenements (salle, date_evenement),
    CONSTRAINT re_pkey PRIMARY KEY (salle, date_evenement, num_reservation)

);

--FUNCTION----------------------------

CREATE OR REPLACE FUNCTION ajouterSalle(add_nom VARCHAR, add_ville VARCHAR, add_capacite VARCHAR)
    RETURNS INTEGER AS
$$
DECLARE
    add_id_salle int;
BEGIN
    INSERT INTO project_schema.salles (nom, ville, capacite)
    VALUES (add_nom, add_ville, add_capacite)
    RETURNING salles.id_salle INTO add_id_salle;
    RETURN add_id_salle;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ajouterFestival(add_nom VARCHAR)
    RETURNS INTEGER AS
$$
DECLARE
    add_id_festival int;
BEGIN
    INSERT INTO project_schema.festivals (nom)
    VALUES (add_nom)
    RETURNING festivals.id_festival INTO add_id_festival;
    RETURN add_id_festival;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ajouterArtiste(add_nom VARCHAR, add_nationalite CHAR)
    RETURNS INTEGER AS
$$
DECLARE
    add_id_artiste int;
BEGIN
    INSERT INTO project_schema.artistes (nom, nationalite)
    VALUES (add_nom, add_nationalite)
    RETURNING artistes.id_artiste INTO add_id_artiste;
    RETURN add_id_artiste;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ajouterClient(add_nom VARCHAR, add_email VARCHAR, add_mdp VARCHAR)
    RETURNS INTEGER AS
$$
DECLARE
    add_id_client int;
BEGIN
    INSERT INTO project_schema.clients (nom_utilisateur, email, mot_de_passe)
    VALUES (add_nom, add_email, add_mdp)
    RETURNING clients.id_client INTO add_id_client;
    RETURN add_id_client;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ajouterEvenement(add_salle INTEGER, add_date_event DATE, add_nom VARCHAR, add_prix INTEGER,
                                            add_nb_places_restantes INTEGER, add_festival INTEGER)
    RETURNS VOID as
$$
BEGIN
    INSERT INTO project_schema.evenements(salle, date_evenement, nom, prix, nb_places_restantes, festival)
    VALUES (add_salle, add_date_event, add_nom, add_prix, add_nb_places_restantes, add_festival);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ajouterConcert(add_artiste INTEGER, add_date_event DATE, add_heure_debut TIME,
                                          add_salle INTEGER)
    RETURNS VOID as
$$
BEGIN
    BEGIN
        INSERT INTO project_schema.concerts(artiste, date_evenement, heure_debut, salle)
        VALUES (add_artiste, add_date_event, add_heure_debut, add_salle);
    END;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ajouterReservation(add_salle INTEGER, add_date_event DATE,
                                              add_nb_tickets INTEGER, add_client INTEGER)
    RETURNS VOID as
$$
BEGIN
    INSERT INTO project_schema.reservations(salle, date_evenement, nb_tickets, client)
    VALUES (add_salle, add_date_event, add_nb_tickets, add_client);
END;
$$ LANGUAGE plpgsql;


--TRIGGER----------------------------


CREATE OR REPLACE FUNCTION enforce_uppercase()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.nationalite := UPPER(NEW.nationalite);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER uppercase_trigger
    BEFORE INSERT OR UPDATE
    ON project_schema.artistes
    FOR EACH ROW
EXECUTE FUNCTION enforce_uppercase();

--------------------------------------------
CREATE OR REPLACE FUNCTION check_date_event()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.date_evenement < NOW()::DATE THEN
        RAISE EXCEPTION 'L événement est dans le passé %', NEW.date_evenement;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER check_date_event
    BEFORE INSERT OR UPDATE
    ON project_schema.evenements
    FOR EACH ROW EXECUTE FUNCTION check_date_event();

--------------------------------------------

CREATE OR REPLACE FUNCTION check_date_concert()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.date_evenement < NOW()::DATE THEN
        RAISE EXCEPTION 'Le concert est dans le passé %', NEW.date_evenement;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER check_date_concert
    BEFORE INSERT OR UPDATE
    ON project_schema.concerts
    FOR EACH ROW EXECUTE FUNCTION check_date_concert();

--------------------------------------------

CREATE OR REPLACE FUNCTION check_concert_event_exists()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM project_schema.evenements
        WHERE date_evenement = NEW.date_evenement AND salle = NEW.salle
    ) THEN
        RAISE EXCEPTION 'The event with date % and salle % does not exist in the evenements table', NEW.date_evenement, NEW.salle;
    END IF;

    RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_event_exists
    BEFORE INSERT ON project_schema.concerts
    FOR EACH ROW
    EXECUTE FUNCTION check_concert_event_exists();

--------------------------------------------

CREATE OR REPLACE FUNCTION check_concert_exists()
    RETURNS TRIGGER AS
$$
BEGIN
    IF EXISTS (
        SELECT *
        FROM project_schema.concerts
        WHERE date_evenement = NEW.date_evenement AND salle = NEW.salle
    ) THEN
        RAISE EXCEPTION 'The concert (DATE : %, SALLE : %) already exist', NEW.date_evenement, NEW.salle;
    END IF;

    RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_concert_exists
    BEFORE INSERT ON project_schema.concerts
    FOR EACH ROW
    EXECUTE FUNCTION check_concert_exists();

--------------------------------------------

CREATE OR REPLACE FUNCTION check_reservation()
    RETURNS TRIGGER AS
$$

DECLARE
    existing_reservation INTEGER;
BEGIN
    IF NEW.nb_tickets < 1 THEN
        RAISE EXCEPTION 'The number of tickets must be greater than 0';
    END IF;

    IF NEW.nb_tickets > 4 THEN
        RAISE EXCEPTION 'The number of tickets must be less than 4';
    END IF;

    IF (
        SELECT SUM(nb_tickets)
        FROM project_schema.reservations
        WHERE date_evenement = NEW.date_evenement AND salle = NEW.salle AND client = NEW.client
    ) + NEW.nb_tickets > 4 THEN
        RAISE EXCEPTION 'The total of tickets must be less or equal than 4';
    END IF;

    IF NEW.nb_tickets > (
        SELECT nb_places_restantes
        FROM project_schema.evenements
        WHERE date_evenement = NEW.date_evenement AND salle = NEW.salle
    ) THEN
        RAISE EXCEPTION 'The number of tickets must be less than or equal to the number of remaining places';
    END IF;

    IF NEW.date_evenement < NOW()::DATE THEN
        RAISE EXCEPTION 'The event is in the past %', NEW.date_evenement;
    END IF;

    IF NOT EXISTS(
        SELECT *
        FROM project_schema.concerts
        WHERE date_evenement = NEW.date_evenement AND salle = NEW.salle
    ) THEN
        RAISE EXCEPTION 'The event with date % and salle % does not exist in the evenements table', NEW.date_evenement, NEW.salle;
    END IF;

    IF NOT EXISTS(
        SELECT *
        FROM project_schema.clients
        WHERE id_client = NEW.client
    ) THEN
        RAISE EXCEPTION 'The client with id % does not exist in the clients table', NEW.client;
    END IF;

    IF EXISTS(
        SELECT *
        FROM project_schema.reservations
        WHERE client = NEW.client AND date_evenement = NEW.date_evenement AND salle <> NEW.salle
    ) THEN
        RAISE EXCEPTION 'The client with id % has already made a reservation for another event with date %', NEW.client, NEW.date_evenement;
    END IF;

    SELECT num_reservation INTO existing_reservation
    FROM project_schema.reservations
    WHERE client = NEW.client AND date_evenement = NEW.date_evenement AND salle = NEW.salle
    LIMIT 1;

    IF existing_reservation IS NOT NULL THEN
        -- Update the existing reservation's nb_tickets
        UPDATE project_schema.reservations
        SET nb_tickets = nb_tickets + NEW.nb_tickets
        WHERE num_reservation = existing_reservation;

        -- Only deduct the additional tickets from the remaining seats
        UPDATE project_schema.evenements
        SET nb_places_restantes = nb_places_restantes - NEW.nb_tickets
        WHERE date_evenement = NEW.date_evenement AND salle = NEW.salle;

        -- Prevent a new row from being inserted
        RETURN NULL;
    ELSE
        -- Assign a new num_reservation if no existing reservation
        NEW.num_reservation := (SELECT COALESCE(MAX(num_reservation), 0) + 1 FROM project_schema.reservations);

        -- Deduct the number of tickets from the remaining seats
        UPDATE project_schema.evenements
        SET nb_places_restantes = nb_places_restantes - NEW.nb_tickets
        WHERE date_evenement = NEW.date_evenement AND salle = NEW.salle;

        RETURN NEW;
    END IF;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_reservation
    BEFORE INSERT ON project_schema.reservations
    FOR EACH ROW
    EXECUTE FUNCTION check_reservation();

--Ajouter la procédure de réservation d’un certain nombre de places pour tous les événements
--d’un festival. Si une des réservations échoue, alors aucune réservation ne sera enregistrée.

CREATE OR REPLACE FUNCTION reserverFestival(add_festival INTEGER, add_nb_tickets INTEGER, add_client INTEGER)
    RETURNS VOID AS $$
DECLARE
    event RECORD;
    reservation_failed BOOLEAN := FALSE;
BEGIN
    FOR event IN
        SELECT *
        FROM project_schema.evenements
        WHERE festival = add_festival
    LOOP
        BEGIN
            PERFORM ajouterReservation(event.salle, event.date_evenement, add_nb_tickets, add_client);
            /*
            EXCEPTION
            WHEN OTHERS THEN
                reservation_failed := TRUE;
            */
        END;
    END LOOP;

    IF reservation_failed THEN
        RAISE EXCEPTION 'One or more reservations failed';
    END IF;
END;
$$ LANGUAGE plpgsql;




--VIEWS----------------------------

CREATE OR REPLACE VIEW festivalsFuturs (nom, date_premier, date_dernier, somme_prix)
AS SELECT f.nom, MIN(e.date_evenement), MAX(e.date_evenement), SUM(e.prix)
FROM project_schema.festivals f
JOIN project_schema.evenements e ON f.id_festival = e.festival
WHERE e.date_evenement >= CURRENT_DATE
GROUP BY f.nom;

CREATE OR REPLACE VIEW reservationsClient (nom_client, nom_evenement, date_evenement, salle_evenement, num_reservation, nb_tickets)
AS SELECT c.nom_utilisateur, e.nom, r.date_evenement, r.salle, r.num_reservation, r.nb_tickets
FROM project_schema.reservations r
JOIN project_schema.evenements e ON r.salle = e.salle AND r.date_evenement = e.date_evenement
JOIN project_schema.clients c ON r.client = c.id_client
ORDER BY c.nom_utilisateur, r.date_evenement, r.salle, r.num_reservation;

--SELECT

SELECT ajouterclient('Mathieu','mathieu@kontu.be','MICHELMICHEL');
SELECT ajouterclient('Alexandre','alexandre@kontu.be','ALEXALEX');

SELECT ajouterfestival('Kontu Festival');

SELECT ajouterartiste('Mathieu','be');
SELECT ajoutersalle('Salle1','Bruxelles',567);
SELECT ajouterevenement(1,'20-11-2024','KontuFestival',45,5600,1);
SELECT ajouterConcert(1, '20-11-2024', '13:40', 1);

SELECT ajouterartiste('Alexandre','be');
SELECT ajoutersalle('Salle2','Bruxelles',567);
SELECT ajouterevenement(2,'21-11-2024','KontuSansBere',30,4500,1);
SELECT ajouterConcert(2, '21-11-2024', '16:40', 2);

SELECT ajouterReservation(1, '20-11-2024', 2, 1);
SELECT ajouterreservation(1, '20-11-2024', 1, 1);
--SELECT ajouterreservation(1, '20-11-2024', 2, 1); --Error - Exception
SELECT reserverfestival(1, 3, 2);
--SELECT reserverFestival(1, 4, 1); --Error - Exception