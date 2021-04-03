use Lavalle;

DELETE FROM ProdottoBar;
INSERT INTO `ProdottoBar` (`CodProdotto`, `NomeProdotto`, `Costo`)
VALUES
('1', 'Caffe', '0.50'),
('2', 'Cornetto', '1.00'),
('3', 'Granita', '1.50');

DELETE FROM Turno;
INSERT INTO Turno (CodTurno, FasciaOraria)
VALUES
(1, "9-12"),
(2, "12-15"),
(3, "15-18");

DELETE FROM Posto;
INSERT INTO Posto (CodPosto, NumeroSdraio, CostoTotale)
VALUES
(1, 1, 5),
(2, 3, 12),
(3, 2, 8),
(4, 3, 12),
(5, 4, 14),
(6, 1, 5),
(7, 3, 12),
(8, 2, 8),
(9, 1, 5),
(10, 2, 8);

DELETE FROM Utente;
INSERT INTO `Utente` (`CodUtente`, `Nome`, `Cognome`, `EMail`, `IdPermesso`, `Password`)
VALUES
(0, 'Ospite', '', 'none', '0', 'none'),
(1, 'Ammi', 'Nistratore', 'root@root.it', '4', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2'), -- Password: root
(2, 'Davide', 'Lavalle', 'davide.lavalle.45@gmail.com', '0', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'), -- Password: 123
(3, 'Irene', 'Siragusa', 'irene@gm.com', '0', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'), -- Password: 123
(4, 'Bagnino', 'Bagnino', 'bagnino@gm.com', '1', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'), -- Password: 123
(5, 'Cassiere', 'Cassiere', 'cassiere@gm.com', '2', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'), -- Password: 123
(6, 'Biglietteria', 'Biglietteria', 'biglietteria@gm.com', '3', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'); -- Password: 123