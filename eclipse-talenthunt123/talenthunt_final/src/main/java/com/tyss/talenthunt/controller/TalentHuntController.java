package com.tyss.talenthunt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tyss.talenthunt.dto.RequirmentBean;
import com.tyss.talenthunt.dto.CandidateCreationBean;
import com.tyss.talenthunt.dto.CustomIdGenerator;
import com.tyss.talenthunt.dto.TalentResponse;
import com.tyss.talenthunt.dto.ScheduleInterviewBean;
import com.tyss.talenthunt.service.TalentHuntServiceDAO;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/talenthunt")
public class TalentHuntController {
	@Autowired
	private TalentHuntServiceDAO service;
	@Autowired
	private CustomIdGenerator generator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@PostMapping(path = "/addrequirement", produces = MediaType.APPLICATION_JSON_VALUE)
	public TalentResponse registerAddrequirement(@RequestBody RequirmentBean reqinfo) {

		TalentResponse talentResponse = new TalentResponse();

		if (generator.generator(reqinfo)) {
			RequirmentBean info = service.addrequirement(reqinfo);
			talentResponse.setStatusCode(200);
			talentResponse.setMessage("Requirement has been registered successfully");

		} else {
			talentResponse.setStatusCode(400);
			talentResponse.setMessage(" adding requirement  failed");
		}

		return talentResponse;
	}

	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TalentResponse updateAddRequirment(@RequestBody RequirmentBean reqinfo) {
		TalentResponse talentResponse = new TalentResponse();
		if (service.updateRequirment(reqinfo)) {
			talentResponse.setStatusCode(200);
			talentResponse.setMessage("Requirment has been updated successfully");
		} else {
			talentResponse.setStatusCode(400);
			talentResponse.setMessage("updating requirement  failed");
		}
		return talentResponse;
	}
// getting all the requiremnts
	@GetMapping(path = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TalentResponse getAllRequirment(HttpServletRequest request) {
		TalentResponse talentResponse = new TalentResponse();

		List<RequirmentBean> req = service.getAllRequirements();
		if (req != null) {
			talentResponse.setStatusCode(200);
			talentResponse.setMessage("Success");
			talentResponse.setAdd(req);
			return talentResponse;
		} else {
			talentResponse.setStatusCode(400);
			talentResponse.setMessage("Failure");
			return talentResponse;
		}
	}

	// register candidate
	@PostMapping(path = "/registerCandidate", produces = MediaType.APPLICATION_JSON_VALUE)
	public TalentResponse registerCandidate(@RequestBody CandidateCreationBean candidateInfo) {
		TalentResponse talentResponse = new TalentResponse();
		CandidateCreationBean creation = service.addCandidate(candidateInfo);
		if (creation != null) {
			talentResponse.setStatusCode(200);
			talentResponse.setMessage("Requirement has been registered successfully");
		} else {
			talentResponse.setStatusCode(400);
			talentResponse.setMessage("adding requirement  failed");
		}
		return talentResponse;
	}

//	@PostMapping(path = "/createCandidate", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Response createCandidates(@RequestHeader String candidateName, @RequestHeader String email,
//			@RequestHeader long phoneNumber,@RequestHeader String govtId, @RequestParam("file") MultipartFile file,
//			@RequestHeader String relocate, @RequestHeader String candidateType, @RequestHeader Date yop,
//			@RequestHeader double percentage, @RequestHeader String currentDesignation, @RequestHeader int yoe, @RequestHeader double noticePeriod, @RequestHeader double currentCTC,
//			@RequestHeader double expectedCTC ) throws IOException {
//		Response response = new Response();
//		System.out.println(candidateName);
//		System.out.println(email);
//		System.out.println(file);
//		File confile = new File(file.getOriginalFilename());
//		FileOutputStream fileout = new FileOutputStream(confile);
//		fileout.write(file.getBytes());
//		fileout.close();
//		CandidateCreation candidate = new CandidateCreation();
//		candidate.setCandidateName(candidateName);
//		candidate.setEmail(email);
//		candidate.setPhoneNumber(phoneNumber);
//		candidate.setGovtId(govtId);
//		candidate.setUploadResume(confile);
//		candidate.setRelocate(relocate);
//		candidate.setCandidateType(candidateType);
//		candidate.setCurrentCTC(currentCTC);
//		candidate.setYop(yop);
//		candidate.setPercentage(percentage);
//		candidate.setCurrentDesignation(currentDesignation);
//		candidate.setYoe(yoe);
//		candidate.setNoticePeriod(noticePeriod);
//		candidate.setCurrentCTC(currentCTC);
//		candidate.setExpectedCTC(expectedCTC);
//
//		CandidateCreation c = service.addCandidate(candidate);
//		if (c != null) {
//			response.setStatusCode(200);
//			response.setMessage("candidate registered successfully");
//		} else {
//			response.setStatusCode(400);
//			response.setMessage("not able to register");
//		}
//		return response;
//	}

	@GetMapping(path = "/get-all-candidate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TalentResponse getAllCandidate(HttpServletRequest request) {
		TalentResponse talentResponse = new TalentResponse();

		List<CandidateCreationBean> candidate = service.getAllCandidate();
		if (candidate != null) {
			talentResponse.setStatusCode(200);
			talentResponse.setMessage("Success");
			talentResponse.setCandidate1(candidate);
			return talentResponse;
		} else {
			talentResponse.setStatusCode(400);
			talentResponse.setMessage("Failure");
			return talentResponse;
		}
	}

	@PostMapping(path = "/scheduleinterview", produces = MediaType.APPLICATION_JSON_VALUE)
	public TalentResponse scheduleInterviewBean(@RequestBody ScheduleInterviewBean scheduleInterview) {
		System.out.println(scheduleInterview.getRequirment());
		TalentResponse talentResponse = new TalentResponse();
		ScheduleInterviewBean interview = service.addInterview(scheduleInterview);
		if (interview != null) {
			talentResponse.setStatusCode(200);
			talentResponse.setMessage("scheduled successfully");
			return talentResponse;
		} else {
			talentResponse.setStatusCode(400);
			talentResponse.setMessage(" interview  scheduling is failed");
			return talentResponse;
		}
	}

	@GetMapping(path = "/get-all-interview", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TalentResponse getAllInterview(HttpServletRequest request) {
		TalentResponse talentResponse = new TalentResponse();

		List<ScheduleInterviewBean> interview = service.getAllInterview();
		if (interview != null) {
			talentResponse.setStatusCode(200);
			talentResponse.setMessage("Success");
			talentResponse.setInterview1(interview);
			return talentResponse;
		} else {
			talentResponse.setStatusCode(400);
			talentResponse.setMessage("Failure");
			return talentResponse;
		}
	}

	@GetMapping(path = "/get-all-interviewByDate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TalentResponse getAllInterviewByDate(HttpServletRequest request) {
		TalentResponse talentResponse = new TalentResponse();
		List<RequirmentBean> requirmentDate = service.getAllRequirmentByDate();
		if (requirmentDate != null) {
			talentResponse.setStatusCode(200);
			talentResponse.setMessage("Success");
			talentResponse.setAdd(requirmentDate);
			return talentResponse;
		} else {
			talentResponse.setStatusCode(400);
			talentResponse.setMessage("Failure");
			return talentResponse;
		}
	}

	@GetMapping(path="/getallstack",produces= MediaType.APPLICATION_JSON_VALUE)	
	public TalentResponse getAllStack() {		
		TalentResponse talentHuntResponse = new TalentResponse();		
		List<ScheduleInterviewBean> bean =  service.getAllStack();		
		if(bean != null) {			
			talentHuntResponse.setStatusCode(200);		    
			talentHuntResponse.setMessage("successs"); 		    
			talentHuntResponse.setInterview1(bean);	
			return talentHuntResponse;		
			}else {			 
				talentHuntResponse.setStatusCode(400);			 
				talentHuntResponse.setMessage("failure"); 			 
				return talentHuntResponse;					
				}
	}
	
	@GetMapping(path="/getAllRemarks",produces= MediaType.APPLICATION_JSON_VALUE)	
	public TalentResponse getAllRemarks() {
		TalentResponse talentHuntResponse = new TalentResponse();
		Map<String, String> map1 = service.getAllRemarks();
		
		if(map1 != null) {			
			talentHuntResponse.setStatusCode(200);		    
			talentHuntResponse.setMessage("successs"); 		    
			talentHuntResponse.setAllReamrks(map1);
			return talentHuntResponse;		
			}else {			 
				talentHuntResponse.setStatusCode(400);			 
				talentHuntResponse.setMessage("failure"); 			 
				return talentHuntResponse;					
				}
		
	}
	
}
