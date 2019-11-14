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

ENV['VAGRANT_DEFAULT_PROVIDER'] = 'docker'

Vagrant.configure("2") do |config|

config.vm.define "my-little-container" do |m|

m.vm.provider :docker do |d|
d.name = 'my-little-container'
d.build_dir = "."
d.cmd = ["ping", "-c 51", "127.0.0.1"]
d.remains_running = true
d.vagrant_machine = "dockerhostvm"
d.vagrant_vagrantfile = "./DockerHostVagrantfile"
end
end
end













docker build -t [nom_Image] .




-Arrêter les conteneurs:
docker stop $(docker ps -a -q)

-Supprimer les conteneurs:
docker rm $(docker ps -a -q)


Supprimer toutes les images non utilisées:
docker rmi $(docker images -q)


Sortir de la BDD et des conteneur: exit

Afficher tous les conteneurs:
docker ps -a
