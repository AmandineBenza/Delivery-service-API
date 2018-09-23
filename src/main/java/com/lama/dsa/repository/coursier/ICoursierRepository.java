package com.lama.dsa.repository.coursier;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lama.dsa.model.order.Coursier;

public interface ICoursierRepository extends MongoRepository<Coursier, String> {

	public List<Coursier> findByName(String coursierName);
	
}
