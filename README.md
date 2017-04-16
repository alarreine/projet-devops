# projet-devops

# Construire l'application
`mvn install`

# Demarrer de l'application
 se mettre dans le repértoire target
`jar -tar red-two-service-1.0-SNAPSHOT.jar`

# Pour lancer l'application 
 mvn spring-boot:run

# Pour tester avec `curl`
## POST méthods
* Renomer un clé \
`curl -H "Content-Type: application/json" -X POST -d '{"key":"1","newKey":"3"}' http://localhost:8080/rename`

## GET méthods
* Demmander info par clé \
`curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/key/{key}`