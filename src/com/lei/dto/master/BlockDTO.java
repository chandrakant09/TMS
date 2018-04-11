package com.lei.dto.master;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.lei.domain.master.District;

@XmlRootElement(name = "Block")
@JsonRootName(value = "Block")
@XmlAccessorType(XmlAccessType.NONE)
public class BlockDTO {
	@Getter
	@Setter
	@XmlElement(name = "BlockId")
	@JsonProperty(value = "BlockId")
	private int blockId;
	
	@Getter
	@Setter
	@XmlElement(name = "BlockName")
	@JsonProperty(value = "BlockName")
	private String blockName;
	
	
}
