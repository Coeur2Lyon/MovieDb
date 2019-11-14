#!/usr/bin/env bash
 #Script exécuté par le Vagrantfile
 #But: Installer le gestionnaire de paquet Chocolatey qui lui, a pour but d'installer MySQL


 Write-Host -message "Ca marche!"

 Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
 Set-TimeZone 'Eastern Standard Time'


 choco install mysql --params "/port:3306 /dataLocation:/mySql/moviedb.sql"

 choco install jre8

 choco install git

choco list


