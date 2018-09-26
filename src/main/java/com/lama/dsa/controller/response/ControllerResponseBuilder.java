package com.lama.dsa.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerResponseBuilder implements IControllerResponseBuilder {
	
	private static final IControllerResponseBuilder INSTANCE = new ControllerResponseBuilder();

	private ControllerResponseBuilder(){
		// prevent instanciation
	}
	
	public static IControllerResponseBuilder getInstance(){
		return INSTANCE;
	}
	
	public ResponseEntity<ControllerResponseContent> build(Object body, HttpStatus status, Object... sugar){
		return new ResponseEntity<>(new ControllerResponseContent(body, sugar), status);
	}
	
	public static class ControllerResponseContent {
		private Object body;
		private Object[] sugar;
		
		public ControllerResponseContent(Object body, Object[] sugar) {
			this.body = body;
			this.sugar = sugar;
		}

		public Object getBody() {
			return body;
		}

		public void setBody(Object body) {
			this.body = body;
		}

		public Object[] getSugar() {
			return sugar;
		}

		public void setSugar(Object[] sugar) {
			this.sugar = sugar;
		}
	}

}
