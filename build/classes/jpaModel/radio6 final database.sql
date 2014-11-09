-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 24 Août 2014 à 20:09
-- Version du serveur: 5.5.38-0ubuntu0.14.04.1
-- Version de PHP: 5.5.9-1ubuntu4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `radio6`
--

-- --------------------------------------------------------

--
-- Structure de la table `Chanson`
--

CREATE TABLE IF NOT EXISTS `Chanson` (
  `numChanson` int(11) NOT NULL AUTO_INCREMENT,
  `cheminFichier` varchar(255) DEFAULT NULL,
  `classification` int(11) DEFAULT NULL,
  `nomChanson` varchar(255) DEFAULT NULL,
  `nomFichier` varchar(255) DEFAULT NULL,
  `periode` int(11) DEFAULT NULL,
  `nomChanteur` varchar(255) DEFAULT NULL,
  `nomPays` varchar(255) DEFAULT NULL,
  `nomSymbole` varchar(255) DEFAULT NULL,
  `nomTheme` varchar(255) DEFAULT NULL,
  `longueur` int(11) DEFAULT NULL,
  PRIMARY KEY (`numChanson`),
  KEY `FK_Chanson_nomPays` (`nomPays`),
  KEY `FK_Chanson_nomTheme` (`nomTheme`),
  KEY `FK_Chanson_nomSymbole` (`nomSymbole`),
  KEY `FK_Chanson_nomChanteur` (`nomChanteur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `Chanson`
--

INSERT INTO `Chanson` (`numChanson`, `cheminFichier`, `classification`, `nomChanson`, `nomFichier`, `periode`, `nomChanteur`, `nomPays`, `nomSymbole`, `nomTheme`, `longueur`) VALUES
(6, '/home/moez/Téléchargements/MUSIC MOEZ/Arch Enemy/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/01 - Blood on Your Hands.mp3', 5, 'Blood In Your Hands', '01 - Blood on Your Hands.mp3', 2007, 'Arch Enemy', 'Algérie', 'TN', 'Politique', NULL),
(7, '/home/moez/Téléchargements/MUSIC MOEZ/Arch Enemy/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/10 - The Great Darkness.mp3', 6, 'The Great Darkness', '10 - The Great Darkness.mp3', 2007, 'Arch Enemy', 'Albanie', 'OR', 'Politique', NULL),
(8, '/home/moez/Téléchargements/MUSIC MOEZ/Arch Enemy/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/06 - Rise of the Tyrant.mp3', 6, 'Rise of the Tyrant', '06 - Rise of the Tyrant.mp3', 2007, 'Arch Enemy', 'Allemagne', 'OX', 'Femme', NULL),
(9, '/home/moez/Téléchargements/MUSIC MOEZ/Arch Enemy/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/02 - The Last Enemy.mp3', 5, 'The Last Enemy', '02 - The Last Enemy.mp3', 2007, 'Arch Enemy', 'Angola', 'OX', 'Femme', 281),
(12, '/home/moez/Téléchargements/MUSIC MOEZ/Arch Enemy/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/Arch Enemy - Rise Of The Tyrant (2007)By_rafaveganhc@hotmail.com/09 - Night Falls Fast.mp3', 2, 'Night Falls Fast', '09 - Night Falls Fast.mp3', 2007, 'Arch Enemy', 'Antigua-et-Barbuda', 'OX', 'Engagé', 251),
(13, '/home/moez/Téléchargements/MUSIC MOEZ/General Music/Just Can''t Get Enough - Black Eyed Peace.mp3', 6, 'Just Can''t Get Enough', 'Just Can''t Get Enough - Black Eyed Peace.mp3', 2010, 'Black Eyed Peas', 'Allemagne', 'OX', 'Engagé', 219);

-- --------------------------------------------------------

--
-- Structure de la table `Chanson_Genre`
--

CREATE TABLE IF NOT EXISTS `Chanson_Genre` (
  `genres_nomGenre` varchar(255) NOT NULL,
  `chansons_numChanson` int(11) NOT NULL,
  PRIMARY KEY (`genres_nomGenre`,`chansons_numChanson`),
  KEY `FK_Chanson_Genre_chansons_numChanson` (`chansons_numChanson`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Chanson_Genre`
--

INSERT INTO `Chanson_Genre` (`genres_nomGenre`, `chansons_numChanson`) VALUES
('Deathmetal', 12),
('Deathmetal', 13);

-- --------------------------------------------------------

--
-- Structure de la table `Chanteur`
--

CREATE TABLE IF NOT EXISTS `Chanteur` (
  `nomChanteur` varchar(255) NOT NULL,
  PRIMARY KEY (`nomChanteur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Chanteur`
--

INSERT INTO `Chanteur` (`nomChanteur`) VALUES
(''),
('Adele'),
('Akon'),
('Arch Enemy'),
('Black Eyed Peas'),
('Britney Spears'),
('Cascada'),
('Celine Dion '),
('Chris Brown'),
('Eminem'),
('Enrique iglesias'),
('Ester Dean'),
('Fayrouz'),
('Gladiator'),
('instrumental music'),
('Kanye West'),
('Karmin'),
('Laura Pausini'),
('Metallica'),
('Metallica&Symphony'),
('Mouzika 02 | Basshunter'),
('Nawel Zoghbi'),
('Nelly Furtado'),
('Om Kolthoum'),
('Opeth'),
('Rihanna'),
('Sabeh Fakhri'),
('SBS & Metallica'),
('SBS_ENIT & Metallica'),
('SBS_ENIT &Metallica'),
('Sean Paul'),
('System Of A Down'),
('Taio Cruz Feat. Flo Rida (BY @ello-)'),
('Taylor Swift'),
('Umberto Tozzi'),
('Usher feat. Enrique Iglesias');

-- --------------------------------------------------------

--
-- Structure de la table `Genre`
--

CREATE TABLE IF NOT EXISTS `Genre` (
  `nomGenre` varchar(255) NOT NULL,
  PRIMARY KEY (`nomGenre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Genre`
--

INSERT INTO `Genre` (`nomGenre`) VALUES
('Deathmetal');

-- --------------------------------------------------------

--
-- Structure de la table `Pays`
--

CREATE TABLE IF NOT EXISTS `Pays` (
  `nomPays` varchar(255) NOT NULL,
  PRIMARY KEY (`nomPays`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Pays`
--

INSERT INTO `Pays` (`nomPays`) VALUES
('Afghanistan'),
('Afrique du Sud'),
('Albanie'),
('Algérie'),
('Allemagne'),
('Andorre'),
('Angola'),
('Antigua-et-Barbuda'),
('Arabie saoudite'),
('Argentine'),
('Arménie'),
('Australie'),
('Autriche'),
('Azerbaïdjan'),
('Bahamas'),
('Bahreïn'),
('Bangladesh'),
('Barbade'),
('Belgique'),
('Belize'),
('Bénin'),
('Bhoutan'),
('Biélorussie'),
('Bolivie'),
('Bosnie-Herzégovine'),
('Botswana'),
('Brésil'),
('Brunei Darussalam'),
('Bulgarie'),
('Burkina Faso'),
('Burundi'),
('Cambodge'),
('Cameroun'),
('Canada'),
('Cap-Vert'),
('Chili'),
('Chine'),
('Chypre'),
('Colombie'),
('Comores'),
('Congo'),
('Costa Rica'),
('Côte d’Ivoire'),
('Croatie'),
('Cuba'),
('Danemark'),
('Djibouti'),
('Dominique'),
('Égypte'),
('Émirats arabes unis'),
('Équateur'),
('Erythrée'),
('Espagne'),
('Estonie'),
('États-Unis d’Amérique'),
('Éthiopie'),
('Fédération de Russie'),
('Fiji'),
('Finlande'),
('France'),
('Gabon'),
('Gambie'),
('Géorgie'),
('Ghana'),
('Grèce'),
('Grenade'),
('Guatemala'),
('Guinée'),
('Guinée Équatoriale'),
('Guinée-Bissau'),
('Guyana'),
('Haïti'),
('Honduras'),
('Hongrie'),
('Îles Marshall'),
('Îles Salomon'),
('Inde'),
('Indonésie'),
('Irak'),
('Iran'),
('Irlande'),
('Islande'),
('Israël'),
('Italie'),
('Jamaïque'),
('Japon'),
('Jordanie'),
('Kazakhstan'),
('Kenya'),
('Kirghizistan'),
('Kiribati'),
('Koweït'),
('Lesotho'),
('Lettonie'),
('Liban'),
('Libéria'),
('Libye'),
('Lituanie'),
('Luxembourg'),
('Madagascar'),
('Malaisie'),
('Malawi'),
('Maldives'),
('Mali'),
('Malte'),
('Maroc'),
('Martinique'),
('Maurice'),
('Mauritanie'),
('Mexique'),
('Micronésie'),
('Moldavie'),
('Monaco'),
('Mongolie'),
('Monténégro'),
('Mozambique'),
('Myanmar'),
('Namibie'),
('Nauru'),
('Népal'),
('Nicaragua'),
('Niger'),
('Nigéria'),
('Norvège'),
('Nouvelle-Zélande'),
('Oman'),
('Ouganda'),
('Ouzbékistan'),
('Pakistan'),
('Palaos'),
('Panama'),
('Papouasie-Nouvelle-Guinée'),
('Paraguay'),
('Pays-Bas'),
('Pérou'),
('Philippines'),
('Pologne'),
('Portugal'),
('Qatar'),
('République arabe syrienne'),
('République centrafricaine'),
('République de Corée'),
('République démocratique du Congo'),
('République démocratique populaire du Laos'),
('République dominicaine'),
('République populaire démocratique de Corée'),
('République tchèque'),
('République yougoslave de Macédoine'),
('République-Unie de Tanzanie'),
('Roumanie'),
('Royaume-Uni de Grande-Bretagne et d''Irlande du Nord'),
('Rwanda'),
('Saint-Kitts-et-Nevis'),
('Saint-Marin'),
('Saint-Vincent-et-les-Grenadines'),
('Sainte-Lucie'),
('Salvador'),
('Samoa'),
('Sao Tomé-et-Principe'),
('Sénégal'),
('Serbie'),
('Seychelles'),
('Sierra Leone'),
('Singapour'),
('Slovaquie'),
('Slovénie'),
('Somalie'),
('Soudan'),
('Sri Lanka'),
('Suède'),
('Suisse'),
('Suriname'),
('Swaziland'),
('Tadjikistan'),
('Tchad'),
('Thaïlande'),
('Timor oriental'),
('Togo'),
('Tonga'),
('Trinité-et-Tobago'),
('Tunisie'),
('Turkménistan'),
('Turquie'),
('Tuvalu'),
('Ukraine'),
('Uruguay'),
('Vanuatu'),
('Venezuela'),
('Viêt Nam'),
('Yémen'),
('Zambie'),
('Zimbabwe');

-- --------------------------------------------------------

--
-- Structure de la table `Symbole`
--

CREATE TABLE IF NOT EXISTS `Symbole` (
  `nomSymbole` varchar(255) NOT NULL,
  PRIMARY KEY (`nomSymbole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Symbole`
--

INSERT INTO `Symbole` (`nomSymbole`) VALUES
('OR'),
('OX'),
('TN');

-- --------------------------------------------------------

--
-- Structure de la table `Theme`
--

CREATE TABLE IF NOT EXISTS `Theme` (
  `nomTheme` varchar(255) NOT NULL,
  PRIMARY KEY (`nomTheme`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Theme`
--

INSERT INTO `Theme` (`nomTheme`) VALUES
('Engagé'),
('Femme'),
('house'),
('Politique');

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE IF NOT EXISTS `Utilisateur` (
  `login` varchar(255) NOT NULL,
  `pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`login`, `pass`) VALUES
('admin', 'admin');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Chanson`
--
ALTER TABLE `Chanson`
  ADD CONSTRAINT `FK_Chanson_nomChanteur` FOREIGN KEY (`nomChanteur`) REFERENCES `Chanteur` (`nomChanteur`),
  ADD CONSTRAINT `FK_Chanson_nomPays` FOREIGN KEY (`nomPays`) REFERENCES `Pays` (`nomPays`),
  ADD CONSTRAINT `FK_Chanson_nomSymbole` FOREIGN KEY (`nomSymbole`) REFERENCES `Symbole` (`nomSymbole`),
  ADD CONSTRAINT `FK_Chanson_nomTheme` FOREIGN KEY (`nomTheme`) REFERENCES `Theme` (`nomTheme`);

--
-- Contraintes pour la table `Chanson_Genre`
--
ALTER TABLE `Chanson_Genre`
  ADD CONSTRAINT `FK_Chanson_Genre_chansons_numChanson` FOREIGN KEY (`chansons_numChanson`) REFERENCES `Chanson` (`numChanson`),
  ADD CONSTRAINT `FK_Chanson_Genre_genres_nomGenre` FOREIGN KEY (`genres_nomGenre`) REFERENCES `Genre` (`nomGenre`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
