FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD moviedb
ENV MYSQL_DATABASE moviedb
ENV MYSQL_USERS vincent
ENV MYSQL_PASSWORD password

MAINTAINER Vincent Fillon <vincent_fillon@hotmail.com>

COPY ./mysqld_charset.cnf /etc/mysql/conf.d/mysqld_charset.cnf
COPY ./mysql-utf8mb4.sql /docker-entrypoint-initdb.d

ADD BDD/MySQL/Projet_Filmotheque.sql /docker-entrypoint-initdb.d
EXPOSE 3306
