CREATE DATABASE jee;
USE jee;

CREATE TABLE `consommateurs` (
  `consommateur_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `email` text,
  `mobile` int(10) DEFAULT NULL,
  PRIMARY KEY (`consommateur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `commandes` (
  `commande_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commande_date` datetime(6) DEFAULT NULL,
  `consommateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`commande_id`),
  KEY `FK4cv1xget1o25f553s4nc9b4vc` (`consommateur_id`),
  CONSTRAINT `FK4cv1xget1o25f553s4nc9b4vc` FOREIGN KEY (`consommateur_id`) REFERENCES `consommateurs` (`consommateur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `produits` (
  `produit_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `produit` text,
  PRIMARY KEY (`produit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `commandes_produits` (
  `produit_id` bigint(20) NOT NULL,
  `commande_id` bigint(20) NOT NULL,
  KEY `FKa6s42a475tek1qc9bvg7uve7w` (`produit_id`),
  KEY `FKmmcl8naq7bh4ya2jr5lknrvvx` (`commande_id`),
  CONSTRAINT `FKa6s42a475tek1qc9bvg7uve7w` FOREIGN KEY (`produit_id`) REFERENCES `produits` (`produit_id`),
  CONSTRAINT `FKmmcl8naq7bh4ya2jr5lknrvvx` FOREIGN KEY (`commande_id`) REFERENCES `commandes` (`commande_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into consommateurs(prenom, nom, email, mobile) values 
("prenom1", "nom1", "email1@truc.com", 1234567890),
("prenom2", "nom2", "email2@truc.com", 1234567891);

insert into commandes(commande_date, consommateur_id) values 
(CAST("2023-01-01" as date), 1),
(CAST("2023-01-02" as date), 1),
(CAST("2023-01-03" as date), 2);

insert into produits(produit) values ("oeuf"), ("lait"), ("sucre"), ("farine"), ("pizza");
insert into commandes_produits(commande_id, produit_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 5), (2, 1);