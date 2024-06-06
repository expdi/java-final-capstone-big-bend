
set ROLE capstone;

-- clear DB
DROP TABLE IF EXISTS artist CASCADE;
DROP TABLE IF EXISTS track CASCADE;
DROP TABLE IF EXISTS album CASCADE;

CREATE TABLE artist
(
    id              serial PRIMARY KEY NOT NULL
,   music_genre     varchar(255)
,   name            varchar(255)
,   nationality     varchar(255)
);

CREATE TABLE album
(
    id_album        serial PRIMARY KEY NOT NULL
,   title           varchar(255)
,   issue_date       DATE
);

CREATE TABLE track
(
    id                      serial PRIMARY KEY NOT NULL
    ,   duration_in_seconds     int
    ,   issue_date              DATE
    ,   language                varchar(255)
    ,   price                   double precision
    ,   title                   varchar(255)
    ,   track_media_type        smallint
    ,   artist_id               integer
    ,   album_id                integer
    ,   FOREIGN KEY (artist_id) REFERENCES artist(id)
    ,   FOREIGN KEY (album_id)  REFERENCES album(id_album)
);

