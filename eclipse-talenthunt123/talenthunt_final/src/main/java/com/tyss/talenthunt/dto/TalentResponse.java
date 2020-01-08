package com.tyss.talenthunt.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class TalentResponse {

	private int statusCode;
	private String message;
	private String clientId;
	private boolean status;
	private List<RequirmentBean> add;
	private List<CandidateCreationBean> candidate1;
	private List<ScheduleInterviewBean> interview1;
	public Map<String, String> allReamrks;
}
