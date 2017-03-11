-- Note:  these are *not* UTC date/time because the driver doing the 
--        insert "assume" the input to TO_DATE is in the system timezone (which is America/Edmonton)
--        So all date/time below are to be "seen" as in timezone America/Edmonton
--        However, the driver will take this input to TO_DATE and translate it into UTC.
--		  So the date inside the database will be in UTC. (I think)
insert into TEMPORALS (ID, DOB_DT, OPENING_TIME, TIMESTAMP_LC, TIMESTAMP_TS, ZONE_ID, ZONE_OFFSET)
values (2000, 
        TO_DATE('1945-02-16','YYYY-MM-DD'), 
        TO_DATE('03:40:23', 'HH24:MI:ss'),
        TO_DATE('2000-05-22 13:30:40', 'YYYY-MM-DD HH24:MI:ss'),
        TO_DATE('2017-03-09 13:56:50', 'YYYY-MM-DD HH24:MI:ss'),
        'America/Edmonton', '-07:00');

  

insert into TEMPORALS (ID, DOB_DT, OPENING_TIME, TIMESTAMP_LC, TIMESTAMP_TS, ZONE_ID, ZONE_OFFSET)
values (2001,
        TO_DATE('1940-09-29','YYYY-MM-DD'), 
        TO_DATE('03:40:23', 'HH24:MI:ss'),
        TO_DATE('2000-06-23 14:40:50', 'YYYY-MM-DD HH24:MI:ss'),
        TO_DATE('2017-03-09 12:46:40', 'YYYY-MM-DD HH24:MI:ss'),
        'America/Edmonton', '-06:00');


insert into TEMPORALS (ID, DOB_DT, OPENING_TIME, TIMESTAMP_LC, TIMESTAMP_TS, ZONE_ID, ZONE_OFFSET)
values (2002,
        TO_DATE('1943-09-29','YYYY-MM-DD'), 
        TO_DATE('14:40:23', 'HH24:MI:ss'),
        TO_DATE('1999-06-23 14:40:50', 'YYYY-MM-DD HH24:MI:ss'),
        TO_DATE('2017-03-09 12:46:40', 'YYYY-MM-DD HH24:MI:ss'),
        'America/Edmonton', '-07:00');
                