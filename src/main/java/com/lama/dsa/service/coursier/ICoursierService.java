package com.lama.dsa.service.coursier;

import java.util.List;

import com.lama.dsa.model.order.Coursier;

public interface ICoursierService {

	public List<Coursier> getByName(String coursierName);
}
