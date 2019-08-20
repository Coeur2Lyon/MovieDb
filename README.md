Procédure d'installation de l'environnement de développement (et base de données test)

1)Installer Docker

2)Positionnez-vous dans le repertoire racine contenant le Dockerfile (par défaut MovieDb)

3)Builder l'image (à partir du Dockerfile):

docker build -t vincent/mysql .     
// on nomme le container souhaité ("vincent/mysql" est un exemple) et on indique un "." pour spécifier que le Dockerfile est dans le répertoire courant.

4)Créer un conteneur pour cette image:

docker run --name projet_filmotheque -p3306:3306 -d vincent/mysql


5)si le conteneur se crée mais ne démarre pas, démarrez-le:
docker start projet_filmotheque )

6)Exécution/entrer dans le conteneur :

docker exec -it projet_filmotheque /bin/bash

-Dans le conteneur (#root@.../#):
7)Connectez-vous à la base en tant que root:

mysql -uroot -pPASSWORD

Dans mysql (mysql>):
showdatabases; 
use filmotheque;
show tables;

