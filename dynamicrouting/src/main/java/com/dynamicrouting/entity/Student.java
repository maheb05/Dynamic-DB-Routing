package com.dynamicrouting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
	
	@Id
	private long sid;
	private String sname;
	private String semail;
	private String snum;
	
}
