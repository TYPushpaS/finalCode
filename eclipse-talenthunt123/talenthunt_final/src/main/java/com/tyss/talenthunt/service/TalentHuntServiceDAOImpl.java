package com.tyss.talenthunt.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.talenthunt.dao.TalentDAO;
import com.tyss.talenthunt.dto.RequirmentBean;
import com.tyss.talenthunt.dto.CandidateCreationBean;
import com.tyss.talenthunt.dto.ScheduleInterviewBean;

@Service
public class TalentHuntServiceDAOImpl implements TalentHuntServiceDAO {
	@Autowired
	private TalentDAO dao;

	@Override
	public RequirmentBean addrequirement(RequirmentBean reqinfo) {
		if (reqinfo.getInterview() != null) {
			for (ScheduleInterviewBean bean : reqinfo.getInterview()) {
				bean.setRequirment(reqinfo);
			}
		}
		return dao.addrequirement(reqinfo);
	}

	@Override
	public boolean updateRequirment(RequirmentBean reqinfo) {
		return dao.updateRequirment(reqinfo);
	}

	@Override
	public List<RequirmentBean> getAllRequirements() {
		return dao.getAllRequirements();
	}

	@Override
	public CandidateCreationBean addCandidate(CandidateCreationBean candidateInfo) {
		
		return dao.addCandidate(candidateInfo);
	}

	@Override
	public List<CandidateCreationBean> getAllCandidate() {
		return dao.getCandidate();
	}

	@Override
	public ScheduleInterviewBean addInterview(ScheduleInterviewBean interview) {
		if (interview.getCandidate() != null) {
			for (CandidateCreationBean bean : interview.getCandidate()) {
				bean.setInterview(interview);
			}
		}
		return dao.addInterview(interview);
	}

	@Override
	public List<ScheduleInterviewBean> getAllInterview() {
		return dao.getAllInterview();
	}

	@Override
	public List<RequirmentBean> getAllRequirmentByDate() {
		return dao.getAllRequirmentByDate();
	}

	@Override
	public List<ScheduleInterviewBean> getAllStack() {
		return dao.getAllStack();
	}

	@Override
	public Map<String, String> getAllRemarks() {
		return dao.getAllRemarks();
	}

	
	
}
