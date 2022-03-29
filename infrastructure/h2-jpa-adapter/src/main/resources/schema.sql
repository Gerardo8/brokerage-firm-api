DROP TABLE IF EXISTS ACCOUNTS;

CREATE TABLE ACCOUNTS(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    CASH NUMERIC NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS ORDERS;

CREATE TABLE ORDERS(
    ID BIGINT NOT NULL AUTO_INCREMENT,
    OPERATION VARCHAR(255) NOT NULL,
    ISSUER_NAME VARCHAR(255) NOT NULL,
    TOTAL_SHARES INTEGER NOT NULL,
    SHARE_PRICE NUMERIC NOT NULL,
    OPERATION_DATE TIMESTAMP NOT NULL,
    ACCOUNT_ID BIGINT NOT NULL,
    FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNTS(ID),
    PRIMARY KEY (ID)
);