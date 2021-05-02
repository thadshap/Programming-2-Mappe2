CREATE TABLE IF NOT EXISTS Patient (
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NULL,
    generalPractitioner VARCHAR(20),
    diagonosis VARCHAR (100),
    PRIMARY KEY (socialSecurityNumber));
