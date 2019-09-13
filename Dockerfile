FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD moviedb
ENV MYSQL_DATABASE moviedb
ENV MYSQL_USERS vincent
ENV MYSQL_PASSWORD password

MAINTAINER Vincent Fillon <vincent_fillon@hotmail.com>

ADD BDD/MySQL/Projet_Filmotheque.sql /docker-entrypoint-initdb.d
EXPOSE 3306
