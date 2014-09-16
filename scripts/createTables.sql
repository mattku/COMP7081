DROP TABLE IF EXISTS users;

CREATE TABLE users 
(
    `user_id`    varchar(20) NOT NULL,
    `password`   varchar(32) NOT NULL,
    `role`       varchar(20) DEFAULT 'user',
    PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;