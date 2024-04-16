CREATE TABLE IF NOT EXISTS person
(
    id         bigint auto_increment
        primary key,
    age        int          not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null
);