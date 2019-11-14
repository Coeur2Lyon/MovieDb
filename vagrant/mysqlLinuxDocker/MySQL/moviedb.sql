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
  IdFilm  int not null,
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


INSERT INTO ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR, CreatedAt)
VALUES ('EASTWOOD', 'Clint', 1930, 'US', '2019-09-18 13:12:06'),
  ('FINSHER', 'David', 1962, 'US', '2019-09-18 13:12:06'),
  ('MELVILLE', 'Jean-Pierre', 1917, 'FR', '2019-09-18 13:12:06'),
  ('CHABROL', 'Claude', 1930, 'FR', '2019-09-18 13:12:06'),
  ('BELMONDO', 'Jean-Paul', 1933, 'FR', '2019-09-18 13:12:06'),
  ('GODARD', 'Jean-Luc', 1930, 'FR', '2019-09-18 13:12:06'),
  ('NORTON', 'Edward', 1969, 'US', '2019-09-18 13:12:06'),
  ('PITT', 'Brad', 1963, 'US', '2019-09-18 13:12:06'),
  ('VILLERET', 'Jacques', 1951, 'FR', '2019-09-18 13:12:06');

INSERT INTO ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR, IsDeleted)
VALUES ('-', '-', 2019, '-', 1);

INSERT INTO moviedb.ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR, CreatedAt)
VALUES ('CHABBAT', 'Alain ', 1958, 'Fr', '2019-09-20 07:54:25'),
  ('COLUCCI', 'Michel', 1944, 'Fr', '2019-09-20 07:54:25'),
  ('ZIDI', 'Claude', 1934, 'Fr', '2019-09-20 07:54:25'),
  ('FREEMAN', 'Morgan', 1944, 'US', '2019-10-08 09:59:25'),
  ('SWANK', 'Hilary', 1974, 'US', '2019-10-08 09:59:25'),
  ('DEPARDIEU', 'Gérard', 1948, 'FR', '2019-10-08 10:30:25'),
  ('RENO', 'Jean', 1948, 'FR', '2019-10-08 10:30:25'),
  ('BESSON', 'Luc', 1959, 'FR', '2019-10-08 11:20:20'),
  ('ARQUETTE', 'Rosanna', 1959, 'US', '2019-10-08 11:24:00'),
  ('BARR', 'Jean-Marc', 1690, 'FR-US', '2019-10-08 11:25:00'),
  ('BERRI', 'Claude', 1934, 'FR', '2019-10-08 11:39:00'),
  ('BACRI', 'Jean-Pierre', 1951, 'FR', '2019-10-08 11:39:00'),
  ('HACKMAN', 'Gene', 1930, 'US', '2019-10-08 11:50:00'),
  ('VEBER', 'Francis', 1937, 'FR', '2019-10-09 07:55:00'),
  ('MARCHAL', 'Olivier', 1958, 'FR', '2019-10-21 13:55:30'),
  ('LANVIN', 'Gérard', 1950, 'FR', '2019-10-21 13:56:38'),
  ('KARYO', 'Tchéky', 1953, 'FR', '2019-10-21 13:57:36'),
  ('ASTIER', 'Lionel', 1953, 'FR', '2019-10-21 13:58:16'),
  ('AUTEUIL', 'Daniel', 1950, 'FR', '2019-10-21 13:59:13'),
  ('BLIER', 'Bernard', 1916, 'FR', '2019-10-21 14:02:14'),
  ('LAUTNER', 'Georges', 1926, 'FR', '2019-10-21 14:02:46'),
  ('SIMON', 'Michel ', 1895, 'FR', '2019-10-21 14:03:39'),
  ('CARNÉ', 'Michel ', 1906, 'FR', '2019-10-21 14:05:00'),
  ('DELON', 'Alain', 1935, 'FR', '2019-10-21 14:21:24'),
  ('PORTMAN', 'Nathalie', 1981, 'IS-US', '2019-10-21 14:24:26'),
  ('VERNEUIL', 'Henri', 1920, 'FR', '2019-10-21 14:31:39'),
  ('VENTURA', 'Lino', 1919, 'IT', '2019-10-21 14:35:19'),
  ('NOIRET', 'Philippe', 1930, 'FR', '2019-10-21 14:37:00'),
  ('LHERMITTE', 'Thierry', 1952, 'FR', '2019-10-21 14:38:09'),
  ('La troupe du Splendid', ' ', 1974, 'FR', '2019-10-21 14:40:15'),
  ('BLANC', 'Michel', 1952, 'FR', '2019-10-21 14:42:04'),
  ('GABIN', 'Jean', 1904, 'FR', '2019-10-21 14:49:10'),
  ('PRESTIA', 'Jo', 1960, 'FR-IT', '2019-10-21 14:53:34'),
  ('OLDMAN', 'Gary', 1958, 'UK', '2019-10-21 14:58:49'),
  ('PARILLAUD', 'Anne', 1960, 'FR', '2019-10-21 15:00:09'),
  ('DURET', 'Marc', 1957, 'FR', '2019-10-21 15:02:05'),
  ('ANGLADE', 'Jean-Hugues', 1955, 'FR', '2019-10-21 15:05:01'),
  ('COPPOLA', 'Fancis Ford', 1939, 'US', '2019-10-22 08:36:57'),
  ('SCORCESE', 'Martin', 1942, 'US-IT', '2019-10-22 08:38:19'),
  ('DE NIRO', 'Robert', 1943, 'US', '2019-10-22 08:40:36'),
  ('KEITEL', 'Harvey', 1939, 'US', '2019-10-22 08:41:46'),
  ('PESCI', 'Joe', 1943, 'US', '2019-10-22 08:47:35'),
  ('LIOTTA', 'Ray', 1954, 'US', '2019-10-22 08:48:15'),
  ('STONE', 'Oliver', 1946, 'US', '2019-10-22 08:50:00'),
  ('LEONE', 'Sergio', 1929, 'IT', '2019-10-22 08:50:31'),
  ('VAN CLEEF', 'Lee', 1925, 'US', '2019-10-22 08:51:15'),
  ('NOLAN', 'Christopher', 1970, 'US', '2019-10-22 09:30:54'),
  ('SHEEN', 'Martin', 1940, 'US', '2019-10-22 09:35:20'),
  ('DOUGLAS', 'Micheal', 1944, 'US', '2019-10-22 09:40:32'),
  ('SHEEN', 'Charlie', 1965, 'US', '2019-10-22 09:41:45'),
  ('HOPKINS', 'Anthony', 1937, 'UK-US', '2019-10-22 09:46:26'),
  ('HARRIS', 'Ed', 1950, 'US', '2019-10-22 09:47:08'),
  ('FARRELL', 'Collin', 1976, 'IR', '2019-10-22 09:51:14'),
  ('ANNAUD', 'Jean-Jacques', 1943, 'FR', '2019-10-22 09:54:50'),
  ('CONNERY', 'Sean', 1930, 'SC', '2019-10-22 09:55:49'),
  ('SLATER', 'Christian', 1969, 'US', '2019-10-22 09:56:43'),
  ('LONSDALE', 'Michael', 1931, 'FR', '2019-10-22 09:57:33'),
  ('POLLACK', 'Sydney', 1934, 'US', '2019-10-22 10:02:19'),
  ('CRUISE', 'Tom', 1962, 'US', '2019-10-22 10:03:41'),
  ('PACINO', 'Al', 1940, 'US', '2019-10-22 10:06:05'),
  ('VINCENT', 'Franck', 1937, 'US', '2019-10-22 10:22:33'),
  ('JACKSON', 'Samuel L.', 1948, 'US', '2019-10-22 10:23:30'),
  ('FOSTER', 'Jodie', 1962, 'US', '2019-10-22 10:27:31'),
  ('STONE', 'Sharon', 1958, 'US', '2019-10-22 10:29:32'),
  ('DEPP', 'Johnny', 1963, 'US', '2019-10-22 14:42:52'),
  ('HOPPER', 'Denis', 1936, 'US', '2019-10-22 14:45:23'),
  ('DUVALL', 'Robert', 1931, 'US', '2019-10-22 14:46:03'),
  ('FISHBURNE', 'Laurence', 1961, 'US', '2019-10-22 14:46:40'),
  ('FORD', 'Harrison', 1942, 'US', '2019-10-22 14:48:03'),
  ('BRANDO', 'Marlon', 1924, 'US', '2019-10-22 14:50:02'),
  ('KILMER', 'Val', 1959, 'US', '2019-10-22 14:56:26'),
  ('SCOTT', 'Tony', 1944, 'UK-US', '2019-10-22 14:58:03'),
  ('MANN', 'Micheal', 1995, 'US', '2019-10-22 15:01:10'),
  ('SIZEMORE', 'Tom', 1943, 'US', '2019-10-22 15:06:08'),
  ('TREJO', 'Danny', 1944, 'US', '2019-10-22 15:16:16'),
  ('SCHUMACHER', 'Joel', 1993, 'US', '2019-10-22 15:28:09'),
  ('FERNANDEL (CONTANDIN)', 'Fernand', 1903, 'FR', '2019-10-23 07:13:18'),
  ('PAGNOL', 'Marcel', 1895, 'FR', '2019-10-23 08:46:44'),
  ('PAGNOL', 'Jacqueline', 1920, 'FR', '2019-10-23 08:46:44'),
  ('CHARPIN', 'Fernand', 1887, 'FR', '2019-10-23 09:00:30'),
  ('KORDA', 'Alexander', 1893, 'UK', '2019-10-23 09:17:03'),
  ('ALLEGRET', 'Marc', 1900, 'FR', '2019-10-23 09:17:59'),
  ('RAIMU (MURAIRE))', 'Jules', 1883, 'FR', '2019-10-23 09:20:26'),
  ('TOURNEUR', 'Maurice', 1876, 'FR', '2019-10-23 09:41:24'),
  ('LAW', 'Jude', 1972, 'UK', '2019-10-23 09:54:46'),
  ('WALKEN', 'Christopher', 1943, 'US', '2019-10-23 10:04:54'),
  ('MONTAND', 'Yves', 1921, 'IT-FR', '2019-10-23 10:21:33'),
  ('BOURVIL (RAIMBOURG)', 'André', 1917, 'FR', '2019-10-23 10:23:27'),
  ('DE FUNES', 'Louis', 1914, 'FR', '2019-10-23 10:24:02'),
  ('CAAN', 'James', 1940, 'US', '2019-10-23 12:04:00');

