USE adlister_db;

DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;
drop table if exists categories;
drop table if exists ad_cat;

CREATE TABLE users
(
    id       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(240) NOT NULL,
    email    VARCHAR(240) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE ads
(
    id           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id      INT UNSIGNED NOT NULL,
    title        VARCHAR(240) NOT NULL,
    description  TEXT         NOT NULL,
    date_created TIMESTAMP    NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
);

create table categories
(
    id   int unsigned not null auto_increment primary key,
    name varchar(50)
);

create table ad_cat
(
    ad_id  int unsigned not null,
    cat_id int unsigned not null,
    foreign key (ad_id) references ads (id),
    foreign key (cat_id) references categories (id)
)
