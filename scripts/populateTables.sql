TRUNCATE TABLE users;

INSERT INTO users(user_id, password, `role`)
    VALUES('user', md5('password'), 'user');
INSERT INTO users(user_id, password, `role`)
    VALUES('admin', md5('password'), 'admin');
