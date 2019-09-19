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

drop table if exists REALISE;

drop table if exists ROLE;

drop table if exists UTILISATEUR;


/*==============================================================*/
/* Table : ACTEUR_REALISATEUR                                   */
/*==============================================================*/
create table ACTEUR_REALISATEUR
(
  IdActeurRealisateur int not null auto_increment,
  Nom                 varchar(50) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  Prenom              varchar(50) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  AnneeNaissance      int,
  NationaliteAR       varchar(50) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  CreatedAt           timestamp    default current_timestamp,
  IsDeleted           tinyint(1)   default 0,
  primary key (IdActeurRealisateur)
);

/*==============================================================*/
/* Table : CORRESPOND                                           */
/*==============================================================*/
create table CORRESPOND
(
  IdGenre int not null,
  IdFilm  int           not null,
  primary key (IdGenre, IdFilm)
);

/*==============================================================*/
/* Table : EVALUE                                               */
/*==============================================================*/
create table EVALUE
(
  IdFilm int not null,
  IdUser int not null,
  Note   int,
  primary key (IdFilm, IdUser)
);

/*==============================================================*/
/* Table : FILM                                                 */
/*==============================================================*/
create table FILM
(
  IdFilm       int not null auto_increment,
  TitreFr      varchar(50) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  TitreO       varchar(50) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  Scenario     text charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  AnneeSortie  int,
  NationaliteF varchar(50) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  CreatedAt    timestamp    default current_timestamp,
  IsDeleted    tinyint(1)   default 0,
  primary key (IdFilm)
);

/*==============================================================*/
/* Table : GENRE                                                */
/*==============================================================*/
create table GENRE
(
  IdGenre int not null,
  Genre   varchar(50),
  primary key (IdGenre)
);

/*==============================================================*/
/* Table : JOUE                                                 */
/*==============================================================*/
create table JOUE
(
  IdActeurRealisateur int not null,
  IdFilm              int not null,
  primary key (IdActeurRealisateur, IdFilm)
);

/*==============================================================*/
/* Table : REALISE                                                 */
/*==============================================================*/
create table REALISE
(
  IdActeurRealisateur int not null,
  IdFilm              int not null,
  primary key (IdActeurRealisateur, IdFilm)
);

/*==============================================================*/
/* Table : ROLE                                                 */
/*==============================================================*/
create table ROLE
(
  IdRole int not null,
  Role   varchar(50),
  primary key (IdRole)
);

/*==============================================================*/
/* Table : UTILISATEUR                                          */
/*==============================================================*/
create table UTILISATEUR
(
  IdUser    int not null auto_increment,
  IdRole    int not null,
  Username  varchar(50) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  Password  varchar(50) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  Email     varchar(150) charset utf8mb4
  COLLATE utf8mb4_unicode_ci,
  Birthday  date,
  CreatedAt timestamp    default current_timestamp,
  IsDeleted tinyint(1)   default 0,
  primary key (IdUser)
);


alter table CORRESPOND
  add constraint FK_CORRESPOND foreign key (IdGenre)
references GENRE (IdGenre)
  on delete cascade
  on update restrict;

alter table CORRESPOND
  add constraint FK_CORRESPOND2 foreign key (IdFilm)
references FILM (IdFilm)
  on delete cascade
  on update restrict;

alter table EVALUE
  add constraint FK_EVALUE foreign key (IdFilm)
references FILM (IdFilm)
  on delete cascade
  on update restrict;

alter table EVALUE
  add constraint FK_EVALUE2 foreign key (IdUser)
references UTILISATEUR (IdUser)
  on delete cascade
  on update restrict;

alter table JOUE
  add constraint FK_JOUE foreign key (IdActeurRealisateur)
references ACTEUR_REALISATEUR (IdActeurRealisateur)
  on delete cascade
  on update restrict;

alter table JOUE
  add constraint FK_JOUE2 foreign key (IdFilm)
references FILM (IdFilm)
  on delete cascade
  on update restrict;

alter table REALISE
  add constraint FK_REALISE foreign key (IdActeurRealisateur)
references ACTEUR_REALISATEUR (IdActeurRealisateur)
  on delete cascade
  on update restrict;

alter table REALISE
  add constraint FK_REALISE2 foreign key (IdFilm)
