Projet DevOps RED2
---

On a créé un système de gestion de dictionnaire distant clef-valeur scalable, écrit avec le langage de programmation JAVA. Il fait partie de la mouvance NoSQL.

## Fonctionalités fournies
RED2 permet de manipuler des types de données simples: chaînes de caractères et listes.

RED2 expose un service REST pour faire des requêtes. Les requêtes posibles sont selon les standard GET, POST, PUT, DELETE.

   Dans tous les cas, il faut s'authentifier sinon le HTTPCODE reponse est 401 UNAUTHORIZED.
   Dans tous le cas, si la clé n'est pas trouvé HTTPCODE est 200 mais le status est KEY_NOT_FOUND {"status":"KEY_NOT_FOUND"}
   La clé utilisée ne doit pas avoir de caractères spéciaux. 
   
Les fonctionalités implementées sont:

### POST

* Authentifier\
Requête d'authentification avec un mot de passe. Red2 demande un mot-de-passe avant de exécuter d'autres requêtes. Le mot de passe utilisé est passé en argument d'initialisation du logiciel. Sinon par défaut c'est "123456".
Si le mot de passe est correct, le service reponds ACCEPTED.
Exemple: 

```x-sh
curl -X POST -H "Content-Type: application/json" \
-d '{"user":"{nomClient}","password":"123456"}' "localhost:8080/auth"
```

* Ajouter un valeur\
La clé pour garder la valeur. Si la clé tient déjà une valeur, il est écrasé, indépendamment de son type.
Exemple:
```x-sh
curl -X POST -H "Content-Type: application/json" \ 
-d '{"key":"key","info":{"info":["hola","ciao"]},"ttl":2,"typeEviction":"NOEVICTION"}' \ 
"localhost:8080/{nomClient}/set"
```

### GET 
* Demmander la valeur stockée par clé \
Obtenir la valeur de la clé. Si la clé n'existe pas on renvoie le status KEY_NOT_FOUND.
Exemple:
```x-sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" \
-X GET http://localhost:8080/{nomClient}/key/{key}
```

### PUT méthods
* Renomer la clé \
Renomer une clé1 par une autre clé2. Si la clé n'existe pas on renvoie le status KEY_NOT_FOUND, sinon OK.
Exemple:
```x-sh
curl -H "Content-Type: application/json" -X PUT -d '{"key":"1","newKey":"3"}' \ 
http://localhost:8080/{nomClient}/rename/
```

* Augmenter la valeur \
Si la valeur contenue à la clé est un entier on additionne le nombre passé comme paramètre. Si elle n'est pas de type integer, on renvoie le status VALUE_NOT_INT.
Example: 
```x-sh
curl -H "Content-Type: application/json" -X PUT -d '{"key":"1","number":"3"}' \
http://localhost:8080/{nomClient}/increase/
```

* Ajouter dans la list \
Ajouter une valeur dans la liste. On renvoie status OK. 
Exemple:
```x-sh
curl -H "Content-Type: application/json" -X PUT \
-d '{"key":"key","info":{"info":["hola","ciao"]},"ttl":2,"typeEviction":"NOEVICTION"}' \
http://localhost:8080/{nomClient}/addlist/
```

### DELETE
* Supprimer cle \
Supprimer une valeur par clé. On renvoie status OK. 
Exemple:
```x-sh
curl -v -X DELETE http://localhost:8080/{nomClient}/delete/{key}
```

## Objets JSON
### Reponse
* JSON basique.
```javascript
{"status":"KEY_NOT_FOUND"}
```

* JSON information.
```javascript
{"status":"OK","info":["hola","ciao"]}
```

### Requête
* JSON setinformation.
key: cle
info: list de info
ttl: temps pour effacer la valeur
typeEviction: ALLKEYLRU, VOLATILELRU, ALLKEYRANDOM, NOEVICTION, VOLATILETTL
```javascript
{"key":"cle","info":{"info":["hola","ciao"]},"ttl":2,"typeEviction":"NOEVICTION"}'
```
* JSON augmenter valeur.
key: cle
number: nombre pour faire la somme
```javascript
'{"key":"1","number":"3"}'
```
* JSON pour renome clé.
key: cle
newKey: nouvelle cle
```javascript
'{"key":"1","newKey":"3"}'
```
* JSON pour s'authentifier.
user: utilisateur
password: mot-de-passe
```javascript
{"user":"{nomClient}","password":"123456"}
```
## Manual Développeur

### Construire l'application
```x-sh
mvn install
```
### Pour Demarrer de l'application
Le fichier executable se trouve dans le dossier _target_
```x-sh
java -jar red-two-service-1.0.jar
```
### Pour lancer l'application directement dépuis maven
```x-sh
mvn spring-boot:run
```

## Docker
L'image du projet se trouve dans docker.hub. Vous pouvez faire pull du projet
```x-sh
docker pull alarreine/red2:v1
```
L'application expose le port 8080. Alors, pour se connecte dépuis le conteneur il faut lancer avec 
le paremétre -p
```x-sh
docker run -p 8080:8080 alarreine/red2:v1
```
