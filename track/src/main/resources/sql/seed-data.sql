
INSERT INTO artist (name, music_genre, nationality)
VALUES ('Taylor Swift', 'POP', 'USA'),
       ('Daft Punk', 'EDM', 'FRA');

INSERT INTO album (title, issue_date)
VALUES ('Speak Now', '2010-10-25'),
       ('Random Access Memories', '2013-5-13');

INSERT INTO track (title, issue_date, duration_in_seconds, track_media_type, language, artist_id, album_id)
VALUES ('Enchanted', '2010-10-25', 240, 1, 'English', 1, 1),
       ('Doin'' It Right', '2013-5-13', 320, 2, 'English', 2, 2);

