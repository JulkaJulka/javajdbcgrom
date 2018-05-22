CREATE TABLE USER_AP(
ID NUMBER,
CONSTRAINT USER_ID_PK PRIMARY KEY (ID),
USER_NAME NVARCHAR2(50) NOT NULL,
PASSWORD NVARCHAR2(50) NOT NULL,
COUNTRY NVARCHAR2(50),
USER_TYPE NVARCHAR2(50),
CONSTRAINT USER_TYPE_CH CHECK(USER_TYPE IN('ADMIN','USER')),
);
