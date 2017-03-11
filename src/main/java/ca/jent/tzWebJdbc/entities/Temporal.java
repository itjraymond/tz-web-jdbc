package ca.jent.tzWebJdbc.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Remember:  In this project, we use Spring JDBC not JPA  hence there is no ORM.
 * We will need to use RowMapper<Temporal> to do manual db-to-java mapping.
 * @author jraymond
 *
 */
public class Temporal {
	
	private Long          id;
	private LocalDate     dob;
	private LocalTime     openingTime;
	private LocalDateTime timestampLc;
	private Instant       timestampTs;
	private String        zoneId;      // because we are using Spring Jdbc and not JPA, we will store the zoneId 
	private String        zoneOffset;  // and zoneOffset as a String. When using JPA we will create converter to 
									   // the java.time.ZoneId and java.time.ZoneOffset respectively.
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalTime getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}
	public LocalDateTime getTimestampLc() {
		return timestampLc;
	}
	public void setTimestampLc(LocalDateTime timestampLc) {
		this.timestampLc = timestampLc;
	}
	public Instant getTimestampTs() {
		return timestampTs;
	}
	public void setTimestampTs(Instant timestampTs) {
		this.timestampTs = timestampTs;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneOffset() {
		return zoneOffset;
	}
	public void setZoneOffset(String zoneOffset) {
		this.zoneOffset = zoneOffset;
	}
	@Override
	public String toString() {
		return "Temporal [id=" + id + ", dob=" + dob + ", openingTime=" + openingTime + ", timestampLc=" + timestampLc
				+ ", timestampTs=" + timestampTs + ", zoneId=" + zoneId + ", zoneOffset=" + zoneOffset + "]";
	}

	
}
