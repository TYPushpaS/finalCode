package com.tyss.talenthunt.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table
public class RequirmentBean implements Serializable{
	
	@Id
	@GenericGenerator(name = "string_based_custom_sequence", strategy = "com.tyss.talenthunt.dto.CustomIdGenerator")
	@GeneratedValue(generator = "string_based_custom_sequence")
	@Column
	private String requirmentId;
	
	@Column
	private String client;
	@Column
	private String technology;
	@Column
	private double yearOfExperience;
	@Column
	private String location;
	@Column
	private int numberOfPosition;
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date opendate;
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date closedate;
	@Column
	private String role;
	@Column
	private double rate; // salary
	@Column
	private String contact;
	@Column
	private String jobDiscription; // job description

//	@OneToOne(cascade =  CascadeType.ALL)
//    @JoinColumn(name = "interviewId")
//    private ScheduleInterviewBean interview;
	
	@JsonIgnore
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "requirment")
	private List<ScheduleInterviewBean> interview;
}
