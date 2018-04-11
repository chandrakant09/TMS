package com.lei.dto.search;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lei.dto.common.KeyValueDto;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
@XmlRootElement(name = "SearchParameter")
@JsonRootName(value = "SearchParameter")
@XmlAccessorType(XmlAccessType.NONE)
public class SearchDTO {
	
	@Getter
	@Setter
	@XmlElement(name = "SearchParameters")
	@JsonProperty(value = "SearchParameters")
	private Collection<String> searchParameters;
	
	@Getter
	@Setter
	@XmlElement(name = "AutoSearchParameters")
	@JsonProperty(value = "AutoSearchParameters")
	private Collection<String> autoSearchEnabled;
}
