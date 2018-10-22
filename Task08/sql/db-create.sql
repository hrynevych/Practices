--<ScriptOptions statementTerminator=";"/>
CREATE DATABASE newbase;

CREATE TABLE users (
	id INT NOT NULL,
	login VARCHAR(30) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE users_groups (
	user_id INT NOT NULL,
	group_id INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE groups (
	id INT NOT NULL,
	name VARCHAR(30) NOT NULL
) ENGINE=InnoDB;

