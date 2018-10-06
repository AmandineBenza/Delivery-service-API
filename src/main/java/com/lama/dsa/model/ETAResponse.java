package com.lama.dsa.model;

import java.util.ArrayList;
import java.util.List;

public class ETAResponse {

	public long ETA;
	public List<IResponseComponent> list;

	public ETAResponse(long ETA) {
		this.ETA = ETA;
		this.list = new ArrayList<IResponseComponent>();
	}

	public ETAResponse() {
		this.list = new ArrayList<IResponseComponent>();
	}

}
