FROM mysql:latest

LABEL maintainer="vincent_fillon@hotmail.com"
# Certains utilisent: "MAINTAINER Vincent Fillon <vincent_fillon@hotmail.com>" mais la commande est Deprecated depuis 2017.

ENV MYSQL_ROOT_PASSWORD moviedb
ENV MYSQL_DATABASE moviedb
ENV MYSQL_USERS vincent
ENV MYSQL_PASSWORD password

ADD MySQL/mysql_charset.cnf /etc/mysql/conf.d/mysql_charset.cnf
ADD MySQL/moviedb.sql /docker-entrypoint-initdb.d

EXPOSE 3306

