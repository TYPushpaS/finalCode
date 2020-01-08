package com.tyss.talenthunt.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.tyss.talenthunt.dto.RequirmentBean;
import com.tyss.talenthunt.dto.CandidateCreationBean;
import com.tyss.talenthunt.dto.ScheduleInterviewBean;

@Repository
public class TalentDAOImpl implements TalentDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public RequirmentBean addrequirement(RequirmentBean reqinfo) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(reqinfo);
			transaction.commit();
			return reqinfo;
		} catch (Exception e) {
			manager.close();
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean updateRequirment(RequirmentBean reqinfo) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		RequirmentBean updateinfo = manager.find(RequirmentBean.class, reqinfo.getRequirmentId());
		if (updateinfo == null) {
			return false;
		}
		transaction.begin();
		updateinfo.setRequirmentId(reqinfo.getRequirmentId());
		updateinfo.setClient(reqinfo.getClient());
		updateinfo.setTechnology(reqinfo.getTechnology());
		updateinfo.setYearOfExperience(reqinfo.getYearOfExperience());
		updateinfo.setLocation(reqinfo.getLocation());
		updateinfo.setNumberOfPosition(reqinfo.getNumberOfPosition());
		updateinfo.setOpendate(reqinfo.getOpendate());
		updateinfo.setClosedate(reqinfo.getClosedate());
		updateinfo.setRole(reqinfo.getRole());
		updateinfo.setRate(reqinfo.getRate());
		updateinfo.setContact(reqinfo.getContact());
		updateinfo.setJobDiscription(reqinfo.getJobDiscription());
		transaction.commit();
		return true;
	}

	@Override
	public List<RequirmentBean> getAllRequirements() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "From RequirmentBean";
		Query query = manager.createQuery(jpql);
		List<RequirmentBean> req = (List<RequirmentBean>) query.getResultList();
		return req;

	}

	@Override
	public CandidateCreationBean addCandidate(CandidateCreationBean candidateInfo) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			candidateInfo.setInterview(manager.find(ScheduleInterviewBean.class, candidateInfo.getInterview().getInterviewId()));
			manager.persist(candidateInfo);
			transaction.commit();
			return candidateInfo;
		} catch (Exception e) {
			manager.close();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CandidateCreationBean> getCandidate() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "From CandidateCreationBean";
		Query query = manager.createQuery(jpql);
		List<CandidateCreationBean> candidate = (List<CandidateCreationBean>) query.getResultList();
		return candidate;
	}

	@Override
	public ScheduleInterviewBean addInterview(ScheduleInterviewBean interview) {

		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			System.out.println("+++++++++++++"+interview.getInterviewId());
			interview.setRequirment(manager.find(RequirmentBean.class, interview.getRequirment().getRequirmentId()));
			manager.persist(interview);
			transaction.commit();
			return interview;
		} catch (Exception e) {
			manager.close();
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<ScheduleInterviewBean> getAllInterview() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "From ScheduleInterviewBean";
		Query query = manager.createQuery(jpql);
		List<ScheduleInterviewBean> interview = (List<ScheduleInterviewBean>) query.getResultList();
		return interview;
	}

	public List<RequirmentBean> getAllRequirmentByDate() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "select count(*),month(opendate) from RequirmentBean GROUP BY MONTH(opendate)";
		Query query = manager.createQuery(jpql);
		List<RequirmentBean> interview = (List<RequirmentBean>) query.getResultList();
		return interview;
	}

	@Override
	public List<ScheduleInterviewBean> getAllStack() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String jpql = "select count(*),skills from ScheduleInterviewBean group by skills";
		Query query = manager.createQuery(jpql);
		List<ScheduleInterviewBean> list = query.getResultList();
		if (list == null) {
			return null;
		}
		return list;
	}

	
	

	@Override
	public Map<String, String> getAllRemarks() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String jpql = "select candidateId, remarks from ScheduleInterviewBean group by remarks";
		Query query = manager.createQuery(jpql);
		Map<String, String> map = (Map<String, String>) query.getResultList();
		return  map;
	}

}
