CREATE TABLE ROOM(
ROOM_ID NUMBER,
CONSTRAINT ROOM_ID_PK PRIMARY KEY(ROOM_ID),
NUMBER_OF_GUEST NUMBER,
PRICE NUMBER(10,2),
BREAKFAST_INCLUDED NUMBER(1) DEFAULT 0,
CONSTRAINT BRC_INCL_RM  CHECK (BREAKFAST_INCLUDED IN (0, 1)),
PETS_ALLOWED NUMBER(1) DEFAULT 0,
CONSTRAINT PETS_ALLOWED_RM  CHECK (PETS_ALLOWED IN(0, 1)),
DATE_AVAILABLE_FROM DATE,
HOTEL_ID NUMBER,
CONSTRAINT ROOM_HOTEL_FK FOREIGN KEY(HOTEL_ID) REFERENCES HOTEL_BK(ID) ON DELETE SET NULL
);

