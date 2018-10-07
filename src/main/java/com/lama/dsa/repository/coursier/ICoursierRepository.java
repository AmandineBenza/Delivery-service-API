package com.lama.dsa.repository.coursier;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;

/**
 * Database coursier storing. 
 */
public interface ICoursierRepository extends MongoRepository<Coursier, String> {

	/**
	 * Find coursiers given a name.
	 */
	public List<Coursier> findByName(String coursierName);

	/**
	 * Find coursiers given a status.
	 */
	public List<Coursier> findByStatus(EnumCoursierStatus coursierStatus);
	
}
