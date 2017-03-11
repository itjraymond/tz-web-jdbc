-- to have this file automatically picked up by Spring Boot, rename it to schema.sql
drop table if exists TEMPORALS;

create table TEMPORALS (
	ID				BIGINT PRIMARY KEY,
	DOB_DT			DATE NULL,
	OPENING_TIME	TIME NULL,
	TIMESTAMP_LC	TIMESTAMP NULL,
	TIMESTAMP_TS	TIMESTAMP NULL,
	ZONE_ID			VARCHAR(100),
	ZONE_OFFSET		VARCHAR(50)
);


insert into TEMPORALS (ID, DOB_DT, OPENING_TIME, TIMESTAMP_LC, TIMESTAMP_TS, ZONE_ID, ZONE_OFFSET)
values (1000, 
        TO_DATE('1967-02-16','YYYY-MM-DD'), 
        TO_DATE('03:40:23', 'HH24:MI:ss'),
        TO_DATE('2004-05-22 13:30:40', 'YYYY-MM-DD HH24:MI:ss'),
        TO_DATE('2017-03-09 13:56:50', 'YYYY-MM-DD HH24:MI:ss'),
        'America/Edmonton', '-07:00');

  

insert into TEMPORALS (ID, DOB_DT, OPENING_TIME, TIMESTAMP_LC, TIMESTAMP_TS, ZONE_ID, ZONE_OFFSET)
values (1001,
        TO_DATE('1995-09-29','YYYY-MM-DD'), 
        TO_DATE('03:40:23', 'HH24:MI:ss'),
        TO_DATE('2005-06-23 14:40:50', 'YYYY-MM-DD HH24:MI:ss'),
        TO_DATE('2017-03-09 12:46:40', 'YYYY-MM-DD HH24:MI:ss'),
        'America/Edmonton', '-06:00');
                