package ca.jent.tzWebJdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.jent.tzWebJdbc.entities.Temporal;
import ca.jent.tzWebJdbc.repositories.JdbcTemporalRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private JdbcTemporalRepository repository;
	
	@Test
	public void basicQueryCount() {

		List<Temporal> list = repository.getTemporals();
		assertNotNull(list);
		assertEquals(3, list.size());  // see h2.data.sql in /src/test/resources/
	}
	
	@Test
	public void verifyOneTemporalEntry() {
		Temporal entity = repository.getTemporal(2001L);
		
		assertEquals(LocalDate.of(1940, 9, 29), entity.getDob());
		assertEquals(LocalTime.of(3, 40, 23), entity.getOpeningTime());
		assertEquals(LocalDateTime.of(2000, 6, 23, 14, 40, 50), entity.getTimestampLc());
		assertEquals(ZonedDateTime.of(LocalDateTime.of(2017, 3,9,19,46,40), ZoneId.of("UTC")).toInstant(), entity.getTimestampTs());
		assertEquals("America/Edmonton", entity.getZoneId());
		assertEquals("-06:00", entity.getZoneOffset());
		System.out.println(entity);
		
	}
	
	@Test
	public void saveOneTemporalEntry() {
		
		Temporal t = new Temporal();
		t.setDob(LocalDate.of(1980, 11, 20));
		t.setOpeningTime(LocalTime.of(14, 45, 00));
		t.setTimestampLc(LocalDateTime.of(1981, 11, 20, 14, 50, 00));
		t.setTimestampTs(Instant.now());
		t.setZoneId(ZoneId.systemDefault().getId());
		t.setZoneOffset(ZoneOffset.ofHours(-7).getId());
		Long id = repository.saveTemporal(t);
		
		Temporal entity = repository.getTemporal(id);
		
		assertEquals(t.getDob(),entity.getDob());
		assertEquals(t.getOpeningTime(), entity.getOpeningTime());
		assertEquals(t.getTimestampLc(), entity.getTimestampLc());
		assertEquals(t.getTimestampTs(), entity.getTimestampTs());
		
		repository.deleteTemporal(id);
		
	}

}
