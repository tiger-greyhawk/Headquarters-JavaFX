insert into users (id, login, password) values
    (1, 'user1', '123'),
    (2, 'user2', '123'),
    (3, 'user3', '123'),
    (4, 'user4', '123'),
    (5, 'admin1', '123'),
    (6, 'admin2', '123');

insert into players (id, user_id, nick) values
    (1, 1, 'Player1'),
    (2, 1, 'Player2'),
    (3, 2, 'Player3'),
    (4, 2, 'Player4'),
    (5, 3, 'Player5'),
    (6, 3, 'Player6'),
    (7, 4, 'Player7'),
    (8, 4, 'Player8'),
    (9, 10, 'PlayerNullUser');

insert into user_active_player (id, user_id, active_player_id) values
    (1, 1, 1),
    (2, 2, 3);

insert into factions (id, name, owner_id) values
    (1, 'The United Earth Federation', 1),
    (2, 'The Cybran Nation', 2),
    (3, 'The Aeon Illuminate', 3),
    (4, 'The Seraphim', 4);

insert into faction_players (id, faction_id, player_id) values
    (0, 1, 1),
    (1, 1, 2),
    (2, 1, 3),
    (3, 2, 4),
    (4, 2, 5),
    (5, 3, 6),
    (6, 3, 7),
    (7, 3, 8),
    (8, 3, 9);

insert into roles (id, name) values
    (1, 'ROLE_USER'),
    (2, 'ROLE_ADMIN');

insert into user_roles (user_id, role_id) values
    (1, 1),
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 2),
    (6, 2);

/*
CREATE TABLE IF NOT EXISTS acl_sid (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    principal tinyint(1) NOT NULL,
    sid varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_uk_1 (sid, principal))
    ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `acl_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_2` (`class`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `acl_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acl_object_identity` bigint(20) NOT NULL,
  `ace_order` int(11) NOT NULL,
  `sid` bigint(20) NOT NULL,
  `mask` int(11) NOT NULL,
  `granting` tinyint(1) NOT NULL,
  `audit_success` tinyint(1) NOT NULL,
  `audit_failure` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_4` (`acl_object_identity`,`ace_order`),
  --KEY `foreign_fk_5` (`sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `acl_object_identity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `object_id_class` bigint(20) NOT NULL,
  `object_id_identity` bigint(20) NOT NULL,
  `parent_object` bigint(20) DEFAULT NULL,
  `owner_sid` bigint(20) DEFAULT NULL,
  `entries_inheriting` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uk_3` (`object_id_class`,`object_id_identity`),
  --KEY `foreign_fk_1` (`parent_object`),
  --KEY `foreign_fk_3` (`owner_sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;
*/


insert into acl_sid (id, principal, sid) values
(1, 1, 'user1'),
(2, 1, 'user2'),
(3, 1, 'user3');

INSERT INTO acl_class (id, class) VALUES
(1, 'name.timoshenko.communityhelper.server.model.domain.Faction'),
(2, 'name.timoshenko.communityhelper.server.model.domain.User');
//(3, 'name.timoshenko.communityhelper.server.model.domain.Player');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 1, 0),
(2, 1, 2, NULL, 2, 0),
(3, 1, 3, NULL, 3, 0),
(4, 1, 4, NULL, 4, 0),
(5, 2, 1, NULL, 1, 0),
(6, 2, 2, NULL, 1, 0),
(7, 2, 3, NULL, 1, 0),
(8, 2, 4, NULL, 1, 0),
(9, 2, 5, NULL, 1, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 2, 1, 1, 4, 1, 1, 1),
(3, 3, 1, 1, 1, 1, 1, 1),
(4, 1, 2, 1, 2, 1, 1, 1),
(5, 2, 2, 1, 2, 1, 1, 1),
(6, 3, 2, 1, 2, 1, 1, 1),
(7, 4, 1, 1, 1, 1, 1, 1),
(8, 5, 1, 1, 1, 1, 1, 1),
(9, 6, 1, 1, 1, 1, 1, 1),
(10, 7, 1, 2, 1, 1, 1, 1),
(11, 8, 1, 1, 1, 1, 1, 1),
(12, 9, 1, 2, 1, 1, 1, 1),
(13, 7, 2, 1, 1, 1, 1, 1),
(14, 8, 2, 1, 1, 1, 1, 1),
(15, 9, 2, 1, 1, 1, 1, 1),
(28, 4, 3, 2, 1, 1, 1, 1),
(29, 5, 3, 2, 1, 1, 1, 1),
(30, 6, 3, 2, 1, 1, 1, 1),
(31, 4, 4, 2, 2, 1, 1, 1),
(32, 5, 4, 2, 2, 1, 1, 1),
(33, 6, 4, 2, 2, 1, 1, 1),
(34, 7, 3, 2, 1, 1, 1, 1),
(35, 8, 3, 2, 1, 1, 1, 1),
(36, 9, 3, 2, 1, 1, 1, 1),
(37, 7, 4, 2, 2, 1, 1, 1),
(38, 8, 4, 2, 2, 1, 1, 1),
(39, 9, 4, 2, 2, 1, 1, 1),
(40, 7, 5, 3, 1, 1, 1, 1),
(41, 8, 5, 3, 1, 1, 1, 1),
(42, 9, 5, 3, 1, 1, 1, 1);

/*
//Tables of acl from http://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/#dbschema-acl

Заготовки для таблиц spring-security-acl.

insert into acl_sid (id, principal, sid) values // уникально по sid+principal
    (1, true, "sid_name admin1(???)");

insert into acl_class (id, class) values //уникально по class
    (1, "class_name example Faction");

insert into acl_object_identity (id , object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) values  //уникально по object_id_class, object_id_identity
    (1, 1, ?, ?, 1, ?true);
    //CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
    //CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    //CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)

insert into acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) values // уникально по acl_object_identity, ace_order
    ();
    //CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    //CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
*/