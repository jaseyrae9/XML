# XML


### Pokretanje bez docker-a

#### Pokretanje Spring Boot glavnog backend-a
* Uraditi maven update projekta za sve mikroservise
* Pokrenuti eureka-server
* Pokrenuti ostale mikroservise

#### Pokretanje agentske backend aplikacije
<pre>
  pipenv shell
  cd XML_agent
  python manage.py migrate
  python manage.py runserver
</pre>


Ukoliko se želi promeniti port dodati:
<pre>
  python manage.py runserver ip-adresa:port
</pre>

#### Pokretanje frontend aplikacija
Pokretanje frontend aplikacija opisano je u okviru readme dokumenata u podfolderima sa frontend aplikacijama.


### Pokretanje sa dokerom

* Pozicionirati se u folder XML/Backend/main-backend-app
* Iskreirati slike svih mikroservisa pomocu mvn package i docker image built -t naziv-kontenjera .
* Kreirati volume za bazu pomocu  docker volume create pgdata
* Pokrenuti docker-compose up
