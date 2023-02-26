IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'automobile')
    BEGIN
        CREATE DATABASE automobile;
    END;
GO


USE automobile;
GO

-- 
-- 
CREATE TABLE auto_owners (
            OWNER_ID INT PRIMARY KEY IDENTITY (1, 1),
            FIRST_NAME VARCHAR(100) NOT NULL,
            LAST_NAME VARCHAR(100) NOT NULL,
            PHONE VARCHAR(100) NOT NULL UNIQUE,
            CNI VARCHAR(100) NOT NULL UNIQUE,
            DOB DATE NOT NULL
        );
    

CREATE TABLE auto_cars (
            CARS_ID INT PRIMARY KEY IDENTITY (1, 1),
            MATRICULE VARCHAR(100) NOT NULL UNIQUE,
            MARQUE VARCHAR(100) NOT NULL,
            MODEL VARCHAR(100) NOT NULL,
            TRANSMISSION VARCHAR(100) NOT NULL,
            ANNEE INT NOT NULL,
	    OWNER_ID int FOREIGN KEY REFERENCES auto_owners (OWNER_ID) ON DELETE CASCADE
        );


-- 
--
INSERT INTO auto_owners (FIRST_NAME, LAST_NAME, PHONE, CNI, DOB) VALUES
('Aminata', 'Diallo', '+221 77 123 45 67', '2234567890123', '1995-05-20'),
('Abdoulaye', 'Ndiaye', '+221 77 567 89 01', '1987654321123', '1987-03-12'),
('Fatou', 'Sow', '+221 77 246 80 24', '2111222333412', '1979-12-06'),
('Mamadou', 'Diop', '+221 77 369 85 21', '1444555666712', '1988-08-14'),
('Awa', 'Kane', '+221 77 987 65 43', '2777888999012', '1992-11-29'),
('Ousmane', 'Sylla', '+221 77 432 10 98', '1123456789123', '1983-02-18'),
('Mariam', 'Traore', '+221 77 111 11 11', '2876543210123', '1990-07-03'),
('Cheikh', 'Tall', '+221 77 222 22 22', '1173456789123', '1975-09-22'),
('Khadim', 'Gueye', '+221 77 333 33 33', '1567891230123', '1999-06-12'),
('Ndeye', 'Fall', '+221 77 444 44 44', '2891234560123', '2002-09-17');




INSERT INTO auto_cars (MATRICULE, MARQUE, MODEL, TRANSMISSION, ANNEE, OWNER_ID) VALUES
('ABC123', 'Toyota', 'Corolla', 'Manuelle', 2015, 1),
('DEF456', 'Honda', 'Civic', 'Automatique', 2019, 2),
('GHI789', 'Ford', 'Mustang', 'Manuelle', 2022, 3),
('JKL012', 'Chevrolet', 'Camaro', 'Automatique', 2018, 4),
('MNO345', 'Jeep', 'Wrangler', 'Manuelle', 2020, 5),
('PQR678', 'Nissan', 'Sentra', 'Automatique', 2017, 6),
('STU901', 'Mazda', 'CX-5', 'Automatique', 2021, 7),
('VWX234', 'Kia', 'Sportage', 'Automatique', 2016, 8),
('YZA567', 'Toyota', 'Camry', 'Automatique', 2017, 2),
('BCD890', 'Honda', 'Accord', 'Manuelle', 2014, 4),
('EFG123', 'Mitsubishi', 'Lancer', 'Manuelle', 2013, 1),
('HIJ456', 'Kia', 'Rio', 'Automatique', 2018, 9),
('KLM789', 'Toyota', 'Yaris', 'Automatique', 2015, 3),
('NOP012', 'Hyundai', 'Elantra', 'Manuelle', 2012, 10);
