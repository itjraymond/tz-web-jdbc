package ca.jent.tzWebJdbc.repositories;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.jent.tzWebJdbc.entities.Temporal;
import ca.jent.tzWebJdbc.mappers.TemporalRowMapper;
import ca.jent.tzWebJdbc.utils.DateUtils;

/**
 * Implementation of our repository for Temporal
 * @author jraymond
 *
 */
@Repository
public class JdbcTemporalRepository implements TemporalRepository {

	private JdbcTemplate template; // provided by Spring
	private static long nextId = System.currentTimeMillis(); // our sophisticated id generation strategy.
	
	/**
	 * Spring Boot automatically wires in a default configured DataSource for H2 embedded db.
	 * @param dataSource
	 */
	@Autowired
	public JdbcTemporalRepository(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Because we use JdbcTemplate from Spring, we need to use a RowMapper<?>
	 * to map database store row to a POJO/Entity bean.  Using JdbcTemplate does
	 * not use JPA (ORM) hence we have to do the mapping programmatically.
	 */
	@Override
	public List<Temporal> getTemporals() {
		return template.query("select * from temporals", new TemporalRowMapper());
	}

	/**
	 * Because we use JdbcTemplate from Spring, we need to use a RowMapper<?>
	 * to map database store row to a POJO/Entity bean.  Using JdbcTemplate does
	 * not use JPA (ORM) hence we have to do the mapping programmatically.
	 */
	@Override
	public Temporal getTemporal(Long id) {
		return template.queryForObject("select * from temporals where id=?", new TemporalRowMapper(), id);
	}

	/**
	 * Save a Temporal into a data store using Spring Jdbc template.
	 * Note: if the Temporal has no "id" set, then an insert will be performed.
	 *       otherwise an update is performed.
	 * @return Long the id of the saved Temporal.
	 */
	@Override
	public Long saveTemporal(Temporal temporal) {
		if (temporal == null) throw new IllegalArgumentException(Temporal.class.getName() +" cannot be null.");
		// if there is no id on the temporal we then do an insert. otherwise an update
		if (temporal.getId() == null) {
			long id = nextId++;
			temporal.setId(id);
			template.update("insert into temporals(ID, DOB_DT, OPENING_TIME, TIMESTAMP_LC, TIMESTAMP_TS, ZONE_ID, ZONE_OFFSET) values(?,?,?,?,?,?,?) ", 
					id, 
					DateUtils.asJavaUtil_Date(temporal.getDob()), 
					DateUtils.asJavaSql_Time(temporal.getOpeningTime()), 
					DateUtils.asJavaSql_Timestamp(temporal.getTimestampLc()), 
					DateUtils.asJavaSql_Timestamp(temporal.getTimestampTs()),
					temporal.getZoneId(),
					temporal.getZoneOffset()
					);
			return id;
			
		} else {
			template.update("update Temporals set DOB_DT=?, OPENING_TIME=?, TIMESTAMP_LC=?, TIMESTAMP_TS=?, ZONE_ID=?, ZONE_OFFSET=? where id=?", 
					DateUtils.asJavaUtil_Date(temporal.getDob()),
					DateUtils.asJavaSql_Time(temporal.getOpeningTime()),
					DateUtils.asJavaSql_Timestamp(temporal.getTimestampLc()),
					DateUtils.asJavaSql_Timestamp(temporal.getTimestampTs()),
					temporal.getZoneId(),
					temporal.getZoneOffset()
					);
			return temporal.getId();
		}
	}

	@Override
	public int deleteTemporal(Long id) {
		return template.update("delete from Temporals where id=?", id);
	}

}
