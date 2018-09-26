package com.lama.dsa.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lama.dsa.controller.response.ControllerResponseBuilder.ControllerResponseContent;

public interface IControllerResponseBuilder {

	public ResponseEntity<ControllerResponseContent> build(Object body, HttpStatus status, Object... sugar);
	
}
