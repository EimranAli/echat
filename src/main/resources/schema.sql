CREATE TABLE IF NOT EXISTS user(
    email varchar(41) NOT NULL,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    hashedPassword varchar(128) NOT NULL
    PRIMARY KEY (email)
)