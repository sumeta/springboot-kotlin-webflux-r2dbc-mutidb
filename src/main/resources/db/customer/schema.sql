CREATE TABLE CUSTOMER (
    IDENTIFIER VARCHAR(20) NOT NULL PRIMARY KEY,
    LAST_NAME VARCHAR(255) NOT NULL,
    FIRST_NAME VARCHAR(255) NOT NULL,
    CREATED_BY VARCHAR(255) NOT NULL,
    CREATED_DATE DATETIME NOT NULL,
    LAST_MODIFIED_BY varchar(255) NOT NULL,
    LAST_MODIFIED_DATE DATETIME NOT NULL
);