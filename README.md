## Projet de gestion des automobiles avec Java swing

## Base de donnees Sql Server dockerize

> docker-compose.yml

```yml
version: '3.8'

services:
  mysqlserver:
    image: mcr.microsoft.com/mssql/server:2022-latest
    container_name: mysqlserver
    ports:
      - 11433:1433
    environment:
      ACCEPT_EULA: 'Y'
      SA_PASSWORD: 'your_password'
    volumes:
      - ./scripts:/scripts/
    command:
      - /bin/bash
      - -c
      - |
        # Launch MSSQL and send to background
        /opt/mssql/bin/sqlservr &
        sleep 30
        # Run every script in /scripts
        for foo in /scripts/*.sql
          do /opt/mssql-tools/bin/sqlcmd -U sa -P $$SA_PASSWORD -l 30 -e -i $$foo
        done
        sleep infinity
    networks:
      - msnet

networks:
  msnet:
    driver: bridge
```

> script

```sql
IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'automobile')
    BEGIN
        CREATE DATABASE automobile;
    END;
GO


USE automobile;
GO

--
--

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='TableName' and xtype='users')
    BEGIN
        CREATE TABLE TableName (
            USER_ID INT PRIMARY KEY IDENTITY (1, 1),
            FIRST_NAME VARCHAR(100) NOT NULL,
            LAST_NAME VARCHAR(100) NOT NULL,
            PHONE VARCHAR(100) NOT NULL,
            CNI VARCHAR(100) NOT NULL,
            DOB DATETIME NOT NULL
        );
    END;
GO

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='TableName' and xtype='cars')
    BEGIN
        CREATE TABLE TableName (
            CARS_Id INT PRIMARY KEY IDENTITY (1, 1),
            MATRICULE VARCHAR(100) NOT NULL,
            MARQUE VARCHAR(100) NOT NULL,
            MODEL VARCHAR(100) NOT NULL,
            TRANSMISSION INT NOT NULL,
            ANNEE YEAR NOT NULL,
            USER_ID int  FOREIGN KEY REFERENCES automobile.users (USER_ID)
        );
    END;
GO
```
