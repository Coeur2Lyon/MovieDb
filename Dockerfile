FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD PASSWORD
ENV MYSQL_DATABASE filmotheque
ENV MYSQL_USERS vincent
ENV MYSQL_PASSWORD password
ADD BDD/MySQL/Projet_Filmotheque.sql /docker-entrypoint-initdb.d
EXPOSE 3306
