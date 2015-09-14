CREATE DATABASE sardb;
CREATE USER 'sar'@'localhost' IDENTIFIED BY 'sar1234';
GRANT ALL PRIVILEGES ON sardb.* to sar@localhost; 