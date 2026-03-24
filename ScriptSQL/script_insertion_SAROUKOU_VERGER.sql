-- =========================================================================
-- 1. Nouveaux Administrateurs
-- =========================================================================

INSERT INTO Administrateur (prenom, nom, identifiantConnexion, motDePasse) 
VALUES ('Maxime', 'Blachard', 'admin', 'admin');


-- =========================================================================
-- 2. Nouveaux Étudiants (Promos différentes)
-- =========================================================================

INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Soudé', 'Loris', 'lsoudé', 'lsoudé2028', 2028); 
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Dib', 'Djamel', 'ddib', 'ddib2028', 2028);
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Dupont', 'Alice', 'adupont', 'adupont2028', 2028);
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Lefebvre', 'Thomas', 'tlefebvre', 'tlefebvre2029', 2029);
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Garcia', 'Inès', 'igarcia', 'igarcia2029', 2029);
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Martin', 'Lucas', 'lmartin', 'lmartin2028', 2028);
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Bernard', 'Emma', 'ebernard', 'ebernard2028', 2028);
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Dubois', 'Hugo', 'hdubois', 'hdubois2029', 2029);
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Roux', 'Chloé', 'croux', 'croux2029', 2029);
INSERT INTO Etudiant (nom, prenom, identifiantConnexion, motDePasse, promo) 
VALUES ('Moreau', 'Léo', 'lmoreau', 'lmoreau2028', 2028);


-- =========================================================================
-- 3. Nouvelles Campagnes (Illustration du Cycle de Vie)
-- =========================================================================

-- ID 1 : Campagne "Ouvertes"
INSERT INTO Campagne (dateDebut, dateFin, nbChoixMax, statut) 
VALUES (TO_DATE('2026-03-01', 'YYYY-MM-DD'), TO_DATE('2026-03-31', 'YYYY-MM-DD'), 3, 'Ouvertes');

-- ID 2 : Campagne "En préparation" 
INSERT INTO Campagne (dateDebut, dateFin, nbChoixMax, statut) 
VALUES (TO_DATE('2026-09-01', 'YYYY-MM-DD'), TO_DATE('2026-10-15', 'YYYY-MM-DD'), 4, 'En préparation');

-- ID 3 : Campagne "Archivées"
INSERT INTO Campagne (dateDebut, dateFin, nbChoixMax, statut) 
VALUES (TO_DATE('2025-01-01', 'YYYY-MM-DD'), TO_DATE('2025-06-01', 'YYYY-MM-DD'), 2, 'Archivées');


-- =========================================================================
-- 4. Nouvelles Dominantes
-- =========================================================================

INSERT INTO Dominante (nom) VALUES ('Cuisine');
INSERT INTO Dominante (nom) VALUES ('Chaudronnerie');
INSERT INTO Dominante (nom) VALUES ('Informatique');


-- =========================================================================
-- 5. Nouvelles Sessions 
-- =========================================================================

-- Session 1 : Cuisine
INSERT INTO SessionEtu (dateHeureDebut, dateHeureFin, capacite, idCampagne, idDominante) 
VALUES (TO_DATE('2026-05-15 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2026-05-15 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 1, 1);

-- Session 2 : Chaudronnerie
INSERT INTO SessionEtu (dateHeureDebut, dateHeureFin, capacite, idCampagne, idDominante) 
VALUES (TO_DATE('2026-05-16 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2026-05-16 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 15, 1, 2);

-- Session 3 : Informatique
INSERT INTO SessionEtu (dateHeureDebut, dateHeureFin, capacite, idCampagne, idDominante) 
VALUES (TO_DATE('2026-05-17 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2026-05-17 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), 10, 1, 3);

-- Session 1 : Cuisine avancée
INSERT INTO SessionEtu (dateHeureDebut, dateHeureFin, capacite, idCampagne, idDominante) 
VALUES (TO_DATE('2026-11-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2026-11-10 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 20, 1, 1);


-- =========================================================================
-- 6. Nouvelles Inscriptions (Simulation de vœux multiples)
-- =========================================================================

INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2026-04-01 09:01:34', 'YYYY-MM-DD HH24:MI:SS'), 1, 'En attente', 1, 1);

INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2026-04-11 09:32:30', 'YYYY-MM-DD HH24:MI:SS'), 1, 'En attente', 1, 2);

INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2026-04-02 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 'En attente', 3, 3);
INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2026-04-02 10:16:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 'En attente', 2, 3);

INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2026-04-03 14:20:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Refusée', 1, 4);

INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2026-04-03 14:22:00', 'YYYY-MM-DD HH24:MI:SS'), 2, 'Validée', 3, 4);

INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2026-04-05 08:05:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Validée', 2, 5);

INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2026-04-10 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 'En attente', 3, 6);

INSERT INTO Inscription (dateRealisee, ordrePreference, statut, idSession, idEtudiant) 
VALUES (TO_DATE('2025-02-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Validée', 4, 7);

-- Valide les insertion et MAJ la BDD
COMMIT;