insert into users (id, login, password) values
    (1, 'user1', '123'),
    (2, 'user2', '123'),
    (3, 'user3', '123');

insert into players (id, user_id, nick) values
    (1, 1, 'Player1'),
    (2, 1, 'Player2'),
    (3, 2, 'Player3'),
    (4, 2, 'Player4'),
    (5, 3, 'Player5'),
    (6, 4, 'Player6'),
    (7, 5, 'Player7'),
    (8, 6, 'Player8');

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
