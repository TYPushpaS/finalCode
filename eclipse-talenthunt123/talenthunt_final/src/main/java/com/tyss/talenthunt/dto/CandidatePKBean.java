package com.tyss.talenthunt.dto;

import javax.persistence.Column;

import lombok.Data;

@Data
public class CandidatePKBean {
@Column
	private RequirmentBean requirment;
@Column

	private ScheduleInterviewBean interviewBean;
}
