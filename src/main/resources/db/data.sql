insert into USERS_ (ID, LOGIN, PASSWORD , NAME) values (1, 'admin', '$2a$10$wT8L/OR4oBfgFBB3L/BSku9NUd6UDS17LKgnpsdwARlNzGDDzOtJO', 'admin');
insert into USERS_ (ID, LOGIN, PASSWORD , NAME) values (2, 'user', '$2a$10$bCS55N3HvGJf54q2IZfbz./r5Tuf8t0afr6ANpUCw/LHpuU8O2ME2', 'user');


insert into USER_ROLE (ID, NAME) values(1, 'ADMIN');
insert into USER_ROLE (ID, NAME) values(2, 'CUSTOMER');


insert into USERS_TO_USER_ROLE (USER_ID, USER_ROLE_ID) values(1, 1);
insert into USERS_TO_USER_ROLE (USER_ID, USER_ROLE_ID) values(2, 2);


insert into ORDERS (DESCRIPTION, USER_ID) values ('change cooler on cpu', 1);
insert into ORDERS (DESCRIPTION, USER_ID) values ('change thermal grease', 1);
insert into ORDERS (DESCRIPTION, USER_ID) values ('change display on samsung galaxy a50', 2);
insert into ORDERS (DESCRIPTION, USER_ID) values ('change power supply', 2);
insert into ORDERS (DESCRIPTION, USER_ID) values ('repair power connector', 2);
