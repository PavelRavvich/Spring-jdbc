CREATE DATABASE spring_jdbc;

CREATE TABLE IF NOT EXISTS items (
  id SERIAL PRIMARY KEY ,
  name VARCHAR(10) ,
  description VARCHAR(20)
);