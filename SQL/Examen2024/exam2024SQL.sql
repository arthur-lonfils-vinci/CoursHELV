DROP SCHEMA IF EXISTS examen CASCADE;
CREATE SCHEMA examen;

CREATE TABLE examen.formations (
                                   id_formation SERIAL PRIMARY KEY NOT NULL,
                                   niveau INTEGER NOT NULL CHECK ( niveau > 0 OR niveau < 6 ),
                                   fdate DATE NOT NULL CHECK (fdate < current_date),
                                   max_participants INTEGER NOT NULL CHECK ( max_participants > 0),
                                   inscription_cloturee BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE examen.participants (
                                     id_participant SERIAL PRIMARY KEY NOT NULL,
                                     nom TEXT NOT NULL CHECK ( nom > ' ' ),
                                     prenom TEXT NOT NULL CHECK ( prenom > ' ' ),
                                     nationalite TEXT NOT NULL CHECK ( nationalite > ' ' )
);

CREATE TABLE examen.inscriptions (
                                     formation SERIAL NOT NULL REFERENCES examen.formations(id_formation),
                                     participant SERIAL NOT NULL REFERENCES examen.participants(id_participant),
                                     PRIMARY KEY (formation, participant)
);


CREATE OR REPLACE FUNCTION examen.formationInscription(_participant INTEGER, _formation INTEGER)
RETURNS INTEGER AS $$
    DECLARE nb_participants INTEGER;
BEGIN
INSERT INTO examen.inscriptions(formation, participant)  VALUES (_formation, _participant);
SELECT count(DISTINCT i.participant) INTO nb_participants
FROM examen.formations f
         JOIN examen.inscriptions i on f.id_formation = i.formation
WHERE f.niveau = (SELECT DISTINCT f1.niveau
                  FROM examen.formations f1
                  WHERE f1.id_formation = _formation);
RETURN nb_participants;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION examen.checkInscription()
RETURNS TRIGGER AS $$
    DECLARE max_niveau_part INTEGER;
        niveau_form INTEGER;
        nbr_participant INTEGER;
BEGIN
        IF (SELECT 1 FROM examen.formations f WHERE f.id_formation = NEW.formation AND  f.inscription_cloturee IS TRUE)
            THEN RAISE EXCEPTION 'EXCEPTION: inscription clôturée';
END IF;

        IF (SELECT 1 FROM examen.formations f WHERE f.id_formation = NEW.formation AND f.fdate < current_date)
            THEN RAISE EXCEPTION 'EXCEPTION: Date passée';
END IF;

SELECT f.niveau FROM examen.formations f WHERE f.id_formation = NEW.formation INTO niveau_form;
SELECT COALESCE((SELECT f.niveau FROM examen.formations f, examen.inscriptions i WHERE i.formation = f.id_formation AND i.participant = NEW.participant ORDER BY f.niveau DESC LIMIT 1), NULL) INTO max_niveau_part;

IF (niveau_form >= 2 AND (max_niveau_part IS NULL OR max_niveau_part + 1 < niveau_form ))
            THEN RAISE EXCEPTION 'EXCEPTION: Niveau trop bas';
END IF;

SELECT COUNT(i.participant) FROM examen.inscriptions i WHERE i.formation = NEW.formation INTO nbr_participant;
IF (nbr_participant >= (SELECT f.max_participants FROM examen.formations f WHERE f.id_formation = NEW.formation))
            THEN UPDATE examen.formations SET inscription_cloturee = TRUE WHERE examen.formations.id_formation = NEW.formation;
END IF;

RETURN NEW;
end;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER triggerInscription
    BEFORE INSERT ON examen.inscriptions
    FOR EACH ROW EXECUTE PROCEDURE examen.checkInscription();


-------------------------------------------


-- Insert data into examen.formations
INSERT INTO examen.formations (niveau, fdate, max_participants)
VALUES
    (1, CURRENT_DATE + INTERVAL '10 days', 10),
    (2, CURRENT_DATE + INTERVAL '20 days', 15),
    (3, CURRENT_DATE + INTERVAL '30 days', 20);

-- Insert data into examen.participants
INSERT INTO examen.participants (nom, prenom, nationalite)
VALUES
    ('Doe', 'John', 'American'),
    ('Smith', 'Jane', 'British'),
    ('Brown', 'Charlie', 'Canadian');

-- Valid INSERT INTO examen.inscriptions (should work)
INSERT INTO examen.inscriptions (formation, participant)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 2),
    (2, 3),
    (3, 3);

-- Example Exception 1: Inscription Clôturée
-- Close registration for a formation
UPDATE examen.formations SET inscription_cloturee = TRUE WHERE id_formation = 1;

-- This should raise an exception: 'EXCEPTION: inscription clôturée'
INSERT INTO examen.inscriptions (formation, participant)
VALUES (1, 2);

-- Example Exception 2: Date Passed
-- Set a past date for a formation
UPDATE examen.formations SET fdate = CURRENT_DATE - INTERVAL '5 days' WHERE id_formation = 2;

-- This should raise an exception: 'EXCEPTION: Date passée'
INSERT INTO examen.inscriptions (formation, participant)
VALUES (2, 1);

-- Example Exception 3: Niveau trop bas
-- Create a participant with a lower level than required
INSERT INTO examen.participants (nom, prenom, nationalite)
VALUES ('Low', 'Level', 'French');

SELECT COALESCE ((SELECT f.niveau AS level
                  FROM examen.formations f, examen.inscriptions i
                  WHERE i.formation = f.id_formation
                    AND i.participant = 4
                  ORDER BY f.niveau DESC
                 LIMIT 1), null ) AS result;

-- This should raise an exception: 'EXCEPTION: Niveau trop bas'
INSERT INTO examen.inscriptions (formation, participant)
VALUES (3, 4);

-- Example Exception 4: Max participants reached
-- Fill the max participants for a formation
DO $$
BEGIN
FOR i IN 1..10 LOOP
                INSERT INTO examen.participants (nom, prenom, nationalite)
                VALUES ('Participant', i::TEXT, 'Random');

INSERT INTO examen.inscriptions (formation, participant)
VALUES (1, (SELECT max(id_participant) FROM examen.participants));
END LOOP;
END $$;

-- This should raise an exception as the formation is now full
INSERT INTO examen.inscriptions (formation, participant)
VALUES (1, 3);





