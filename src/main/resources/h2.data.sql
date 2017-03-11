insert into TEMPORALS (ID, DOB_DT, OPENING_TIME, TIMESTAMP_FIX, TIMESTAMP_TS, ZONE_ID, ZONE_OFFSET)
values (1000, 
        TO_DATE('1967-02-16','YYYY-MM-DD'), 
        TO_DATE('03:40:23', 'HH24:MI:ss'),
        TO_DATE('2004-05-22 13:30:40', 'YYYY-MM-DD HH24:MI:ss'),
        TO_DATE('2017-03-09 13:56:50', 'YYYY-MM-DD HH24:MI:ss'),
        'America/Edmonton', '-07:00');
         