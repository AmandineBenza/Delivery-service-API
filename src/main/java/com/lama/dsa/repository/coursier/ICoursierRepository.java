package com.lama.dsa.repository.coursier;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;

public interface ICoursierRepository extends MongoRepository<Coursier, String> {

	public List<Coursier> findByName(String coursierName);

	public List<Coursier> findByStatus(EnumCoursierStatus coursierStatus);
	
}
