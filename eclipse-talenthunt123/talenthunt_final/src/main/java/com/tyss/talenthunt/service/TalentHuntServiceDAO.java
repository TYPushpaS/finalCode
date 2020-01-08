package com.tyss.talenthunt.service;

import java.util.List;
import java.util.Map;

import com.tyss.talenthunt.dto.RequirmentBean;
import com.tyss.talenthunt.dto.CandidateCreationBean;
import com.tyss.talenthunt.dto.ScheduleInterviewBean;

public interface TalentHuntServiceDAO {
	public RequirmentBean addrequirement(RequirmentBean reqinfo);
	public boolean updateRequirment(RequirmentBean reqinfo);
	public List<RequirmentBean> getAllRequirements();
	public CandidateCreationBean addCandidate(CandidateCreationBean candidateInfo);
	public List<CandidateCreationBean> getAllCandidate();
	public ScheduleInterviewBean addInterview(ScheduleInterviewBean interview);
	public List<ScheduleInterviewBean> getAllInterview();
	public List<RequirmentBean> getAllRequirmentByDate();
	public List<ScheduleInterviewBean> getAllStack();
	public Map<String, String> getAllRemarks();
}
