package com.lei.domain.master;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="district")
public class District {
	@Id
	@GeneratedValue
	@Column(name="district_id")
	@Getter
	@Setter
	private int disttId;
	
	@Column(name="district_name")
	@Getter
	@Setter
	private String disttName;
	
	@OneToMany(mappedBy="district")
	@Getter
	@Setter
	private List<Block> blocks;

	

}
