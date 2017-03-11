package ca.jent.tzWebJdbc.repositories;

import java.util.List;

import ca.jent.tzWebJdbc.entities.Temporal;

/**
 * Defining our repository interface for Temporal.
 * @author jraymond
 *
 */
public interface TemporalRepository {

	List<Temporal> getTemporals();
	
	Temporal getTemporal(Long id);
	
	Long saveTemporal(Temporal temporal);
	
	int deleteTemporal(Long id);
}
