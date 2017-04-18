# Projet DEVOPS

## Construire l'application
```x-sh
mvn install
```

## Pour Demarrer de l'application
Le fichier executable se trouve dans le dossier _target_
```x-sh
jar -tar red-two-service-1.0.jar
```
## Pour lancer l'application directement dépuis maven
```x-sh
mvn spring-boot:run
```

## Docker
L'image du projet se trouve dans docker.hub. Vous pouvez faire pull du projet
```x-sh
docker pull alarreine/red2:v1
```
L'application offre expose le port 8080. Alors, pour se connecte dépuis le conteneur il faut lancer avec le paremétre -p
```x-sh
docker run -p 8080:8080 alarreine/red2:v1
```
## Vous pouvez tester directament dépuis une console avec *curl*
### POST méthods
* Auth \
```x-sh
curl -X POST -H "Content-Type: application/json" -d '{"user":"agustin","password":"123456"}' "localhost:8080/auth"
```

* Ajouter information \
```x-sh
curl -X POST -H "Content-Type: application/json" -d '{"key":"key","info":{"info":["hola","ciao"]},"ttl":2,"typeEviction":"NOEVICTION"}' "localhost:8080/agustin/set"
```


### GET méthods
* Demmander info par clé \
```x-sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/agustin/key/{key}
```

### PUT méthods
* Renomer un clé \
```x-sh
curl -H "Content-Type: application/json" -X PUT -d '{"key":"1","newKey":"3"}' http://localhost:8080/{nomClient}/rename/
```

* Augmenter la valeur \
```x-sh
curl -H "Content-Type: application/json" -X PUT -d '{"key":"1","number":"3"}' http://localhost:8080/{nomClient}/increase/
```

* Ajouter information dans la list \
```x-sh
curl -H "Content-Type: application/json" -X PUT -d '{"key":"key","info":{"info":["hola","ciao"]},"ttl":2,"typeEviction":"NOEVICTION"}' http://localhost:8080/{nomClient}/addlist/
```

### DELETE
* Supprimer cle \
```x-sh
curl -v -X DELETE http://localhost:8080/{nomClient}/delete/{key}
```

