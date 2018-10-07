package com.lama.dsa.service.coursier;

import java.util.List;

import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;

public interface ICoursierService {
	
	
	 /**
     * Get coursier list given a name.
     */
	public List<Coursier> getByName(String coursierName);
	
	 /**
     * Insert a coursier in the database.
     */
	public Coursier insert(Coursier coursier);
	
	 /**
     * Get coursier(s) given a status.
     */
	public List<Coursier> getByStatus(EnumCoursierStatus coursierStatus);

	 /**
     * Update a coursier in the database.
     */
	public Coursier update(Coursier coursier);
}
