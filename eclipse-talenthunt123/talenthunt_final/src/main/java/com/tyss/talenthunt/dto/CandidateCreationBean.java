package com.tyss.talenthunt.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Candidate_creation")
public class CandidateCreationBean implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column
	private int candidateId;
	@Column
	private String candidateName;
	@Column
	private String email;
	@Column
	private long phoneNumber;
	@Column
	private String govtId;
//	@Column
//	private File uploadResume;
	@Column
	private String relocate;
	@Column
	private String candidateType;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date yearOfPassing;
	@Column
	private double percentage;
	@Column
	private String currentDesignation;
	@Column
	private double yearOfExperience;
	@Column
	private double noticePeriod;
	@Column
	private double currentCTC;
	@Column
	private double expectedCTC;
    
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="interviewId")
	private ScheduleInterviewBean interview;

}
