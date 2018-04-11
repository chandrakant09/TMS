package com.lei.dto.master;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lei.domain.master.Block;


@XmlRootElement(name = "District")
@JsonRootName(value = "District")
@XmlAccessorType(XmlAccessType.NONE)
public class DistrictDTO {
	
	@Getter
	@Setter
	@XmlElement(name = "DisttId")
	@JsonProperty(value = "DisttId")
	private int disttId;
	
	@Getter
	@Setter
	@XmlElement(name = "DisttName")
	@JsonProperty(value = "DisttName")
	private String disttName;
	
	
	@Getter
	@Setter
	@XmlElement(name = "Blocks")
	@JsonProperty(value = "Blocks")
	private List<BlockDTO> blocks;

}
