#!/usr/bin/env bash

# cd /
# mkdir -p java
# cd java
# mkdir -p src
# cd src

# yum install apt

# apt install openjdk-11-jdk-headless
# apt install default-jdk
# apt install openjdk-8-jdk-headless
# apt install ecj

yum install java-1.8.0-openjdk
yum install mysql -y
yum install docker -y

cd mysql

docker build -t vincent/mysql .
docker run --name moviedb -p3306:3306 -d vincent/mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
docker start moviedb




