CREATE TABLE IF NOT EXISTS user_permission
(
    user_id bigint(20) NOT NULL,
    permission_id bigint(20) NOT NULL,
    PRIMARY KEY (user_id, permission_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_permission_id FOREIGN KEY (permission_id) REFERENCES permission (id)
);