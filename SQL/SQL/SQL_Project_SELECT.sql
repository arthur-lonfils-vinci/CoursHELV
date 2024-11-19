--SELECT

SELECT ajouterclient('Mathieu','mathieu@kontu.be','MICHELMICHEL');
SELECT ajouterartiste('Mathieu','be');
SELECT ajouterfestival('Kontu Festival');
SELECT ajoutersalle('Salle2','Bruxelles',567);
SELECT ajouterevenement(1,'15-11-2024','KontuFestival',45,5600,1);
SELECT ajouterConcert(1, '15-11-2024', '13:40', 1);
SELECT ajouterReservation(1, '15-11-2024', 2, 1);
SELECT ajouterreservation(1, '15-11-2024', 1, 1);
SELECT ajouterreservation(1, '15-11-2024', 2, 1);