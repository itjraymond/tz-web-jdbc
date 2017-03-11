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

-- NOTE:  instead of ending with _FIX to represent a Date/Time without a timezone per say we could use:
--        _LC  (for Local; may map to LocalDateTime better)  also only two letters.

-- Changed to _LC