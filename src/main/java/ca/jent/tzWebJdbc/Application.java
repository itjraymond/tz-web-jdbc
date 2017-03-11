package ca.jent.tzWebJdbc;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.jent.tzWebJdbc.entities.Temporal;
import ca.jent.tzWebJdbc.repositories.JdbcTemporalRepository;

/**
 * To keep it simple, we use RestController so that we don't have 
 * any UI to develop.
 * 
 * @author jraymond
 *
 */
@SpringBootApplication
@RestController
public class Application {
	
	/**
	 * Use different setting for default timezone to show that it
	 * does not change the date/time (point on timeline):  
	 * the timestamp stored in the database end up to be UTC (specific 
	 * point on the timeline). Hence, even changing the default 
	 * timezone will always retrieve the "same" moment in time.
	 */
	@PostConstruct
	  void setTimezoneToUTC() {
		TimeZone.setDefault(TimeZone.getDefault());
	    //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	    //TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo")); 
	  }
	
	/**
	 * We use JDBC not JPA for this coding test.
	 */
	@Autowired
	private JdbcTemporalRepository repository;

	
	/**
	 * Retrieve one Temporal entity.  Assumes that schema.sql has 
	 * been enabled and executed.
	 * @return JSON representation of Temporal
	 */
	@GetMapping("temporal/one")
	public Temporal getOne() {
		return repository.getTemporal(1000L);
	}
	
	/**
	 * Retrieve all Temporal entities.  If schema.sql has
	 * been enabled and executed it will return at least
	 * two Temporal entity.
	 * @return JSON representation of a List of Temporal
	 */
	@GetMapping("temporal/all")
	public List<Temporal> getAll(){
		return repository.getTemporals();
	}
	
	/**
	 * When this http request is captured, we create a new
	 * Temporal entry into underlying data store.
	 * @return JSON representation of the created Temporal and
	 * 			the created Temporal fetched from the database.
	 */
	@GetMapping("temporal/create")
	public List<Temporal> create() {
		Temporal temporal = new Temporal();
		temporal.setDob(LocalDate.now());
		temporal.setOpeningTime(LocalTime.now());
		temporal.setTimestampLc(LocalDateTime.now());
		temporal.setTimestampTs(Instant.now());
		temporal.setZoneId(ZoneId.systemDefault().getId());
		temporal.setZoneOffset(ZoneOffset.ofHours(-7).toString());
		Long id = repository.saveTemporal(temporal);
		Temporal t = repository.getTemporal(id);
		List<Temporal> list = new ArrayList<>();
		list.add(temporal);
		list.add(t);
		return list;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}


/**
 * NOTES:
 * 1. Created Spring Boot project with:  WEB, JDBC, H2
 * 2. Created schema.sql (creating TEMPORALS table and inserting row(s)) @see src/main/resources/
 *    ** NOTE: naming the file h2.schema.sql will **not** be picked automatically nor h2.data.sql.
 *    see comments within the application.properties of /src/main/resources/
 * 3. Autowired a JdbcTemplate within our Application (this JdbcTemplate type is automatically
 *              provided by Spring Boot Autoconfiguration including the DataSource because of
 *              the embbeded H2 database.  So did not have to provide **any** connection information.
 *              Actually, we only provided the "spring.datasource.url=jdbc:h2:~/test" but it was such
 *              that we could see our Table(s) from /h2-console
 * 4. We do not have any object mapping (ORM) because first we are **not** using JPA but straight JDBC.
 * 5. The SQL query used above should return the row(s) from schema.sql as JSON object when
 *              reaching the URL: localhost:8080/temporal/one or localhost:8080/temporal/all
 * 6. The H2 console activation needed to properties within the application.properties (see application.properties).
 * 
 */
