CREATE TABLE IF NOT EXISTS user
(
    id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name varchar(255) DEFAULT NULL UNIQUE KEY,
    full_name varchar(255) DEFAULT NULL,
    password varchar(255) DEFAULT NULL,
    account_non_expired bit(1) DEFAULT NULL,
    account_non_locked bit(1) DEFAULT NULL,
    credentials_non_expired bit(1) DEFAULT NULL,
    enabled bit(1) DEFAULT NULL
);