package com.tyss.talenthunt.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table
public class ScheduleInterviewBean {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "interviewId")
	private int interviewId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_Date")
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "interview_location")
	private String interviewlocation;
	@Column(name = "interview_type")
	private String interviewtype;
	@Column(name = "skills")
	private String skills;
	@Column(name = "status")
	private String status;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "interviewer")
	private String interviewer;
	@Column(name = "hrName")
	private String hrName;
//	@Column(name = "candidateId")
//	private String candidateId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "requirmentId")
	private RequirmentBean requirment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "interview")
	private List<CandidateCreationBean> candidate;

}
