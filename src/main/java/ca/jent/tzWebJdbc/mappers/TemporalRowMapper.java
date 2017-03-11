package ca.jent.tzWebJdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ca.jent.tzWebJdbc.entities.Temporal;
import ca.jent.tzWebJdbc.utils.DateUtils;

public class TemporalRowMapper implements RowMapper<Temporal> {

	@Override
	public Temporal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Temporal t = new Temporal();
		t.setId(rs.getLong("ID"));
		t.setDob(DateUtils.asJavaTime_LocalDate(rs.getDate("DOB_DT")));
		t.setOpeningTime(DateUtils.asJavaTime_LocalTime(rs.getTime("OPENING_TIME")));
		t.setTimestampLc(DateUtils.asJavaTime_LocalDateTime(rs.getTimestamp("TIMESTAMP_LC")));
		t.setTimestampTs(DateUtils.asJavaTime_Instant(rs.getTimestamp("TIMESTAMP_TS")));
		t.setZoneId(rs.getString("ZONE_ID"));
		t.setZoneOffset(rs.getString("ZONE_OFFSET"));
		return t;
	}

}
