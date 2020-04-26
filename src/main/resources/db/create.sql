SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS heroes (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  age INT,
  power VARCHAR,
  weakness VARCHAR
);
CREATE TABLE IF NOT EXISTS squads(
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  cause VARCHAR,
  max_size INT
);