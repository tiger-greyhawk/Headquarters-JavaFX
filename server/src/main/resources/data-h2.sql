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
//Tables of acl from http://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/#dbschema-acl

insert into acl_sid (id, principal, sid) values // уникально по sid+principal
    (1, true, "sid_name");

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