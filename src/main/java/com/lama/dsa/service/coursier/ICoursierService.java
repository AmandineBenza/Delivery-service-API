package com.lama.dsa.service.coursier;

import java.util.List;

import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;

public interface ICoursierService {

	public List<Coursier> getByName(String coursierName);

	Coursier insert(Coursier coursier);

	List<Coursier> getByStatus(EnumCoursierStatus coursierStatus);

	Coursier update(Coursier coursier);
}
