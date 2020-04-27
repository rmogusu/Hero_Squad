CREATE DATABASE superhero;
\c superhero;
CREATE TABLE heroes (id SERIAL PRIMARY KEY, name VARCHAR, age INT,power VARCHAR,weakness VARCHAR, squadid INTEGER);
CREATE TABLE squads (id SERIAL PRIMARY KEY, name VARCHAR, cause VARCHAR, max_size INTEGER);
CREATE DATABASE superhero_test WITH TEMPLATE superhero;