INSERT INTO FILM (TitreFr, TitreO, Scenario, AnneeSortie, NationaliteF, CreatedAt)
VALUES ('Impitoyable', 'Unforgiven',
        'Kansas 1880. William Munny, redoutable hors-la-loi reconverti dans l''élevage va, à la demande d''un jeune tueur, reprendre du service pour venger une prostituée défigurée par un cow-boy sadique.',
        1992, 'US', '2019-09-18 13:12:06'),
  ('Fight Club', 'Fight Club', 'Le film démarre sur le plan du personnage principal (Edward Norton) à qui on a enfoncé un pistolet dans la bouche et dont on entend la voix en monologue qui se remémore comment il en est arrivé là.', 1999, 'US', '2019-09-18 13:12:06'),
  ('Aîné des Ferchaux(l'')', 'Aîné des Ferchaux(l'')', 'Contraint de renoncer à une carrière de boxeur qu''il avait un temps envisagée, un jeune homme, Michel, se fait engager comme secrétaire et garde du corps d''un vieux banquier, Dieudonné Ferchaux, contraint de quitter précipitamment la France pour fuir la justice. À New York puis à La Nouvelle-Orléans, les deux hommes apprendront à mieux se connaître tout en jouant au chat et à la souris.', 1963, 'FR', '2019-09-18 13:12:06'),
  ('A bout de souffle', 'A bout de souffle', 'Michel Poiccard, jeune voyou insolent, vole une voiture à Marseille pour se rendre à Paris. En route, il tue un gendarme motocycliste qui voulait le verbaliser ', 1960, 'FR', '2019-09-18 13:12:06'),
  ('Landru', 'Bluebeard', 'Ce film retrace la vie du tueur en série Henri Désiré Landru. Pendant la Première Guerre mondiale, il séduisait des femmes seules et riches. Ayant réussi à leur faire signer une procuration, il les assassinait dans sa maison de campagne puis faisait disparaître leurs corps en les brûlant dans un fourneau.', 1963, 'FR', '2019-09-18 13:12:06'),
  ('Astérix & Obélix: Mission Cléopâtre', 'Asterix & Obelix: Mission Cleopatra', 'Numerobis, architecte se voit confier le chantier du palais de César par Cléopâtre', 2002, 'FR', '2019-09-18 13:15:44'),
  ('Million Dollar Baby', 'Million Dollar Baby', 'Rejeté depuis longtemps par sa fille, l''entraîneur Frankie Dunn s''est replié sur lui-même et vit dans un désert affectif, en évitant toute relation qui pourrait accroître sa douleur et sa culpabilité.
Le jour où Maggie Fitzgerald, 31 ans, pousse la porte de son gymnase à la recherche d''un coach, elle n''amène pas seulement avec elle sa jeunesse et sa force, mais aussi une
histoire jalonnée d''épreuves et une exigence, vitale et urgente : monter sur le ring, entraînée par Frankie, et enfin concrétiser le rêve d''une vie.
Après avoir repoussé plusieurs fois sa demande, Frankie se laisse convaincre par l''inflexible détermination de la jeune femme. Une relation mouvementée, tour à tour stimulante et exaspérante, se noue entre eux, au fil de laquelle Maggie et l''entraîneur se découvrent une communauté d''esprit et une complicité inattendues...', 1994, 'US', '2019-09-18 13:35:06'),
  ('Didier', 'Didier', 'Jean-Pierre, agent de football, a accepté de garder Didier, le chien d''Annabelle, une amie journaliste. Au cours de la nuit, le labrador prend une apparence humaine, mais reste psychologiquement toujours un chien. Aux prises avec Richard, le patron du club de football pour lequel il est agent, Jean-Pierre n''avait pas besoin d''un problème supplémentaire, en plus de ses soucis avec ses joueurs vedettes, d''abord Baco, puis Fabrice blessé juste une semaine avant un match très important contre le PSG. Didier va révéler certaines dispositions footballistiques et l''aider à remplacer les joueurs blessés. De plus, il finira même par réconcilier Jean-Pierre et sa petite amie Maria.', 1997, 'FR', '2019-10-02 06:53:33'),
  ('Tchao Pantin', 'So long, stooge', 'À Paris dans le 18e arrondissement, le pompiste de nuit Lambert (Coluche), alcoolique et dépressif, croise la route d''un jeune juif arabe, petit trafiquant sans envergure : Youseff Bensoussan (Richard Anconina), venu se réfugier dans sa station-service afin d''éviter une patrouille de police. Ils deviennent rapidement amis, mais finissent par se brouiller, Bensoussan refusant de suivre les conseils, délivrés maladroitement, de Lambert qui veut le protéger. Une nuit, alors qu''il est poursuivi et tente à nouveau de trouver refuge dans sa station-service, Bensoussan, victime d''un règlement de comptes, est abattu sous les yeux de Lambert.Lambert est en réalité un ancien flic, hanté jusqu''à l''alcoolisme par la mort de son fils (par overdose) qu''il a été incapable d''éviter, malgré ou peut-être à cause de ses méthodes musclées. Il décide alors de venger le meurtre du jeune Bensoussan en traquant les commanditaires de son assassinat : deux trafiquants de drogue de Barbès. Il reçoit l''aide de Lola (Agnès Soral), une jeune punk ayant eu une relation avec Bensoussan, qui veut le sauver de son désespoir.', 1983, 'FR', '2019-10-04 15:12:41'),
  ('Banzaï', 'Banzaï', 'Michel Bernardin est employé à Planète Assistance, société qui aide les Français en difficulté à l''étranger. Sa fiancée Isabelle va quitter son emploi d''hôtesse de l''air pour passer plus de temps avec lui grâce à son amie Sophia qui lui a trouvé un emploi dans son agence de voyages. Mais Isabelle est obligée de rester encore quelques temps à son poste pour quelques vols et va le cacher à son fiancé.', 1983, 'FR', '2019-10-04 15:23:19'),
  ('Inspecteur Labavure', 'Inspector Blunder', 'Fils d''un héros de la police abattu par Pierrot le fou, Michel Clément (Coluche) est loin d''égaler son père Jules. Encouragé par sa mère (Marthe Villalonga) à suivre les traces paternelles, il obtient de justesse son diplôme d''inspecteur de police (par indulgence du jury) et se retrouve stagiaire à la PJPP (Police Judiciaire de la Préfecture de Police). Là, sa maladresse lui cause de nombreux problèmes, mais malgré cela, Michel rêve d''arrêter l''ennemi public no 1, l''impitoyable Roger Morzini (Gérard Depardieu). Celui-ci, trouvant son visage bien trop connu, subit une opération de chirurgie esthétique.

Une journaliste ambitieuse, Marie-Anne Prossant (Dominique Lavanant), fille d''un directeur de journal, nargue Morzini en réclamant une interview de lui, ce qui lui vaut d''être mise sous protection policière. Michel Clément assurera officiellement et visiblement cette tâche pour détourner l''attention, tandis que des policiers plus compétents assureront cette sécurité cachés, attendant que Morzini s''attaque à elle pour l''arrêter.

Morzini, rendu méconnaissable par son opération, se fait passer pour un écrivain auteur de romans policiers dénommé Antoine Collard et se lie dʼamitié avec Michel, lequel, en toute naïveté, va lui révéler tous les secrets de la police pour ne pas être capturé.

Prétextant faire des recherches pour son prochain roman, Morzini réussit à abuser de la naïveté de Michel pour approcher Marie-Anne Prossant et lʼenlever, au nez et à la barbe des policiers, qui surveillent pendant ce temps Michel dans sa voiture. Morzini, qui a emmené son otage dans une maison abandonnée, réclame une rançon au père de Marie-Anne Prossant. Les policiers arrêtent Michel, soupçonné dʼêtre le complice de Morzini. Ils sʼarrangent pour que celui-ci sʼéchappe du commissariat afin de le suivre, à lʼaide dʼun émetteur quʼils ont dissimulé dans la doublure de son costume. Mais Michel se rendra compte de la présence de lʼémetteur et donnera ses habits à un clochard, ce qui fera échouer la filature.

Avec quelques amis, Michel prend les choses en main et enlève la mère de Morzini. Celle-ci le tuyaute sur lʼendroit où se trouve probablement son fils. Michel sʼy rend et confronte Morzini, qui refuse de relâcher son otage. Michel décide alors dʼattaquer la maison à lʼaide dʼune pelle mécanique et libère lui-même Marie-Anne Prossant.', 1980, 'FR', '2019-10-04 15:23:19'),
  ('Le Grand Bleu', 'The Big Blue', 'Jacques Mayol et Enzo Molinari se connaissent depuis l''enfance, dans les années 1960. Ils ont grandi ensemble en Grèce et partagent depuis toujours leur passion pour la mer. Mais à la suite de l''accident au cours d''une plongée et du décès de son père, Jacques revient en France. Vingt ans ont passé, mais la rivalité entre les deux hommes existe toujours. Le championnat du monde d''apnée No Limit à Taormina en Sicile à la fin des années 1980 est l''occasion pour les deux hommes de se retrouver et d''explorer un monde que personne ne connaît mieux qu''eux.

 En Grèce, en 1965, les amis de Jacques lui montrent une pièce d''or sous l''eau. Il se prépare à plonger mais Enzo est plus rapide. Le lendemain, le père de Jacques se noie lors d''une expérience en plongée.

 En Sicile en 1988, Enzo est demandé en renfort pour sauver un plongeur coincé sous une épave. Il plonge dans l''eau sans respirateur et parvient à sauver le plongeur. Il demande ensuite à son frère Roberto de trouver Jacques. Au Pérou, Johanna Baker croise Jacques Mayol et, avec le Dr Lawrence, regarde une expérience quand Jacques plonge dans le lac glacé sans respirateur. La jeune femme lui apporte du café, Jacques revient plus tard lui offrir un cadeau afin de la remercier. De retour en France, Enzo trouve Jacques et le convie au championnat à Taormina. A New York, Johanna découvre que l''appartement qu''elle partage avec sa colocataire a été cambriolé. Elle apprend par le docteur Lawrence que Jacques est en Sicile et décide de s''y rendre.

 Sur place, Enzo et Jacques se retrouvent, le premier réprimande le réceptionniste de l''hôtel. Tandis qu''ils bavardent, Johanna les trouve et ils sympathisent ensemble. Lors d''une soirée, les deux hommes décident de voir qui tient le plus longtemps sous l''eau. Ils finissent sur un brancard. Johanna s''occupe de Jacques. Le lendemain, Enzo se voit interdit d''aller plonger et malgré les conseils de son médecin, il se lance. Un soir, le trio libère un dauphin enfermé dans un bassin. A l''entraînement d''apnée, Jacques plonge à plus de 110 m de profondeur. Dans la soirée, il entretient une liaison avec Johanna avec qui il passe la nuit. En pleine nuit, il sort, plonge dans la mer et nage avec un dauphin jusqu''au petit matin. Johanna comprend qu''il préfère passer du temps sous l''eau qu''être avec des femmes et décide de partir à New York. Une sorte d''ascenseur emmène Enzo, Jacques et un Belge à bord pour l''entraînement. Jacques et Johanna se retrouvent. Enzo fait 115 mètres, 4 minutes et 50 secondes en apnée. Jacques fait 120 mètres. La veille de la compétition, Johanna veut parler d''avenir avec Jacques mais il ne semble pas partager son opinion. Le jour de la compétition, Enzo fait tout pour battre Jacques, il va descendre même le plus profond possible, même à y risquer sa vie. Le docteur Lawrence veut arrêter la compétition car il estime que c''est trop dangereux mais Enzo refuse de l''écouter. Il reste plus longtemps et plus profond. Une fois remonté, il se meurt et demande à son ami de le laisser mourir au fond de la mer. Dévasté par la mort de son ami, il se mure dans le mutisme. Johanna apprend qu''elle est enceinte. Elle va voir Jacques qui fait une petite crise, elle le suit et tente de le raisonner. Elle lui avoue même sa grossesse mais il plonge quand même et - à la profondeur maximale qu''autorise la compétition - il quitte la lumière pour rejoindre un dauphin dans la noirceur des profondeurs.', 1988, 'FR', '2019-10-09 05:50:00'),
  ('Le dîner de cons', 'The dinner game', 'Tous les mercredis, Pierre Brochant et ses amis organisent un dîner où chacun doit amener un con. Celui qui a trouvé le plus spectaculaire est declaré vainqueur. Ce soir, Brochant exulte, il est sur d''avoir trouvé la perle rare, un con de classe mondiale: Francois Pignon, comptable au ministère des Finances et passionné de modèles réduits en allumettes. Ce qu''il ignore c''est que Pignon est passe maître dans l''art de déclencher des catastrophes.', 1998, 'FR', '2019-10-09 07:32:31'),
  ('La Chèvre', 'Knock on Wood', 'La fille du grand PDG Bens, très malchanceuse, se fait enlever alors qu''elle est en vacances au Mexique. Pour la retrouver, son père, conseillé par son psychologue d''entreprise, utilise un de ses employés aussi malchanceux qu''elle, François Perrin, comptable, dans l''espoir qu''il lui arrive les mêmes malheurs qu''à sa fille et qu''il la retrouve. Le détective privé Campana, chargé de l''enquête malgré son scepticisme, devra donc faire équipe avec Perrin, ce qui ne sera pas de tout repos car non seulement il devra en rattraper les innombrables bourdes mais en plus il devra bien souvent partager sa poisse et même, de temps à autre, en subir seul les conséquences, au point que son cartésianisme initial n''en sortira pas intact.

', 1981, 'FR', '2019-10-09 07:42:10'),
  ('Tais-toi!', 'Ruby & Quentin', 'Quentin de Montargis se fait arrêter et se retrouve en prison. Ruby, un criminel, recherché par la police, est emprisonné après avoir volé l''argent de son ancien patron Vogel. Il souhaite venger la mort de la femme qu''il aimait et que Vogel, son mari, a tuée.

En prison, le criminel ne parle plus à personne. Apprenant que Quentin a rendu fous plusieurs détenus pendant les dernières semaines, le commissaire Vernet voit là la façon de faire parler Ruby. Mais Ruby tente de se suicider, Quentin pensant avoir trouvé un ami en fait de même pour le retrouver à l''hôpital, puis le suit jusqu''à l''asile de fous, où il contacte un ancien collègue du bâtiment, Martineau. À la demande de Quentin, Martineau le fait évader avec Ruby qui s’apprêtait à quitter l''asile d''une façon plus discrète. Quentin et Ruby se retrouvent alors en pyjama d''hôpital au milieu de la ville, avec les hommes de Vogel à leur poursuite. Quentin aide Ruby à leur échapper et à voler une voiture, mais malgré cela Ruby soupçonne Quentin de travailler pour le commissaire Vernet. Ruby découvre la vraie nature de Quentin : un con certes, mais quelqu''un de très attachant et doté de vraies qualités humaines.

Ruby et Quentin arrivent à s''introduire chez Vogel. Cependant, Vogel blesse par balle Ruby, et c''est finalement Quentin qui tue Vogel pour protéger Ruby. La police arrive sur les lieux quelques instants après.', 2002, 'FR', '2019-10-09 07:42:10'),
  ('Les Ripoux', 'My new partner', 'René, vieux flic adepte des arrangements "à l''amiable" avec les petits truands, se voit adjoindre comme coéquipier François, un jeunôt frais émoulu de l''école de police. Avec l''aide de son amie Simone, il entreprend de convertir le rigoureux novice à ses méthodes, et pour cela commence par le mettre en relation avec une jolie call-girl, Natacha...', 1984, 'FR', '2019-10-21 12:45:07'),
  ('MR 73', 'The Last Deadly Mission', 'Un tueur en série ensanglante Marseille. Louis Schneider, flic au SRPJ, mène l''enquête malgré l''alcool et les fantômes de son passé. Le passé resurgit aussi pour Justine. 25 ans plus tôt, ses parents ont été sauvagement assassinés par Charles Subra. Schneider l''avait alors arrêté. Mais aujourd''hui, par le jeu des remises de peine et pour bonne conduite, Subra sort de prison. Cette libération anticipée va alors réunir Schneider et Justine, deux êtres qui tentent de survivre au drame de leur vie.', 2008, 'FR', '2019-10-21 13:05:23'),
  ('Les Lyonnais ', 'A Gang Story', 'De sa jeunesse passée dans la misère d’un camp de gitans, Edmond Vidal, dit Momon, a retenu le sens de la famille, une loyauté sans faille, et la fierté de ses origines. Il a surtout conservé l’amitié de Serge Suttel. L’ami d’enfance avec qui il a découvert la prison à cause d’un stupide vol de cerises. Avec lui, inexorablement il a plongé dans le Grand Banditisme, et connu l’apogée du GANG DES LYONNAIS, l’équipe qu’ils ont formée ensemble et qui a fait d’eux les plus célèbres braqueurs du début des années soixante dix. Leur irrésistible ascension prend fin en 1974, lors d’une arrestation spectaculaire.
Aujourd’hui à l’approche de la soixantaine, Momon tente d’oublier cette période de sa vie. Sa rédemption, il l’a trouvée en se retirant des "affaires". En prenant soin de Janou, son épouse, qui a tant souffert à l’époque et de ses enfants et petits enfants, tous respectueux, devant cet homme aux valeurs simples et universelles, lucide et pétri d’humanité. A l’inverse de Serge Suttel, qui malgré le temps n’a rien renié de son itinéraire...', 2011, 'FR', '2019-10-21 13:06:56'),
  ('Le Quai des brumes ', 'Port of Shadows', 'Un déserteur de l''armée tente d''échapper la France. Mais il décide de protéger Nelly, terrorisée par son tuteur Zabel. Une suite de meurtres va prendre place.', 1938, 'FR', '2019-10-21 13:16:57'),
  ('Les Tontons Flingueurs', 'Monsieur Gangster', 'Propriétaire d''une petite usine de tracteurs, Fernand Naudin mène une vie tranquille et sans histoire quand un télégramme l''appelle à Paris. Il arrive à temps pour recueillir le dernier soupir d''un ami de jeunesse, Louis dit « le Mexicain », qui lui confie ses affaires louches en même temps que la garde de sa fille Patricia. Et les ennuis commencent...
', 1963, 'FR', '2019-10-21 13:19:29'),
  ('36 Quai des Orfèvres', '36', 'Paris. Depuis plusieurs mois, un gang de braqueurs opère en toute impunité avec une rare violence. Le directeur de la PJ, Robert Mancini a été parfaitement clair avec ses deux lieutenants les plus directs, Léo Vrinks, patron de la BRI (Brigade de recherche et d''intervention), et Denis Klein, patron de la BRB (Brigade de répression du banditisme) : celui qui fera tomber ce gang le remplacera à son poste de grand " patron " du 36, quai des Orfèvres. La lutte est ouverte entre ces deux grands flics, autrefois amis, qu''aujourd''hui tout sépare : leurs vies, leurs méthodes, leurs équipes et une femme, Camille Vrinks...', 2004, 'FR', '2019-10-21 13:25:35'),
  ('Mélodie en sous-sol', 'Any number can win', 'A peine sorti de prison, Charles, un truand à la retraite, refuse de s''acheter une bonne conduite. Ce dernier décide de monter un gros casse: le cambriolage du casino Palm Beach à Cannes. Pour mener à bien ce projet, Charles aura à ses côtés Francis, un jeune voyou sans scrupules et Louis, beau-frère de celui-ci. Chacun aura un rôle bien défini : Charles surveillera les salles du casino, Francis utilisera ses charmes pour visiter les coulisses du lieu et Louis sera le chauffeur des deux compères.', 1963, 'FR', '2019-10-21 13:33:15'),
  ('Le Placard', 'The Closet', 'François Pignon, un homme au costume sombre et à l''allure discrète, est comptable dans une usine de caoutchouc, dont le secteur privilégié est le préservatif.
Il est sur le point d''être licencié, lorsque sur les conseils de Belon, son voisin d''immeuble, il propage la rumeur selon laquelle il est homosexuel. Suite à ce faux coming out, les cadres de la direction décident de garder Pignon pour des raisons "politiquement correctes".
Celui-ci passe brusquement pour un marginal, bien qu''il n''ait rien changé à son comportement. C''est le regard des autres qui va s''en trouver modifié.', 2001, 'FR', '2019-10-21 13:34:27'),
  ('Léon', 'Léon: The professional', 'Un tueur à gages répondant au nom de Léon prend sous son aile Mathilda, une petite fille de douze ans, seule rescapée du massacre de sa famille. Bientôt, Léon va faire de Mathilda une "nettoyeuse", comme lui. Et Mathilda pourra venger son petit frère...', 1994, 'FR', '2019-10-21 13:35:25'),
  ('Nikita', 'La femme Nikita', 'Le braquage d''une pharmacie par une bande de junkies en manque de drogue tourne mal : une fusillade cause la mort de plusieurs personnes dont un policier, abbatu par la jeune Nikita. Condamnée à la prison à perpétuité, celle-ci fait bientôt la rencontre de Bob, un homme mystérieux qui contraint la jeune femme à travailler secrètement pour le gouvernement.
Après quelques rébellions lors d''un entraînement intensif de plusieurs années, Nikita devient un agent hautement qualifié des services secrets, capable désormais selon Bob d''évoluer seule à l''extérieur. Celui-ci espère d''ailleurs s''en assurer lors d''une terrible mise à l''épreuve, dans laquelle Nikita doit éliminer un pilier de la mafia asiatique au beau milieu d''un restaurant bondé...', 1990, 'FR', '2019-10-21 14:47:01'),
  ('Un singe en hiver', 'A monkey in winter', 'En juin 1944, Albert Quentin (Jean Gabin), ancien fusilier marin en Chine, tient, avec sa femme Suzanne (Suzanne Flon) rencontrée à La Bourboule, l''hôtel Stella dans le village de Tigreville, sur la côte normande aux environs de Deauville.

Il se laisse souvent aller à trop boire, ce qui le porte à la nostalgie de sa jeunesse militaire vécue sur le Yang-Tsé-Kiang. Lors d''un bombardement en juin 1944, il promet à Suzanne de ne plus boire si l''hôtel échappe à la destruction , promesse tenue1.

Un soir, quinze ans plus tard, débarque Gabriel Fouquet (Jean-Paul Belmondo), homme jeune et remuant, publicitaire de son état. Fouquet boit pour effacer l''échec de sa vie sentimentale avec Claire qui vit à Madrid, « voyager » en Espagne grâce à l''alcool, et rêver de tauromachie. Il vient voir sa fille Marie pensionnaire à Tigreville, dans une pension dont Mme Victoria, la directrice qui, pourtant française, ne parle qu''anglais. Les deux hommes, qui n''ont pas « le vin petit ni la cuite mesquine », vont connaître deux jours d''évasion grâce à l''ivresse, l''un en Espagne et l''autre en Chine. Ce sera l''occasion d''un duo a cappella sur la fameuse chanson Nuits de Chine. L''apothéose de cette soûlographie est atteinte avec un feu d''artifice « dantesque » sur la plage. Le lendemain, la vie sépare les deux amis en gare de Lisieux : Gabriel part avec sa fille, qu''il a sortie de sa pension, alors qu''Albert se dirige vers l''hiver de sa vie.', 1962, 'FR', '2019-10-21 15:13:58'),
  ('Le clan des Siciliens', 'The Sicilian Clan', 'Roger Sartet, un truand ambitieux, s''évade avec l''aide des Manalese, une famille mafieuse. Traqué par le commissaire Le Groff, Sartet va néanmoins proposé un gros coup aux Manalese: voler une collection de bijoux.', 1969, 'FR', '2019-10-21 15:17:04'),
  ('Peur sur la ville', 'Fear Over The City', 'Le commissaire Letellier a vu sa carrière brisée par le truand Marcucci, à l''issue d''un braquage qui a mal tourné. Muté dans un commissariat terne, il continue à chercher la trace de son ennemi. Au moment où Letellier apprend enfin le retour du braqueur à Paris, un mystérieux tueur terrorise la capitale. Il se fait appeler Minos, par référence à L''Enfer, le premier tome de la Divine Comédie, de Dante, se présente à ses victimes puis à l''opinion publique comme un « justicier » et étrangle des femmes célibataires à la vie sexuelle libre. Letellier doit alors choisir entre assouvir sa vengeance ou faire son métier de policier et neutraliser un redoutable tueur en série... ', 1975, 'FR', '2019-10-21 15:23:03'),
  ('Le Cords De Mon Ennemi', 'Body Of My Enemy', 'Un homme sort de prison après y avoir passé 7 ans pour un crime qu''il n''a pas commis. Il rentre chez lui pour découvrir qui est derrière tout cela.', 1976, 'FR', '2019-10-21 15:25:19'),
  ('Mean Streets', 'Mean Streets', 'Au début des années 1970, dans le quartier de Little Italy à New York, Charlie et Johnny Boy tentent de percer dans le milieu. Si Charlie a ses chances grâce à son oncle bien intégré dans la mafia qui lui promet la gestion d''un restaurant, Johnny Boy criblé de dettes multiplie ce qui apparaît de plus en plus comme des provocations. Charlie marqué par la religion et la figure de saint François d''Assise protège et tente de sauver un Johnny Boy qui se condamne lui-même chaque jour davantage…', 1973, 'US', '2019-10-22 08:40:01'),
  ('Raging Bull', 'Raging Bull', 'Jake LaMotta est un boxeur américain d''origine italienne surnommé « le taureau du Bronx ». Issu d''un milieu modeste, il atteint les sommets grâce à des combats mythiques, notamment contre Sugar Ray Robinson et Marcel Cerdan, qui le mèneront au titre de champion du monde des poids moyens. Mais il connaît ensuite l''échec de sa vie privée (divorce, reconversion en gérant de boîte de nuit...).

', 1980, 'US', '2019-10-22 08:44:33'),
  ('Les Nerfs à vif ', 'Cape Fear', 'Max Cady, condamné à quatorze années de prison pour viol et voie de fait sur une mineure, est à nouveau libre. Avec détermination et rigueur, il entreprend de se venger de l''avocat Sam Bowden, qu''il estime responsable de son incarcération.
', 1991, 'US', '2019-10-22 08:45:46'),
  ('Les Affranchis', 'Goodfellas', 'Depuis sa plus tendre enfance, Henry Hill, né d''un père irlandais et d''une mère sicilienne, veut devenir gangster et appartenir à la Mafia. Adolescent dans les années cinquante, il commence par travailler pour le compte de Paul Cicero et voue une grande admiration pour Jimmy Conway, qui a fait du détournement de camions sa grande spécialité. Lucide et ambitieux, il contribue au casse des entrepôts de l''aéroport d''Idlewild et épouse Karen, une jeune Juive qu''il trompe régulièrement. Mais son implication dans le trafic de drogue le fera plonger...', 1990, 'US', '2019-10-22 08:52:13'),
  ('Taxi Driver', 'Taxi Driver', 'Vétéran de la Guerre du Vietnam, Travis Bickle est chauffeur de taxi dans la ville de New York. Ses rencontres nocturnes et la violence quotidienne dont il est témoin lui font peu à peu perdre la tête. Il se charge bientôt de délivrer une prostituée mineure de ses souteneurs.', 1976, 'US', '2019-10-22 08:53:41'),
  ('Casino', 'Casino', 'En 1973, Sam Ace Rothstein est le grand manitou de la ville de toutes les folies, Las Vegas. Il achète et épouse une virtuose de l’arnaque, Ginger Mc Kenna, qui sombre bien vite dans l’alcool et la drogue. Mais un autre ennui guette Sam, son ami d’enfance Nicky Santoro, qui entreprend de mettre la ville en coupe réglée…', 1995, 'US', '2019-10-22 08:56:34'),
  ('Alexandre', 'Alexander', 'Le film narre la vie d''Alexandre le Grand (-356 à -323) du point de vue d''un de ses principaux généraux, Ptolémée, dictant ses Mémoires1, de son enfance à sa mort, des cours d''Aristote aux conquêtes qui firent sa légende, de l''intimité aux champs de bataille.

Fils du roi Philippe II de Macédoine, il soumit la Grèce antique révoltée, fonda les Alexandries, défit les Perses, s''empara de Babylone et atteignit l''Indus pour établir à 32 ans le plus vaste empire jamais unifié avant lui : la civilisation hellénistique.

Deux époques coexistent dans le film : celle du narrateur, l''Égypte ptolémaïque, et celle de la vie d''Alexandre, évoquée de façon non linéaire par une alternance entre la progression chronologique générale et plusieurs retours en arrière.', 2005, 'US', '2019-10-22 08:58:57'),
  ('Ne pas avaler', 'Nil by Mouth', 'Ne pas avaler dépeint la vie quotidienne d''une famille déchirée par la violence, l''alcool et la drogue, sans jamais tomber dans le misérabilisme.

La famille de Raymond, sa femme Val et son beau-frère Billy vivent dans un quartier ouvrier de Londres. Billy est drogué et Raymond l''expulse de chez lui, le contraignant à vivre dehors, espérant l''aide de sa mère Janet et de sa grand-mère Kath. Raymond, père d''une petite fille, est la plupart du temps ivre et devient parfois violent, y compris avec sa femme enceinte.', 1997, 'UK-FR', '2019-10-22 09:31:20'),
  ('Platoon', 'Platoon', 'Septembre 1967: Chris Taylor, dix-neuf ans, rejoint la compagnie Bravo du 25ème régiment d''infanterie, près de la frontière cambodgienne. Chris, issu d''une famille bourgeoise s''est engagé volontairement et, plein d''idéal entend bien servir son pays. Mais la réalité est tout autre et ses illusions vont tomber les unes après les autres. Il sera également temoin de la rivalité sanglante qui oppose deux officiers qu''il admire.', 1986, 'US', '2019-10-22 09:32:42'),
  ('Apocalypse Now', 'Apocalypse Now', 'Cloîtré dans une chambre d''hôtel de Saïgon, le jeune capitaine Willard, mal rasé et imbibé d''alcool, est sorti de sa prostration par une convocation de l''état-major américain. Le général Corman lui confie une mission qui doit rester secrète : éliminer le colonel Kurtz, un militaire aux méthodes quelque peu expéditives et qui sévit au-delà de la frontière cambodgienne.', 1979, 'US', '2019-10-22 09:34:10'),
  ('Le Bon, la Brute et le Truand', 'Il buono, il brutto, il cattivo', 'Pendant la Guerre de Sécession, trois hommes, préférant s''intéresser à leur profit personnel, se lancent à la recherche d''un coffre contenant 200 000 dollars en pièces d''or volés à l''armée sudiste. Tuco sait que le trésor se trouve dans un cimetière, tandis que Joe connaît le nom inscrit sur la pierre tombale qui sert de cache. Chacun a besoin de l''autre. Mais un troisième homme entre dans la course : Setenza, une brute qui n''hésite pas à massacrer femmes et enfants pour parvenir à ses fins.', 1966, 'IT', '2019-10-22 09:38:49'),
  ('Wall Street', 'Wall Street', 'Splendeurs et misères de Bud Fox, jeune loup d''une banque d''affaires de Wall Street, qui réussit à séduire un investisseur, Gordon Gekko. Ce dernier lui explique que l''avarice et l''ambition sont les premières vertus s''il veut réussir dans le milieu de la finance.', 1987, 'US', '2019-10-22 09:42:51'),
  ('Les Pleins Pouvoirs', 'Absolute Powers', 'Excellent dessinateur à ses heures perdues, Luther Whitney est également un cambrioleur de haut vol menant une vie solitaire et entretenant des relations tendues avec sa fille Kate, devenue procureur, en raison de l''absence de ce dernier. Un soir, alors qu''il commet un cambriolage dans la luxueuse demeure de Walter Sullivan, philanthrope influent de Washington, il assiste caché dans une chambre forte avec un miroir sans tain aux ébats amoureux de Christy, la femme de Sullivan, et d''un homme qui se trouve être le président des États-Unis Alan Richmond, dont Sullivan est l''ami et également un soutien financier.

Mais, sous le regard pétrifié de Luther, la situation dégénère lorsque Richmond se montre violent envers Christy, qui pour se protéger, s''empare d''un coupe-papier et le blesse au bras avant d''être abattue par balles par Colin et Burton, deux agents de la sécurité rapprochée du président. Gloria Russell, conseillère du président également présente sur les lieux, leur ordonne de maquiller leur acte en crime de cambrioleur, mais Luther s''enfuit avec quelques objets de valeur ainsi que le précieux coupe papier avec les empreintes de Richmond, après avoir semé les gardes du corps venus le poursuivre.

Chargé de l''enquête, le lieutenant de police Seth Frank soupçonne Luther du cambriolage, mais pas du meurtre car Luther n''a jamais tué, ce qui vaut l''estime de Frank. Alors que Luther s''apprête à fuir à l''étranger, il décide de rester afin de confondre lui-même les véritables responsables après avoir regardé, écœuré, une conférence de presse de Richmond feindre le chagrin auprès de Sullivan. Il se rend à un rendez-vous avec Kate - organisé par Frank - qui a découvert que son père n''a jamais cessé de l''aimer et de s''intéresser à sa carrière, tout en veillant sur elle de loin. Mais Colin et McCarthy, un tueur à gages engagé par Sullivan, sont tous deux au rendez-vous armés d''un fusil à lunettes. Luther et Kate se font tirer dessus mais échappent aux balles. Luther, déguisé en policier, réussit à tromper les tueurs et à échapper aux forces de l''ordre.

Luther envoie à Russell le collier de diamants que portait Christy le soir du meurtre après avoir envoyé un Polaroïd du coupe-papier. Fou de rage, Richmond ordonne alors à Colin et Burton de tuer Kate, mais celle-ci n''est que grièvement blessée lors de la chute de sa voiture sur une falaise par Colin. Burton, écœuré, se suicide en laissant une lettre de confession, tandis que Luther tue Colin qui, déguisé en infirmier, tentait d''achever Kate à l''hôpital. Luther révèle à Sullivan la vérité et lui remet le coupe-papier. Le vieil homme se rend à la Maison-Blanche en pleine nuit pour un entretien avec son « ami intime » Richmond. Seth Frank arrête Russell, tandis que Luther apprend le « suicide » de Richmond. Après avoir sauvé Kate, Luther sait que Seth, amoureux de cette dernière, peut veiller sur elle.', 1997, 'US', '2019-10-22 09:49:48'),
  ('Pollock', 'Pollock', 'La vie de l''artiste américain d''après-guerre Jackson Pollock qui s''est fait connaître du grand public par sa peinture abstraite.', 2000, 'US', '2019-10-22 09:53:44'),
  ('Wall Street : L''argent ne dort jamais', 'Wall Street: Money Never Sleeps', 'En 2001, Gordon Gekko sort de prison après avoir purgé une peine de 8 ans pour délit d''initié et diverses fraudes.

Sept ans plus tard, à Wall Street, New York peu avant le krach boursier de 2008, le jeune trader, Jacob « Jake » Moore, assiste à l''effondrement de sa banque qui conduit au suicide de son patron et mentor. Jake suit la piste des obscures tractations financières à l''origine de ces événements tout en essayant d''obtenir le contact avec Gordon Gekko, ex gourou de la finance et père de sa petite amie, Winnie. Celle-ci reproche la mort de son frère à son père pendant son incarcération et a coupé les ponts avec lui.

S''approchant simultanément de Bretton James, responsable de la perte de son mentor selon lui, Jake se confronte aux maîtres du jeu.', 2010, 'US', '2019-10-22 10:00:06'),
  ('La Firme', 'The Firm', 'A la fin de ses études de droit, Mitch McDeere, brillant élément, est sollicité par plusieurs grands cabinets d''avocats. Il fixe son choix sur Bendini, Lambert & Locke, qui lui offrent les conditions les plus avantageuses. En contrepartie, il devra, comme chaque membre du cabinet, fournir quelque quatre-vingts heures de travail hebdomadaire. Il ignore encore qu''il vient de vendre son âme au diable.
', 1993, 'US', '2019-10-22 10:04:51'),
  ('L''Enfer du Dimanche', 'Any Given Sunday', 'Les Sharks de Miami, prestigieuse équipe de football américain, sont dans une mauvaise passe et luttent pour obtenir une place en série éliminatoire. Leur quart-arrière étoile Jack « Cap » Rooney (Dennis Quaid) est de plus en plus contesté au sein de l''équipe à cause de son âge, notamment par l''ambitieuse présidente Christina Pagniacci (Cameron Diaz), qui veut moderniser la franchise. De plus, l''entraîneur Tony D''Amato (Al Pacino), qui a mené dans le passé l''équipe à deux victoires en finale du championnat, est critiqué car considéré comme vieillissant. Le jeune Willie Beamen (Jamie Foxx) remplace Cap après la blessure de ce dernier et, malgré des débuts hésitants, fait rapidement sensation par son jeu spectaculaire. Rapidement, les résultats des Sharks s''améliorent, et Beamen voit sa popularité grimper, popularité qui lui monte vite à la tête. S''instaure alors une guerre entre Beamen, qui veut mener les matches à sa manière au mépris des cahiers de jeu, et D''Amato qui compte bien se faire respecter jusqu''au bout de sa carrière, dont il sent la fin proche.', 1999, 'US', '2019-10-22 10:07:29'),
  ('The Doors', 'The Doors', '1965. Jim Morrison, qui écrit des poèmes et suit les cours à UCLA, s''éprend de Pamela Courson. Il lui lit ses écrits influencés par le mysticisme des Indiens, qu''il a découvert durant son enfance au Nouveau-Mexique. La sensibilité des poèmes de Morrison impressionne Ray Manzarek et, bientôt, un groupe musical se forme…', 1991, 'US', '2019-10-22 15:01:44'),
  ('Heat', 'Heat', 'La bande de Neil McCauley à laquelle est venu se greffer Waingro, une nouvelle recrue, attaque un fourgon blindé pour s''emparer d''une somme importante en obligations. Cependant, ce dernier tue froidement l''un des convoyeurs et Chris Shiherlis se retrouve obligé de "terminer le travail". Neil tente d''éliminer Waingro, mais celui-ci parvient à s''échapper. Parallèlement, le lieutenant Vincent Hanna mène l''enquête...', 1995, 'US', '2019-10-22 15:05:32'),
  ('Collatéral', 'Collateral', 'Max est taxi de nuit à Los Angeles. Un soir, il se lie d''amitié avec une dénommée Annie Farrell, une belle femme procureur montée à l''arrière de son véhicule. Quelques minutes plus tard, c''est au tour d''un homme prénommé Vincent de monter dans le taxi. Un businessman, selon toute apparence, avec un emploi du temps chargé : pas moins de cinq rendez-vous à tenir dans la nuit. Max accepte de lui louer ses services jusqu''au petit matin, en échange de 600 dollars.
Premier arrêt. Vincent entre dans un immeuble. Un coup de feu éclate aussitôt, un corps plonge dans le vide, s''écrasant sur le toit du taxi. Vincent redescend et, sous la menace de son arme, oblige Max à dissimuler le cadavre dans le coffre et à reprendre son mortel périple.
Un chauffeur de taxi, un tueur implacable, cinq "cibles" à éliminer, des agents des stups et une équipe du FBI... Leurs destins se joueront cette nuit...', 2004, 'US', '2019-10-22 15:11:11'),
  ('Chute Libre', 'Falling Down', 'Sous le soleil brûlant de Los Angeles, un Américain transpire à grosses gouttes dans sa voiture, engluée au coeur d''un énorme embouteillage. C''est fini, il sera en retard pour l''anniversaire de sa fille. Il abandonne alors son véhicule, immatriculé "D-Fens", et sombre dans la folie. Tout à son obsession d''arriver à temps pour embrasser sa fille, il dévaste une épicerie, rosse des voyous, s''empare d''un arsenal, saccage un fast-food et tire au bazooka sur un chantier, un oeil hagard, l''autre rivé sur sa montre. L''officier de police Prendergast oublie qu''il fête le jour même son départ à la retraite et décide de prendre le dément en chasse...', 1993, 'US', '2019-10-22 15:29:23'),
  ('Carnaval', 'Dardamelle', 'Un architecte se fache avec son épouse, celle-çi lui avoue qu''elle le trompe. Il décide de proclamer son infidèlité et affiche sur son balcon : cocu de première classe...', 1953, 'FR', '2019-10-23 07:57:30'),
  ('L''Ennemi Public N°1', 'L''Ennemi Public N°1', 'Pour une simple erreur, Joe Calvet est pris pour un redoutable criminel. Il se prend au jeu...', 1954, 'FR', '2019-10-23 08:08:28'),
  ('Simplet', 'Simplet', 'Un simple d''esprit s''avère être le porte-bonheur d''un village.', 1942, 'FR', '2019-10-23 08:42:43'),
  ('Topaze', 'Topaze', 'Instituteur à la pension Muche, Topaze, minable répétiteur incapable de tricher sur les notes de riches cancres, est licencié. Réduit au chômage, il donne des leçons particulières au neveu de Suzy Courtois, une demi-mondaine... Il va alors prendre conscience de la vanité de sa mission éducative et devenir une fripouille cynique...', 1954, 'FR', '2019-10-23 08:47:25'),
  ('Le Schpountz', 'Le Schpountz', 'Jeune commis épicier un peu mythomane, Irénée, à qui le cinéma a tourné la tête, est convaincu qu''il deviendra un acteur célèbre. Il rencontre une équipe de tournage qui lui réserve une plaisanterie cruelle... Il arrive aux studios plein d''espoir...', 1948, 'FR', '2019-10-23 09:03:33'),
  ('Marius (Trilogie Marseillaise)', 'Marius (Trilogie Marseillaise)', 'Marseille – Le bar de la Marine – Marius est un jeune homme que la mer exalte. Il aime son père César, bourru et bonhomme, il aime aussi la petite Fanny qui vend des coquillages devant le bar de César. Depuis son enfance, l’envie de courir le monde l’enflamme. Il lutte contre sa folie. Il ne veut pas abandonner son père qui en mourrait peut-être de chagrin, ni la petite Fanny qui ne pense qu’à lui. Et pourtant la mer est là…', 1931, 'FR', '2019-10-23 09:08:29'),
  ('Fanny(Trilogie Marseillaise)', 'Fanny(Trilogie Marseillaise)', 'Marius est parti sur " La Malaisie ", abandonnant le vieux César, son père, et Fanny, sa fiancée, qui porte leur enfant. Panisse, un brave homme, épouse Fanny et adopte le petit Césariot qu’il aime comme un fils. Mais un jour, Marius revient…', 1932, 'FR', '2019-10-23 09:08:29'),
  ('César(Trilogie Marseillaise)', 'César(Trilogie Marseillaise)', 'Fanny, abandonnée par Marius, épouse Panisse qui adopte Césariot, l’enfant de l’amour, et l’élève comme son fils. Aujourd’hui, Césariot est adulte et Panisse se meurt. Fanny révèle la vérité à son fils qui décide alors de partir à la recherche de Marius, son père…', 1936, 'FR', '2019-10-23 09:08:29'),
  ('Angèle', 'Angèle', 'Séduite, puis abandonnée, Angèle se retrouve vite dans une maison close de Marseille. Fille-mère d''un petit garcon, elle retourne au village où son père l''enferme dans la cave de la ferme avec sa progéniture illégitime.
', 1934, 'FR', '2019-10-23 09:08:29'),
  ('Adhémar ou le Jouet de la Fatalité', 'Adhémar', 'Adhémar Pomme déclenche l''hilarité dans chacun de ses emplois à cause de son faciès chevalin. Il essaye d''échapper à son handicap en se faisant admettre à l''asile de monstres. Mais ça n''est pas si facile.
', 1951, 'FR', '2019-10-23 09:11:11'),
  ('Adrien', 'Adrien', 'Adrien, un inventeur, tente de faire commercialiser son invention révolutionnaire : des patins à roulettes à moteur...', 1943, 'FR', '2019-10-23 09:12:23'),
  ('Mam''zellz Nitouche', 'Mam''zellz Nitouche', 'La jeune pensionnaire d''un couvent est rappelee par sa famille pour faire un beau mariage. Mais la belle veut faire du theatre...', 1931, 'FR', '2019-10-23 09:34:44'),
  ('Zouzou', 'Zouzou', 'Zouzou, jeune orpheline, est adoptee par un vieux forain. Elle ira jusqu''a sacrifier ses amours pour devenir une vedette de la scene.', 1934, 'FR', '2019-10-23 09:35:33'),
  ('Les Gaités de l''Escadron', 'Les Gaités de l''Escadron', 'Sous le contrôle du sévère adjudant Flick, les recrues de la caserne ont un quotidien des plus difficiles. Entre brimades et multiples corvées, la vie de soldat est loin d''être évidente.
', 1932, 'FR', '2019-10-23 09:40:44'),
  ('Stalingrad', 'Stalingrad', 'Durant la Seconde Guerre mondiale, en septembre 1942, Hitler envisage d''envahir Stalingrad, ville clé de l''URSS qui le sépare de la victoire totale sur l''Europe. Les Russes se préparent à la défensive. Un héros aiderait à motiver les troupes.
Entre alors en jeu Vassili Zaitsev, un jeune tireur d''élite de l''Armée Rouge. Doué d''une stupéfiante adresse au tir, il est remarqué par un commissaire au peuple, Ivan Danilov. Les deux jeunes gens deviennent amis. Ranimer la flamme de l''héroïsme, redonner aux soldats décimés la force de lutter contre l''implacable machine de guerre allemande, telle est la mission de Danilov. Celui-ci fait de Vassili le héros de sa propagande.
Dans le camp ennemi, l''état-major dépêche son meilleur tireur d''élite, le Major König, pour abattre celui qui est devenu le symbole de l''indomptable résistance russe.', 2001, 'FR', '2019-10-23 09:48:44'),
  ('Le Nom de la Rose', 'The Name of the Rose', 'En l''an 1327, dans une abbaye bénédictine, des moines disparaissent. Un franciscain, Guillaume de Baskerville aidé du jeune novice Adso von Melk mène l''enquête. C''est l''époque où l''Eglise, en pleine crise, se voit disputer son pouvoir spirituel et temporel. C''est aussi l''apogée de l''inquisition. Un thriller moyenageux très attendu préparé avec soin pendant trois ans, respectant le mieux possible l''époque et qui a coûté la bagatelle de dix-neuf millions de dollars. C''est également un film de Jean-Jacques Annaud toujours passionnément entraîné par ses sujets.

', 1986, 'FR-IT-DE', '2019-10-23 09:53:10'),
  ('Gribouille', 'Gribouille', 'Bon père de famille, Camille Morestan est nommé juré au procès de Natalie Roguin, accusée d''avoir tué son amant. Il parvient à la faire acquitter et décide de l''engager dans son magasin. Il va alors secrètement s''éprendre d''elle, tout comme son fils Claude. Ils vont alors se déchirer pour les faveurs de la charmante Natalie.
', 1937, 'FR', '2019-10-23 09:53:10'),
  ('True Romance', 'True Romance', 'Le jour de son anniversaire, Clarence Worley rencontre la splendide Alabama dans un cinéma miteux. Coup de foudre immédiat. Après une nuit d''amour, Alabama avoue a Clarence qu''elle a été en fait engagée par le patron de Clarence comme cadeau d''anniversaire. De là va commencer une folle aventure.', 1993, 'US', '2019-10-23 10:03:42'),
  ('Le Cercle Rouge', 'Le Cercle Rouge', 'Un truand marseillais, un détenu en cavale et un ancien policier mettent au point le hold-up du siècle. Le commissaire Mattei, de la brigade criminelle, leur tend une souricière.
', 1970, 'FR-IT', '2019-10-23 10:28:15'),
  ('Jardins de Pierre', 'Gardens of Stone', 'En 1966, le sergent Clell Hazard (James Caan) et le sergent-major Goody Nelson (James Earl Jones), vétérans de guerre, notamment celle de Corée, sont cantonnés au bataillon de parade à Washington. Ils ont sous leurs ordres de jeunes recrues qui officient au cimetière militaire d’Arlington lors d''innombrables enterrements de soldats morts au Viêt Nam. Quand débarque un jeune soldat, militaire jusqu''au bout des ongles, Jackie Willow (D. B. Sweeney), fils d’un vieil ami de Hazard, celui-ci le prend sous son aile malgré leurs divergences d’opinion sur la guerre en cours.

Willow ne rêve que de guerre et de médailles tandis que Hazard, ayant déjà connu le bourbier vietnamien, tente de lui faire comprendre que cette guerre n’en est pas une comme les autres. Alors que Willow retrouve son ex-petite amie qu’il finira par épouser, Hazard rencontre une journaliste (Anjelica Huston), violemment opposée à la guerre. L’idylle se nouera malgré tout alors que Willow, devenu officier, part pour le Viêt Nam où, désabusé, comme le montre son courrier, il finira par être tué au désespoir du sergent Hazard.', 1987, 'US', '2019-10-23 12:11:49'),
  ('Tucker', 'Tucker: The Man and His Dream', 'En 1948, le jeune ingénieur américain Preston Tucker conçoit une automobile révolutionnaire qu''il baptise de son nom. Le succès prévisible déclenche une contre-attaque immédiate des trois grands constructeurs, General Motors, Chrysler et Ford, pour tuer le projet dans l''œuf. Mais Tucker est décidé à ne pas se laisser faire et à réaliser son rêve : il doit absolument réaliser cinquante exemplaires de sa voiture pour que celle-ci existe de fait...', 1988, 'US', '2019-10-23 12:28:57'),
  ('Le Parrain', 'The Godfather ', 'En 1945, à New York, les Corleone sont une des cinq familles de la mafia. Don Vito Corleone, "parrain" de cette famille, marie sa fille à un bookmaker. Sollozzo, " parrain " de la famille Tattaglia, propose à Don Vito une association dans le trafic de drogue, mais celui-ci refuse. Sonny, un de ses fils, y est quant à lui favorable.
Afin de traiter avec Sonny, Sollozzo tente de faire tuer Don Vito, mais celui-ci en réchappe. Michael, le frère cadet de Sonny, recherche alors les commanditaires de l''attentat et tue Sollozzo et le chef de la police, en représailles.
Michael part alors en Sicile, où il épouse Apollonia, mais celle-ci est assassinée à sa place. De retour à New York, Michael épouse Kay Adams et se prépare à devenir le successeur de son père...',
   1972, 'US', '2019-10-23 12:28:57'),
  ('Le Parrain: 2e partie', 'The Godfather: Part II', 'Depuis la mort de Don Vito Corleone, son fils Michael règne sur la famille. Amené à négocier avec la mafia juive, il perd alors le soutien d''un de ses lieutenants, Frankie Pentageli. Echappant de justesse à un attentat, Michael tente de retrouver le coupable, soupçonnant Hyman Roth, le chef de la mafia juive.
Vito Corleone, immigrant italien, arrive à New York au début du siècle ; très vite, il devient un des caïds du quartier, utilisant la violence comme moyen de régler toutes les affaires. Seul au départ, il bâtit peu à peu un véritable empire, origine de la fortune de la famille des Corleone.',
   1975, 'US', '2019-10-23 12:28:57'),
  ('Le Parrain: 3e partie', 'The Godfather: Part III', 'Atteignant la soixantaine, Michael Corleone désire à la fois renouer avec les siens et se réhabiliter aux yeux de la société, surtout de l''Eglise. Il arrivera presque a ses fins, mais sa vie passée et ses anciens ennemis le rattraperont plus vite.
Michael Corleone est fatigué. Il veut prendre ses distances avec les activités mafieuses de sa famille. Il veut convertir ces activités en affaires légales. Kay, son ex-femme, lui fait même accepter que leur fils devienne un chanteur d''opéra et ne reprenne pas les activités familiales.
Pendant ce temps, la fille de Michael, Mary, et son neveu, le fils de Sonny, Vincent, nouent une idylle qui n''est pas la bienvenue dans la famille.
Il décide d''aider le Vatican à renflouer ses caisses et reçoit en échange le contrôle d''une entreprise immobilière leur appartenant. Attisant la jalousie de ses pairs, Michael échappe de justesse à un attentat commis par l''un d''eux. Vincent se propose alors pour reprendre les affaires de la famille en main.',
   1991, 'US', '2019-10-23 12:28:57'),
  ('Dracula', 'Bram Stoker''s Dracula',
   'En 1492, le prince Vlad Dracul, revenant de combattre les armées turques, trouve sa fiancée suicidée. Fou de douleur, il défie Dieu, et devient le comte Dracula, vampire de son état. Quatre cents ans plus tard, désireux de quitter la Transylvanie pour s''établir en Angleterre, il fait appel à Jonathan Harker, clerc de notaire et fiancé de la jolie Mina Murray. La jeune fille est le sosie d''Elisabeta, l''amour ancestral du comte...',
   1992, 'US', '2019-10-23 12:44:59'),
  ('Les Gens de la pluie ', 'The Rain People',
   'Natalie Ravenna est femme au foyer. Mais lorsqu’elle découvre qu''elle est enceinte, elle ressent le besoin de s''évader de sa vie et de son mariage. Elle parcourt alors les routes des États-Unis. Elle fait la rencontre d''un homme simple, joueur de football, blessé, fidèle, et naïf, qu''elle prend en stop, et qui s''appelle Killer. Il lui parle des Gens de la pluie, des gens transparents faits de pluie, et qui se dissolvent lorsqu''ils pleurent. Il dit en avoir rencontré.',
   1969, 'US', '2019-10-23 12:52:46'),
  ('Conversation secrète', 'The Conversation',
   'Harry Caul, catholique introverti et secret, est un grand spécialiste de la filature. Il est engagé dans une mission pour suivre un couple et enregistrer leurs conversations. Une fois sa mission accomplie, il découvre en écoutant son enregistrement que le couple est en danger de mort. Se souvenant d''une précédente mission au cours de laquelle une famille avait été tuée, il est pris dans un dilemme moral qu''il ne parviendra pas à surmonter.',
   1974, 'US', '2019-10-23 12:54:50');

INSERT INTO GENRE (IdGenre, Genre)
VALUES
  (0, 'Policier'),
  (1, 'Thriller'),
  (2, 'Fantastique/SF'),
  (3, 'Drame'),
  (4, 'Biopic'),
  (5, 'Action'),
  (6, 'Horreur'),
  (7, 'Comédie'),
  (8, 'Western'),
  (9, 'Aventure'),
  (10, 'Guerre');

INSERT INTO JOUE (IdActeurRealisateur, IdFilm)
VALUES
  (1, 1),
  (14, 1),
  (23, 1),
  (7, 2),
  (8, 2),
  (5, 3),
  (5, 4),
  (3, 5),
  (11, 6),
  (1, 7),
  (14, 7),
  (15, 7),
  (11, 8),
  (22, 8),
  (12, 9),
  (12, 10),
  (12, 11),
  (16, 11),
  (17, 12),
  (19, 12),
  (20, 12),
  (46, 12),
  (9, 13),
  (16, 14),
  (16, 15),
  (17, 15),
  (38, 16),
  (39, 16),
  (29, 17),
  (26, 18),
  (27, 18),
  (28, 18),
  (34, 19),
  (42, 19),
  (30, 20),
  (37, 20),
  (16, 21),
  (25, 21),
  (29, 21),
  (43, 21),
  (34, 22),
  (42, 22),
  (16, 23),
  (29, 23),
  (39, 23),
  (17, 24),
  (35, 24),
  (44, 24),
  (17, 25),
  (27, 25),
  (45, 25),
  (46, 25),
  (47, 25),
  (5, 26),
  (42, 26),
  (34, 27),
  (37, 27),
  (42, 27),
  (5, 28),
  (5, 29),
  (30, 29),
  (49, 30),
  (50, 30),
  (51, 30),
  (50, 31),
  (52, 31),
  (71, 31),
  (50, 32),
  (50, 33),
  (52, 33),
  (53, 33),
  (71, 33),
  (72, 33),
  (50, 34),
  (51, 34),
  (73, 34),
  (50, 35),
  (52, 35),
  (71, 35),
  (74, 35),
  (63, 36),
  (10, 37),
  (54, 38),
  (60, 38),
  (75, 38),
  (48, 39),
  (58, 39),
  (76, 39),
  (77, 39),
  (78, 39),
  (79, 39),
  (80, 39),
  (1, 40),
  (56, 40),
  (58, 41),
  (59, 41),
  (60, 41),
  (1, 42),
  (23, 42),
  (62, 42),
  (62, 43),
  (81, 43),
  (54, 44),
  (59, 44),
  (60, 44),
  (23, 45),
  (62, 45),
  (69, 45),
  (54, 46),
  (70, 46),
  (54, 47),
  (81, 47),
  (35, 48),
  (50, 48),
  (70, 48),
  (81, 48),
  (84, 48),
  (85, 48),
  (69, 49),
  (59, 50),
  (77, 50),
  (87, 51),
  (89, 51),
  (87, 52),
  (87, 53),
  (87, 54),
  (89, 54),
  (87, 55),
  (90, 55),
  (90, 56),
  (93, 56),
  (90, 57),
  (93, 57),
  (87, 58),
  (89, 58),
  (87, 59),
  (87, 60),
  (89, 60),
  (87, 61),
  (93, 62),
  (42, 63),
  (42, 64),
  (87, 64),
  (93, 64),
  (62, 65),
  (95, 65),
  (65, 66),
  (66, 66),
  (67, 66),
  (93, 67),
  (44, 68),
  (66, 68),
  (72, 68),
  (76, 68),
  (84, 68),
  (96, 68),
  (34, 69),
  (97, 69),
  (98, 69),
  (100, 70),
  (66, 71),
  (70, 72),
  (77, 72),
  (80, 72),
  (100, 72),
  (50, 73),
  (77, 73),
  (80, 73),
  (100, 73),
  (70, 74),
  (44, 75),
  (61, 75),
  (77, 76),
  (100, 76),
  (100, 77);

INSERT INTO REALISE (IdActeurRealisateur, IdFilm)
VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (6, 4),
  (4, 5),
  (11, 6),
  (1, 7),
  (11, 8),
  (21, 9),
  (13, 10),
  (13, 11),
  (18, 12),
  (24, 13),
  (24, 14),
  (24, 15),
  (13, 16),
  (25, 17),
  (25, 18),
  (33, 19),
  (31, 20),
  (25, 21),
  (36, 22),
  (24, 23),
  (18, 24),
  (18, 25),
  (36, 26),
  (36, 27),
  (36, 28),
  (36, 29),
  (49, 30),
  (49, 31),
  (49, 32),
  (49, 33),
  (49, 34),
  (49, 35),
  (54, 36),
  (44, 37),
  (54, 38),
  (54, 39),
  (55, 40),
  (54, 41),
  (1, 42),
  (62, 43),
  (54, 44),
  (68, 45),
  (54, 46),
  (54, 47),
  (83, 48),
  (83, 49),
  (86, 50),
  (36, 51),
  (36, 52),
  (87, 53),
  (88, 54),
  (88, 55),
  (88, 56),
  (91, 56),
  (92, 57),
  (88, 58),
  (88, 59),
  (87, 60),
  (88, 61),
  (92, 62),
  (92, 63),
  (94, 64),
  (64, 65),
  (64, 66),
  (92, 67),
  (82, 68),
  (3, 69),
  (48, 70),
  (48, 71),
  (48, 72),
  (48, 73),
  (48, 74),
  (48, 75),
  (48, 76),
  (48, 77);

INSERT CORRESPOND (IdGenre, IdFilm)
VALUES
  (5, 1),
  (8, 1),
  (2, 2),
  (5, 2),
  (0, 3),
  (3, 3),
  (9, 3),
  (0, 4),
  (3, 4),
  (3, 5),
  (4, 5),
  (7, 6),
  (9, 6),
  (3, 7),
  (5, 7),
  (3, 8),
  (7, 8),
  (0, 9),
  (3, 9),
  (5, 9),
  (7, 10),
  (9, 10),
  (7, 11),
  (3, 12),
  (4, 12),
  (9, 12),
  (7, 13),
  (7, 14),
  (7, 15),
  (0, 16),
  (5, 16),
  (7, 16),
  (0, 17),
  (1, 17),
  (3, 17),
  (0, 18),
  (4, 18),
  (5, 18),
  (9, 19),
  (0, 20),
  (5, 20),
  (7, 20),
  (0, 21),
  (1, 21),
  (5, 21),
  (0, 22),
  (7, 23),
  (8, 24),
  (5, 25),
  (3, 26),
  (0, 27),
  (5, 27),
  (0, 28),
  (0, 29),
  (1, 29),
  (3, 29),
  (0, 30),
  (3, 30),
  (3, 31),
  (4, 31),
  (1, 32),
  (0, 33),
  (4, 33),
  (3, 34),
  (0, 35),
  (4, 35),
  (4, 36),
  (5, 36),
  (9, 36),
  (3, 37),
  (4, 37),
  (3, 38),
  (5, 38),
  (0, 39),
  (3, 39),
  (5, 39),
  (8, 40),
  (3, 41),
  (0, 42),
  (1, 42),
  (3, 42),
  (4, 43),
  (3, 44),
  (1, 45),
  (3, 46),
  (4, 47),
  (0, 48),
  (1, 48),
  (5, 48),
  (0, 49),
  (1, 49),
  (5, 49),
  (1, 50),
  (3, 50),
  (7, 51),
  (7, 52),
  (7, 53),
  (3, 54),
  (7, 54),
  (7, 55),
  (3, 56),
  (7, 56),
  (3, 57),
  (7, 57),
  (3, 58),
  (7, 58),
  (7, 59),
  (7, 60),
  (7, 61),
  (7, 62),
  (0, 63),
  (0, 64),
  (0, 65),
  (1, 66),
  (9, 66),
  (7, 67),
  (5, 68),
  (0, 69),
  (3, 70),
  (3, 71),
  (7, 71),
  (0, 72),
  (3, 72),
  (0, 73),
  (3, 73),
  (0, 74),
  (3, 74),
  (4, 75),
  (6, 75),
  (3, 76),
  (1, 77),
  (3, 77);
