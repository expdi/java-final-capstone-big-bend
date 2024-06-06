
INSERT INTO artist (name, music_genre, nationality)
VALUES ('Taylor Swift', 'POP', 'USA'),
       ('Seu Jorge', 'SAMBA', 'BRA'),
       ('Daft Punk', 'EDM', 'FRA'),
       ('Natalia Lafourcade', 'ALTERNATIVE', 'MEX'),
       ('Sigur Ros', 'INDIE', 'ISL'),
       ('Hoàng Thùy Linh', 'POP', 'VNM');

INSERT INTO album (title, issue_date)
VALUES ('Speak Now', '2010-10-25'),
       ('The Life Aquatic Studio Sessions', '2005-10-22'),
       ('Random Access Memories', '2013-5-13'),
       ('Hasta la raíz', '2015-03-17'),
       ('Takk...', '2005-9-12'),
       ('See Tình', '2022-02-20');

INSERT INTO track (title, issue_date, duration_in_seconds, track_media_type, language, artist_id, album_id)
VALUES ('Back To December', '2010-10-25', 240, 1, 'English', 1, 1),
       ('Speak Now', '2010-10-25', 200, 1, 'English', 1, 1),
       ('Life on Mars?', '2005-10-22', 271, 2, 'Portugese', 2, 2),
       ('Oh! You Pretty Things', '2005-10-22', 211, 2, 'Portugese', 2, 2),
       ('Lose Yourself To Dance', '2013-5-13', 720, 3, 'English', 3, 3),
       ('Doin'' It Right', '2013-5-13', 320, 2, 'English', 3, 3),
       ('Hasta la raíz', '2015-03-17', 100, 3, 'Spanish', 4, 4),
       ('Hoppípolla', '2005-9-12', 230, 0, 'Icelandic', 5, 5),
       ('See Tình', '2022-02-20', 180, 2, 'Vietnamese', 6, 6);