references FILM (IdFilm)
  on delete cascade
  on update restrict;

alter table UTILISATEUR
  add constraint FK_ENDOSSSE foreign key (IdRole)
references ROLE (IdRole)
  on delete cascade 
  on update restrict;


/*==============================================================*/
/* Insertion des données                                       */
/*==============================================================*/


INSERT INTO ROLE (IdRole, Role)
VALUES (1, 'administrateur'),
       (2, 'utilisateur');

INSERT INTO UTILISATEUR (IdRole, Username, Password, Email, Birthday)
VALUES (1, 'JackAdmin', 'password', 'jackadmin@gmail.com', '1980-05-24'),
       (2, 'PolUtilisateur', 'password', 'polutilisateur@hotmail.com', '1996-09-30');


INSERT INTO ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR)
VALUES ('EASTWOOD', 'Clint', 1930, 'US'),
       ('FINSHER', 'David', 1962, 'US'),
       ('MELVILLE', 'Jean-Pierre', 1917, 'FR'),
       ('CHABROL', 'Claude', 1930, 'FR'),
       ('BELMONDO', 'Jean-Paul', 1933, 'FR'),
       ('GODARD', 'Jean-Luc', 1930, 'FR'),
       ('NORTON', 'Edward', 1969, 'US'),
       ('PITT', 'Brad', 1963, 'US'),
       ('VILLERET', 'Jacques', 1951, 'FR');

INSERT INTO ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR,IsDeleted)
VALUES('-','-',2019,'-',1);


INSERT INTO FILM (TitreFr, TitreO, Scenario, AnneeSortie, NationaliteF)
VALUES ('Impitoyable',
        'Unforgiven',
        'Un ancien cowboy se voit proposer une mission périlleuse par un de ses anciens équipiers',
        1992,
        'US'),
       ('Fight Club',
        'Fight Club',
        'Le film démarre sur le plan du personnage principal (Edward Norton) à qui on a enfoncé un pistolet dans la bouche et dont on entend la voix en monologue qui se remémore comment il en est arrivé là.',
        1999,
        'US'),
       ('Aîné des Ferchaux(l\')',
        'Aîné des Ferchaux(l\')',
        'Contraint de renoncer à une carrière de boxeur qu''il avait un temps envisagée, un jeune homme, Michel, se fait engager comme secrétaire et garde du corps d''un vieux banquier, Dieudonné Ferchaux, contraint de quitter précipitamment la France pour fuir la justice. À New York puis à La Nouvelle-Orléans, les deux hommes apprendront à mieux se connaître tout en jouant au chat et à la souris.',
        1963,
        'FR'),
       ('A bout de souffle',
        'A bout de souffle',
        'Michel Poiccard, jeune voyou insolent, vole une voiture à Marseille pour se rendre à Paris. En route, il tue un gendarme motocycliste qui voulait le verbaliser ',
        1960,
        'FR'),
       ('Landru',
        'Landru',
        'Ce film retrace la vie du tueur en série Henri Désiré Landru. Pendant la Première Guerre mondiale, il séduisait des femmes seules et riches. Ayant réussi à leur faire signer une procuration, il les assassinait dans sa maison de campagne puis faisait disparaître leurs corps en les brûlant dans un fourneau.',
        1963,
        'FR');

INSERT INTO GENRE (IdGenre, Genre)
VALUES (0, 'Policier'),
       (1, 'Thriller'),
       (2, 'Fantastique/SF'),
       (3, 'Drame'),
       (4, 'Biopic'),
       (5, 'Action'),
       (6, 'Horreur'),
       (7, 'Comédie'),
       (8, 'Western'),
       (9, 'Aventure');

INSERT INTO JOUE (IdActeurRealisateur, IdFilm)
VALUES (1, 1),
       (3, 5),
       (5, 3),
       (5, 4),
       (7, 2),
       (8, 2);

INSERT INTO REALISE (IdActeurRealisateur, IdFilm)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (6, 4),
       (4, 5);


INSERT CORRESPOND (IdGenre, IdFilm)
VALUES (8, 1),
       (5, 1),
       (2, 2),
       (5, 2),
       (0, 3),
       (3, 3),
       (9, 3),
       (0, 4),
       (3, 4),
       (3, 5),
       (4, 5);