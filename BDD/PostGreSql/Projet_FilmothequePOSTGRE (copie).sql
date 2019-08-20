/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  09/08/2019 11:38:01                      */
/*==============================================================*/


drop table ACTEUR_REALISATEUR;

drop table CORRESPOND;

drop table EVALUE;

drop table FILM;

drop table GENRE;

drop table JOUE;

drop table REALISE;

drop table ROLE;

drop table UTILISATEUR;

/*==============================================================*/
/* Table : ACTEUR_REALISATEUR                                   */
/*==============================================================*/
create table ACTEUR_REALISATEUR (
   ID_ACTEUR_REALISATEUR SERIAL not null,
   NOM                  VARCHAR(50)          null,
   PRENOM               VARCHAR(50)          null,
   ANNEE_NAISSANCE      INT4                 null,
   constraint PK_ACTEUR_REALISATEUR primary key (ID_ACTEUR_REALISATEUR)
);

/*==============================================================*/
/* Table : CORRESPOND                                           */
/*==============================================================*/
create table CORRESPOND (
   ID_GENRE             NUMERIC(8,0)         not null,
   ID_FILM              INT4                 not null,
   constraint PK_CORRESPOND primary key (ID_GENRE, ID_FILM)
);

/*==============================================================*/
/* Table : EVALUE                                               */
/*==============================================================*/
create table EVALUE (
   ID_FILM              INT4                 not null,
   ID_USER              INT4                 not null,
   NOTE                 INT4                 null,
   constraint PK_EVALUE primary key (ID_FILM, ID_USER)
);

/*==============================================================*/
/* Table : FILM                                                 */
/*==============================================================*/
create table FILM (
   ID_FILM              SERIAL not null,
   TITRE_FR             VARCHAR(50)          null,
   TITRE_O              VARCHAR(50)          null,
   SCENARIO             TEXT                 null,
   DATESORTIE           INT4                 null,
   constraint PK_FILM primary key (ID_FILM)
);

/*==============================================================*/
/* Table : GENRE                                                */
/*==============================================================*/
create table GENRE (
   ID_GENRE             NUMERIC(8,0)         not null,
   GENRE                VARCHAR(50)          null,
   constraint PK_GENRE primary key (ID_GENRE)
);

/*==============================================================*/
/* Table : JOUE                                                 */
/*==============================================================*/
create table JOUE (
   ID_ACTEUR_REALISATEUR INT4                 not null,
   ID_FILM              INT4                 not null,
   constraint PK_JOUE primary key (ID_ACTEUR_REALISATEUR, ID_FILM)
);

/*==============================================================*/
/* Table : REALISE                                              */
/*==============================================================*/
create table REALISE (
   ID_ACTEUR_REALISATEUR INT4                 not null,
   ID_FILM              INT4                 not null,
   constraint PK_REALISE primary key (ID_ACTEUR_REALISATEUR, ID_FILM)
);

/*==============================================================*/
/* Table : ROLE                                                 */
/*==============================================================*/
create table ROLE (
   ID_ROLE              INT4                 not null,
   ROLE                 VARCHAR(50)          null,
   constraint PK_ROLE primary key (ID_ROLE)
);

/*==============================================================*/
/* Table : UTILISATEUR                                          */
/*==============================================================*/
create table UTILISATEUR (
   ID_USER              SERIAL not null,
   ID_ROLE              INT4                 not null,
   USERNAME             VARCHAR(50)          null,
   PASSWORD             VARCHAR(50)          null,
   EMAIL                VARCHAR(150)         null,
   BIRTHDAY             DATE                 null,
   constraint PK_UTILISATEUR primary key (ID_USER)
);

alter table CORRESPOND
   add constraint FK_CORRESPO_CORRESPON_GENRE foreign key (ID_GENRE)
      references GENRE (ID_GENRE)
      on delete restrict on update restrict;

alter table CORRESPOND
   add constraint FK_CORRESPO_CORRESPON_FILM foreign key (ID_FILM)
      references FILM (ID_FILM)
      on delete restrict on update restrict;

alter table EVALUE
   add constraint FK_EVALUE_EVALUE_FILM foreign key (ID_FILM)
      references FILM (ID_FILM)
      on delete restrict on update restrict;

alter table EVALUE
   add constraint FK_EVALUE_EVALUE2_UTILISAT foreign key (ID_USER)
      references UTILISATEUR (ID_USER)
      on delete restrict on update restrict;

alter table JOUE
   add constraint FK_JOUE_JOUE_ACTEUR_R foreign key (ID_ACTEUR_REALISATEUR)
      references ACTEUR_REALISATEUR (ID_ACTEUR_REALISATEUR)
      on delete restrict on update restrict;

alter table JOUE
   add constraint FK_JOUE_JOUE2_FILM foreign key (ID_FILM)
      references FILM (ID_FILM)
      on delete restrict on update restrict;

alter table REALISE
   add constraint FK_REALISE_REALISE_ACTEUR_R foreign key (ID_ACTEUR_REALISATEUR)
      references ACTEUR_REALISATEUR (ID_ACTEUR_REALISATEUR)
      on delete restrict on update restrict;

alter table REALISE
   add constraint FK_REALISE_REALISE2_FILM foreign key (ID_FILM)
      references FILM (ID_FILM)
      on delete restrict on update restrict;

alter table UTILISATEUR
   add constraint FK_UTILISAT_ENDOSSSE_ROLE foreign key (ID_ROLE)
      references ROLE (ID_ROLE)
      on delete restrict on update restrict;

