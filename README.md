# JavaEE_Spring_SOA
Mise en oeuvre de la SOA pour le nouveau système d’information de la bibliothèque d’une grande ville.


################################################
##########Informations globales  ###############
################################################

Une fois que vous avez décompressé le fichier zip, vous y trouverez trois répertoirs:
-biblio-client: contient le client SOAP et l'IHM;
-biblio-ws: contient le server SOAP;
-sql: contient le scripte de créations des bases dedonnées dev et test.

Avant de commencer le déploiement, vous devez éxecuter dans votre base de données le script SQL présent dans le répertoire sql.
Celui va crée l'architecture de la base de données nécessire au bon fonctionnement de l'application.

################################################
########## Déploiement du server  ##############
################################################


Dans un premier temps, il convient de mettre à jour les données de connexion à la base
de données MySQL dans le fichier biblio-ws/biblio-ws-webapp/src/main/java/resources/application-dev.properties pour l'environnement de développement

les données url et port sont à changer en fonction de votre environnement.

spring.datasource.url=jdbc:mysql://{url}:{port}/javaee?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=

Et biblio-ws/biblio-ws-business/src/main/test/resources/application-test.properties pour l'environnement de test.

les données {url} et {port} sont à changer en fonction de votre environnement.

spring.datasource.url=jdbc:mysql://{url}:{port}/javaee_test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=

Une fois cela fait, rendez-vous dans le répertoire biblio-ws et tapez
mvn install

!!!!! Si jamais une erreur survient lors de la lecture du fichier XSD, réessayer d'installer en tapant rajoutant l'option: -Djavax.xml.accessExternalSchema=all !!!!!!!

Les tests unitaires vont se lancer et Maven va télécharger les dépendances.
Lorsque cela est terminé, rendez-vous dans le répertoire biblio-ws/biblio-ws-webapp et tapez:
mvn spring-boot:run 

Le server tomcat embarqué va se lancer et l'application va se déployer sur le port 8080 du localhost. Attention à ce que ce port ne soit pas déjà utilisé.




################################################
########## Déploiement du client  ##############
################################################

Maintenant, rendez-vous dans le répertoire biblio-client.

Il n'y a aucune configuration à faire, simplement tapez:
mvn install

Une fois la commande terminée, rendez-vous dans le répertoire biblio-client/biblio-client-webapp et tapez:
mvn spring-boot:run


L'application va se lancer sur le port 9090 du localhost. Rendez-vous sur http://localhost:9090/ afin de vous rendre sur la page d'accueil de l'application.


################################################
########## Déploiement du Batch  ###############
################################################

Pour le déploiement du batch, il suffit de taper : java -jar lenomdujar.jar.


Bonne navigation ! 