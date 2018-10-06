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
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj instanceof ETAResponse )
			return this.list.equals(((ETAResponse)obj).list);
		 return false;
	}
}
