## Projet de gestion des automobiles avec Java swing

## Result
![Screeshort result](https://drive.google.com/file/d/1NuTKAyUP54A5THijZv8p1n-wQNtHvz3r/view?usp=share_link)


## Base de donnees Sql Server 

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
      - ./db_scripts:/scripts/
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
