package com.lama.dsa.model;

import java.util.ArrayList;
import java.util.List;

public class ETAResponse {
	public long ETA;
	public List<IResponseComponant> list;
	
	public ETAResponse(long ETA ){
		this.ETA = ETA;
		this.list = new ArrayList<IResponseComponant>();
	}
	public ETAResponse(){
		this.list = new ArrayList<IResponseComponant>();
	}
}
