package com.lama.dsa.service.coursier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lama.dsa.model.order.Coursier;
import com.lama.dsa.model.order.EnumCoursierStatus;
import com.lama.dsa.repository.coursier.ICoursierRepository;

@Transactional
@Service("CoursierService")
public class CoursierService implements ICoursierService {

	@Autowired
	private ICoursierRepository coursierRepository;
	
	public CoursierService() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Coursier> getByName(String coursierName) {
		return coursierRepository.findByName(coursierName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Coursier insert(Coursier coursier) {
		return  coursierRepository.insert(coursier);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Coursier> getByStatus(EnumCoursierStatus coursierStatus) {
		return coursierRepository.findByStatus(coursierStatus);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Coursier update(Coursier coursier) {
		return coursierRepository.save(coursier);
	}
}
