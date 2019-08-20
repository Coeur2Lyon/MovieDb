/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  23/05/2019 16:38:00                      */
/*==============================================================*/


drop table if exists ACTEUR_REALISATEUR;

drop table if exists CORRESPOND;

drop table if exists EVALUE;

drop table if exists FILM;

drop table if exists GENRE;

drop table if exists JOUE;

drop table if exists ROLE;

drop table if exists UTILISATEUR;

/*==============================================================*/
/* Table : ACTEUR_REALISATEUR                                   */
/*==============================================================*/
create table ACTEUR_REALISATEUR
(
   IdActeurRealisateur  int not null auto_increment,
   Nom                  varchar(50),
   Prenom               varchar(50),
   AnneeNaissance       int,
   primary key (IdActeurRealisateur)
);

/*==============================================================*/
/* Table : CORRESPOND                                           */
/*==============================================================*/
create table CORRESPOND
(
   IdGenre              numeric(8,0) not null,
   IdFilm               int not null,
   primary key (IdGenre, IdFilm)
);

/*==============================================================*/
/* Table : EVALUE                                               */
/*==============================================================*/
create table EVALUE
(
   IdFilm               int not null,
   IdUser               int not null,
   Note                 int,
   primary key (IdFilm, IdUser)
);

/*==============================================================*/
/* Table : FILM                                                 */
/*==============================================================*/
create table FILM
(
   IdFilm               int not null auto_increment,
   IdActeurRealisateur  int not null,
   TitreFr              varchar(50),
   TitreO               varchar(50),
   Scenario             text,
   Datesortie           int,
   primary key (IdFilm)
);

/*==============================================================*/
/* Table : GENRE                                                */
/*==============================================================*/
create table GENRE
(
   IdGenre              numeric(8,0) not null,
   Genre                varchar(50),
   primary key (IdGenre)
);

/*==============================================================*/
/* Table : JOUE                                                 */
/*==============================================================*/
create table JOUE
(
   IdActeurRealisateur  int not null,
   IdFilm               int not null,
   primary key (IdActeurRealisateur, IdFilm)
);

/*==============================================================*/
/* Table : ROLE                                                 */
/*==============================================================*/
create table ROLE
(
   IdRole               int not null,
   Role                 varchar(50),
   primary key (IdRole)
);

/*==============================================================*/
/* Table : UTILISATEUR                                          */
/*==============================================================*/
create table UTILISATEUR
(
   IdUser               int not null auto_increment,
   IdRole               int not null,
   Username             varchar(50),
   Password             varchar(50),
   Email                varchar(150),
   Birthday             date,
   primary key (IdUser)
);

alter table CORRESPOND add constraint FK_CORRESPOND foreign key (IdGenre)
      references GENRE (IdGenre) on delete restrict on update restrict;

alter table CORRESPOND add constraint FK_CORRESPOND2 foreign key (IdFilm)
      references FILM (IdFilm) on delete restrict on update restrict;

alter table EVALUE add constraint FK_EVALUE foreign key (IdFilm)
      references FILM (IdFilm) on delete restrict on update restrict;

alter table EVALUE add constraint FK_EVALUE2 foreign key (IdUser)
      references UTILISATEUR (IdUser) on delete restrict on update restrict;

alter table FILM add constraint FK_REALISE foreign key (IdActeurRealisateur)
      references ACTEUR_REALISATEUR (IdActeurRealisateur) on delete restrict on update restrict;

alter table JOUE add constraint FK_JOUE foreign key (IdActeurRealisateur)
      references ACTEUR_REALISATEUR (IdActeurRealisateur) on delete restrict on update restrict;

alter table JOUE add constraint FK_JOUE2 foreign key (IdFilm)
      references FILM (IdFilm) on delete restrict on update restrict;

alter table UTILISATEUR add constraint FK_ENDOSSSE foreign key (IdRole)
      references ROLE (IdRole) on delete restrict on update restrict;

