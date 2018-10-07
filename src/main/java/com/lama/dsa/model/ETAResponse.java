
package com.lama.dsa.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Custom response used to regroup response content and ETA. 
 */
public class ETAResponse {
	
	private long ETA;
	private List<IResponseComponent> content;
	
	public ETAResponse(long ETA){
		this.ETA = ETA;
		this.content = new ArrayList<IResponseComponent>();
	}
	
	public ETAResponse(){
		this.content = new ArrayList<IResponseComponent>();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj instanceof ETAResponse)
			return this.content.equals(((ETAResponse)obj).content);
		 return false;
	}
	
	public void add(List<? extends IResponseComponent> components){
		content.addAll(components);
	}
	
	public void add(IResponseComponent component){
		content.add(component);
	}

	public boolean hasNoContent(){
		return content.isEmpty();
	}
	
	public long getETA() {
		return ETA;
	}

	public void setETA(long eTA) {
		ETA = eTA;
	}
	
	public List<IResponseComponent> getContent(){
		return content;
	}
	
}
