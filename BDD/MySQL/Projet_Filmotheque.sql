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
  ('VEBER', 'Francis', 1937, 'FR', '2019-10-09 07:55:00');


INSERT INTO FILM (TitreFr, TitreO, Scenario, AnneeSortie, NationaliteF, CreatedAt)
VALUES ('Impitoyable', 'Unforgiven',
        'Un ancien cowboy se voit proposer une mission périlleuse par un de ses anciens équipiers', 1992, 'US',
        '2019-09-18 13:12:06'),
  ('Fight Club', 'Fight Club',
   'Le film démarre sur le plan du personnage principal (Edward Norton) à qui on a enfoncé un pistolet dans la bouche et dont on entend la voix en monologue qui se remémore comment il en est arrivé là.',
   1999, 'US', '2019-09-18 13:12:06'),
  ('Aîné des Ferchaux(l\')', 'Aîné des Ferchaux(l\')',
   'Contraint de renoncer à une carrière de boxeur qu''il avait un temps envisagée, un jeune homme, Michel, se fait engager comme secrétaire et garde du corps d''un vieux banquier, Dieudonné Ferchaux, contraint de quitter précipitamment la France pour fuir la justice. À New York puis à La Nouvelle-Orléans, les deux hommes apprendront à mieux se connaître tout en jouant au chat et à la souris.',
   1963,
   'FR', '2019-09-18 13:12:06'),
  ('A bout de souffle', 'A bout de souffle',
   'Michel Poiccard, jeune voyou insolent, vole une voiture à Marseille pour se rendre à Paris. En route, il tue un gendarme motocycliste qui voulait le verbaliser ',
   1960, 'FR', '2019-09-18 13:12:06'),
  ('Landru', 'Landru',
   'Ce film retrace la vie du tueur en série Henri Désiré Landru. Pendant la Première Guerre mondiale, il séduisait des femmes seules et riches. Ayant réussi à leur faire signer une procuration, il les assassinait dans sa maison de campagne puis faisait disparaître leurs corps en les brûlant dans un fourneau.',
   1963, 'FR', '2019-09-18 13:12:06'),
  ('Mission Cléopâtre', 'Mission Cléopâtre',
   'Numerobis, architecte se voit confier le chantier du palais de César par Cléopâtre', 2002, 'FR',
   '2019-09-18 13:15:44'),
  ('Million Dollar Baby', 'Million Dollar Baby', 'Million Dollar Baby', 1994, 'US', '2019-09-18 13:35:06'),
  ('Didier', 'Didier',
   'Jean-Pierre, agent de football, a accepté de garder Didier, le chien d''Annabelle, une amie journaliste. Au cours de la nuit, le labrador prend une apparence humaine, mais reste psychologiquement toujours un chien. Aux prises avec Richard, le patron du club de football pour lequel il est agent, Jean-Pierre n''avait pas besoin d''un problème supplémentaire, en plus de ses soucis avec ses joueurs vedettes, d''abord Baco, puis Fabrice blessé juste une semaine avant un match très important contre le PSG. Didier va révéler certaines dispositions footballistiques et l''aider à remplacer les joueurs blessés. De plus, il finira même par réconcilier Jean-Pierre et sa petite amie Maria.',
   1997, 'FR', '2019-10-02 06:53:33'),
  ('Tchao Pantin', 'Tchao Pantin',
   'À Paris dans le 18e arrondissement, le pompiste de nuit Lambert (Coluche), alcoolique et dépressif, croise la route d''un jeune juif arabe, petit trafiquant sans envergure : Youseff Bensoussan (Richard Anconina), venu se réfugier dans sa station-service afin d''éviter une patrouille de police. Ils deviennent rapidement amis, mais finissent par se brouiller, Bensoussan refusant de suivre les conseils, délivrés maladroitement, de Lambert qui veut le protéger. Une nuit, alors qu''il est poursuivi et tente à nouveau de trouver refuge dans sa station-service, Bensoussan, victime d''un règlement de comptes, est abattu sous les yeux de Lambert.Lambert est en réalité un ancien flic, hanté jusqu''à l''alcoolisme par la mort de son fils (par overdose) qu''il a été incapable d''éviter, malgré ou peut-être à cause de ses méthodes musclées. Il décide alors de venger le meurtre du jeune Bensoussan en traquant les commanditaires de son assassinat : deux trafiquants de drogue de Barbès. Il reçoit l''aide de Lola (Agnès Soral), une jeune punk ayant eu une relation avec Bensoussan, qui veut le sauver de son désespoir.',
   1983, 'FR', '2019-10-04 15:12:41'),
  ('Banzaï', 'Banzaï',
   'Michel Bernardin est employé à Planète Assistance, société qui aide les Français en difficulté à l''étranger. Sa fiancée Isabelle va quitter son emploi d''hôtesse de l''air pour passer plus de temps avec lui grâce à son amie Sophia qui lui a trouvé un emploi dans son agence de voyages. Mais Isabelle est obligée de rester encore quelques temps à son poste pour quelques vols et va le cacher à son fiancé.',
   1983, 'FR', '2019-10-04 15:23:19'),
  ('Inspecteur Labavure', 'Inspecteur Labavure',
   'Fils d''un héros de la police abattu par Pierrot le fou, Michel Clément (Coluche) est loin d''égaler son père Jules. Encouragé par sa mère (Marthe Villalonga) à suivre les traces paternelles, il obtient de justesse son diplôme d''inspecteur de police (par indulgence du jury) et se retrouve stagiaire à la PJPP (Police Judiciaire de la Préfecture de Police). Là, sa maladresse lui cause de nombreux problèmes, mais malgré cela, Michel rêve d''arrêter l''ennemi public no 1, l''impitoyable Roger Morzini (Gérard Depardieu). Celui-ci, trouvant son visage bien trop connu, subit une opération de chirurgie esthétique.

Une journaliste ambitieuse, Marie-Anne Prossant (Dominique Lavanant), fille d''un directeur de journal, nargue Morzini en réclamant une interview de lui, ce qui lui vaut d''être mise sous protection policière. Michel Clément assurera officiellement et visiblement cette tâche pour détourner l''attention, tandis que des policiers plus compétents assureront cette sécurité cachés, attendant que Morzini s''attaque à elle pour l''arrêter.

Morzini, rendu méconnaissable par son opération, se fait passer pour un écrivain auteur de romans policiers dénommé Antoine Collard et se lie dʼamitié avec Michel, lequel, en toute naïveté, va lui révéler tous les secrets de la police pour ne pas être capturé.

Prétextant faire des recherches pour son prochain roman, Morzini réussit à abuser de la naïveté de Michel pour approcher Marie-Anne Prossant et lʼenlever, au nez et à la barbe des policiers, qui surveillent pendant ce temps Michel dans sa voiture. Morzini, qui a emmené son otage dans une maison abandonnée, réclame une rançon au père de Marie-Anne Prossant. Les policiers arrêtent Michel, soupçonné dʼêtre le complice de Morzini. Ils sʼarrangent pour que celui-ci sʼéchappe du commissariat afin de le suivre, à lʼaide dʼun émetteur quʼils ont dissimulé dans la doublure de son costume. Mais Michel se rendra compte de la présence de lʼémetteur et donnera ses habits à un clochard, ce qui fera échouer la filature.

Avec quelques amis, Michel prend les choses en main et enlève la mère de Morzini. Celle-ci le tuyaute sur lʼendroit où se trouve probablement son fils. Michel sʼy rend et confronte Morzini, qui refuse de relâcher son otage. Michel décide alors dʼattaquer la maison à lʼaide dʼune pelle mécanique et libère lui-même Marie-Anne Prossant.',
   1980, 'FR', '2019-10-04 15:23:19'),

  ('Le Grand Bleu', 'Le Grand Bleu',
   'Jacques Mayol et Enzo Molinari se connaissent depuis l''enfance, dans les années 1960. Ils ont grandi ensemble en Grèce et partagent depuis toujours leur passion pour la mer. Mais à la suite de l''accident au cours d''une plongée et du décès de son père, Jacques revient en France. Vingt ans ont passé, mais la rivalité entre les deux hommes existe toujours. Le championnat du monde d''apnée No Limit à Taormina en Sicile à la fin des années 1980 est l''occasion pour les deux hommes de se retrouver et d''explorer un monde que personne ne connaît mieux qu''eux.

 En Grèce, en 1965, les amis de Jacques lui montrent une pièce d''or sous l''eau. Il se prépare à plonger mais Enzo est plus rapide. Le lendemain, le père de Jacques se noie lors d''une expérience en plongée.

 En Sicile en 1988, Enzo est demandé en renfort pour sauver un plongeur coincé sous une épave. Il plonge dans l''eau sans respirateur et parvient à sauver le plongeur. Il demande ensuite à son frère Roberto de trouver Jacques. Au Pérou, Johanna Baker croise Jacques Mayol et, avec le Dr Lawrence, regarde une expérience quand Jacques plonge dans le lac glacé sans respirateur. La jeune femme lui apporte du café, Jacques revient plus tard lui offrir un cadeau afin de la remercier. De retour en France, Enzo trouve Jacques et le convie au championnat à Taormina. A New York, Johanna découvre que l''appartement qu''elle partage avec sa colocataire a été cambriolé. Elle apprend par le docteur Lawrence que Jacques est en Sicile et décide de s''y rendre.

 Sur place, Enzo et Jacques se retrouvent, le premier réprimande le réceptionniste de l''hôtel. Tandis qu''ils bavardent, Johanna les trouve et ils sympathisent ensemble. Lors d''une soirée, les deux hommes décident de voir qui tient le plus longtemps sous l''eau. Ils finissent sur un brancard. Johanna s''occupe de Jacques. Le lendemain, Enzo se voit interdit d''aller plonger et malgré les conseils de son médecin, il se lance. Un soir, le trio libère un dauphin enfermé dans un bassin. A l''entraînement d''apnée, Jacques plonge à plus de 110 m de profondeur. Dans la soirée, il entretient une liaison avec Johanna avec qui il passe la nuit. En pleine nuit, il sort, plonge dans la mer et nage avec un dauphin jusqu''au petit matin. Johanna comprend qu''il préfère passer du temps sous l''eau qu''être avec des femmes et décide de partir à New York. Une sorte d''ascenseur emmène Enzo, Jacques et un Belge à bord pour l''entraînement. Jacques et Johanna se retrouvent. Enzo fait 115 mètres, 4 minutes et 50 secondes en apnée. Jacques fait 120 mètres. La veille de la compétition, Johanna veut parler d''avenir avec Jacques mais il ne semble pas partager son opinion. Le jour de la compétition, Enzo fait tout pour battre Jacques, il va descendre même le plus profond possible, même à y risquer sa vie. Le docteur Lawrence veut arrêter la compétition car il estime que c''est trop dangereux mais Enzo refuse de l''écouter. Il reste plus longtemps et plus profond. Une fois remonté, il se meurt et demande à son ami de le laisser mourir au fond de la mer. Dévasté par la mort de son ami, il se mure dans le mutisme. Johanna apprend qu''elle est enceinte. Elle va voir Jacques qui fait une petite crise, elle le suit et tente de le raisonner. Elle lui avoue même sa grossesse mais il plonge quand même et - à la profondeur maximale qu''autorise la compétition - il quitte la lumière pour rejoindre un dauphin dans la noirceur des profondeurs.',

   ('Le dîner de cons', 'Le dîner de cons', 'Tous les mercredis, Pierre Brochant et ses amis organisent un dîner où chacun doit amener un con. Celui qui a trouvé le plus spectaculaire est declaré vainqueur. Ce soir, Brochant exulte, il est sur d''avoir trouvé la perle rare, un con de classe mondiale: Francois Pignon, comptable au ministère des Finances et passionné de modèles réduits en allumettes. Ce qu''il ignore c''est que Pignon est passe maître dans l''art de déclencher des catastrophes.', 1998, 'FR', '2019-10-09 07:32:31', 0),
('La Chèvre', 'La Chèvre', 'La fille du grand PDG Bens, très malchanceuse, se fait enlever alors qu''elle est en vacances au Mexique. Pour la retrouver, son père, conseillé par son psychologue d''entreprise, utilise un de ses employés aussi malchanceux qu''elle, François Perrin, comptable, dans l''espoir qu''il lui arrive les mêmes malheurs qu''à sa fille et qu''il la retrouve. Le détective privé Campana, chargé de l''enquête malgré son scepticisme, devra donc faire équipe avec Perrin, ce qui ne sera pas de tout repos car non seulement il devra en rattraper les innombrables bourdes mais en plus il devra bien souvent partager sa poisse et même, de temps à autre, en subir seul les conséquences, au point que son cartésianisme initial n''en sortira pas intact.

', 1981, 'FR', '2019-10-09 07:42:10', 0),
   ('Tais-toi!', 'Tais-toi!', 'Quentin de Montargis se fait arrêter et se retrouve en prison. Ruby, un criminel, recherché par la police, est emprisonné après avoir volé l''argent de son ancien patron Vogel. Il souhaite venger la mort de la femme qu''il aimait et que Vogel, son mari, a tuée.

En prison, le criminel ne parle plus à personne. Apprenant que Quentin a rendu fous plusieurs détenus pendant les dernières semaines, le commissaire Vernet voit là la façon de faire parler Ruby. Mais Ruby tente de se suicider, Quentin pensant avoir trouvé un ami en fait de même pour le retrouver à l''hôpital, puis le suit jusqu''à l''asile de fous, où il contacte un ancien collègue du bâtiment, Martineau. À la demande de Quentin, Martineau le fait évader avec Ruby qui s’apprêtait à quitter l''asile d''une façon plus discrète. Quentin et Ruby se retrouvent alors en pyjama d''hôpital au milieu de la ville, avec les hommes de Vogel à leur poursuite. Quentin aide Ruby à leur échapper et à voler une voiture, mais malgré cela Ruby soupçonne Quentin de travailler pour le commissaire Vernet. Ruby découvre la vraie nature de Quentin : un con certes, mais quelqu''un de très attachant et doté de vraies qualités humaines.

Ruby et Quentin arrivent à s''introduire chez Vogel. Cependant, Vogel blesse par balle Ruby, et c''est finalement Quentin qui tue Vogel pour protéger Ruby. La police arrive sur les lieux quelques instants après.', 2002, 'FR', '2019-10-09 07:42:10', 0));


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
  (9,13),
  (16,14),
  (16,15),
  (17,15);

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
  (24, 15);

INSERT CORRESPOND (IdGenre, IdFilm)
VALUES
  (8, 1),
  (5, 1),
  (2, 2),
  (5, 2),
  (0, 3),
  (9, 3),
  (3, 3),
  (0, 4),
  (3, 4),
  (3, 5),
  (4, 5),
  (9, 6),
  (7, 6),
  (3, 7),
  (7, 7),
  (3, 8),
  (7, 8),
  (0, 8),
  (3, 9),
  (0, 9),
  (9, 10),
  (7, 10),
  (0, 11),
  (7, 11),
  (9, 12),
  (4, 12),
  (3, 12),
  (7, 13),
  (7, 14),
  (7, 15);
