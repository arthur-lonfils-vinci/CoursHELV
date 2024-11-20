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