-- Drop user first if they exist
DROP USER if exists 'springgrocery'@'%' ;

-- Now create user with prop privileges
CREATE USER 'springgrocery'@'%' IDENTIFIED BY 'springgrocery';

GRANT ALL PRIVILEGES ON * . * TO 'springgrocery'@'%';