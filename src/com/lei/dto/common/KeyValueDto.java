package com.lei.dto.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@XmlRootElement(name = "KeyValue")
@JsonRootName(value = "KeyValue")
@XmlAccessorType(XmlAccessType.NONE)
public class KeyValueDto {
	@Getter
	@Setter
	@XmlElement(name = "Key")
	@JsonProperty(value = "Key")
	private String key;
	
	@Getter
	@Setter
	@XmlElement(name = "Value")
	@JsonProperty(value = "Value")
	private String value;
	
	public KeyValueDto() {
		// TODO Auto-generated constructor stub
	}
	public KeyValueDto(String key,String value) {
		this.key=key;
		this.value=value;
	}
	
	public void setId(Long id){
		setKey(""+id);
	}
	public void setName(String name){
		setValue(name);
	}
	public Long getId(){
		if(getKey()!=null){
			return Long.parseLong(getKey());
		}else{
			return null;
		}
	}
	public String getName(){
		return getValue();
	}
}
