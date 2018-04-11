package com.lei.domain.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="block")
public class Block {
	@Id
	@GeneratedValue
	@Column(name="block_id")
	@Getter
	@Setter
	private int blockId;
	
	@Column(name="block_name")
	@Getter
	@Setter
	private String blockName;
	
	@ManyToOne
    @JoinColumn(name="district_id")
	@Getter
	@Setter
	private District district;
	
	

}
