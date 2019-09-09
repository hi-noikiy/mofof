package com.mofof.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mofof.entity.mywork.Alert;
import com.mofof.entity.mywork.CalEvent;
import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.repository.UserAccountRepository;
import com.mofof.service.AlertService;
import com.mofof.service.CalEventService;
import com.mofof.service.UserService;
import com.mofof.util.DateUtil;
import com.mofof.util.GsonBuilderFactory;



@RestController
public class CalEventController {
  private static Logger log = LoggerFactory.getLogger(CalEventController.class);
  
	@Autowired
	CalEventService calEventService;
	@Autowired
	UserService userService;
	@Autowired
	AlertService alertService;
	@Autowired
	UserAccountRepository userAccountRepository;
	
	@GetMapping("/cal_events")
	public String index() {
		Session session = SecurityUtils.getSubject().getSession();
		long id  = (long) session.getAttribute("userAccountId");
		UserAccount userAccount = userAccountRepository.findById(id);
		long uid = userAccount.getUser().getId();
		Set<CalEvent> calEvents = calEventService.findAllByUserId(uid);
		
		for (CalEvent calEvent : calEvents) {
      Alert alert = alertService.findByCalEventId(calEvent.getId());
      calEvent.setAlert(alert);
      //取所有参与人的信息
      Set<User> users = calEvent.getAttendees();
      for (User user : users) {
        UserAccount userAccount2 = userAccountRepository.findByUserId(user.getId());
        user.setUserAccount(userAccount2);
      }          
    }
		return GsonBuilderFactory.createGsonBuilder(
				Arrays.asList("createTime", "calEvent", "creator", "role", "departments",
                      "managedDepartments", "userAccount", "calEvents")
			).toJson(calEvents);
	}
	
  @PostMapping("/cal_events")
  public String create(@RequestBody CalEvent calEvent) {   
      Session session = SecurityUtils.getSubject().getSession();
      long id = (long) session.getAttribute("userAccountId");
      UserAccount userAccount = userAccountRepository.findById(id);
      User user = userAccount.getUser();
      calEvent.setCreatorUser(user);
      Set<User> attendees = calEvent.getAttendees();
      if (attendees != null) {
        Set<User> users = new HashSet<User>();
        for (User user2 : attendees) {
          User u = userService.findById(user2.getId());
          users.add(u);
        }
        calEvent.setAttendees(users);
      }   
    CalEvent calEvent2 = calEventService.save(calEvent);
    calEvent2.setAlert(alertService.findByCalEventId(calEvent2.getId()));
    return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime", "calEvent", "creator", "role",
            "departments", "managedDepartments", "userAccount", "calEvents"))
        .toJson(calEvent2);
  }
	
	@PutMapping("/cal_events/{id}")
	public String update(@PathVariable long id, @RequestBody CalEvent calEvent) {
	  LocalDateTime canceledAt = calEvent.getCanceledAt();
	  //日程的取消
	  if (canceledAt != null) {
	    calEvent = calEventService.findById(id);	 
	    Alert alert = alertService.findByCalEventId(id);
	    calEvent.setAlert(alert);
	    calEvent.setCanceledAt(canceledAt);
	  }else {
	    Session session = SecurityUtils.getSubject().getSession();
      long uId = (long) session.getAttribute("userAccountId");
      UserAccount userAccount = userAccountRepository.findById(uId);
      User user = userAccount.getUser();
      calEvent.setCreatorUser(user);
	    
	    Set<User> attendees = calEvent.getAttendees();
      if (attendees != null) {
        Set<User> users = new HashSet<User>();
        for (User user2 : attendees) {
          User u = userService.findById(user2.getId());
          users.add(u);
        }
        calEvent.setAttendees(users);
      }   
	    
  	  calEvent.setId(id);
  	  Alert alert = calEvent.getAlert();
  	  if (alert != null) {
  	    Alert alert2 = alertService.findByCalEventId(id);
  	    alert2.setnMinBefore(alert.getnMinBefore());
  	    alert2.setNotifyVia(alert.getNotifyVia());
  	    alert2.setRepeatCount(alert.getRepeatCount());
  	    calEvent.setAlert(alert2);
  	  }
	  }
	  calEvent = calEventService.update(calEvent);
		return GsonBuilderFactory.createGsonBuilder(
				Arrays.asList("createTime", "calEvent", "creator", "role", "departments",
                      "managedDepartments", "userAccount", "calEvents")
			).toJson(calEvent);
	}
	
	@DeleteMapping("/cal_events/{id}")
  public void delete(@PathVariable long id) {
	  Alert alert = alertService.findByCalEventId(id);
	  alertService.deleteById(alert);
	  //calEventService.delete(id);
  }	
	
	@PostMapping("/cal_events/{day}")
  public String index(@PathVariable String day, @RequestBody User user) throws ParseException {
	  SimpleDateFormat sdf = null;
	  LocalDateTime start = null;
	  LocalDateTime end = null;
	  if (day.length() == 6) {
      start = DateUtil.atStartOfMonth(day);
      end = DateUtil.atEndOfMonth(day);   
    }
  	if (day.length() == 8) { 	   
  	  start = DateUtil.atStartOfDay(day);
      end = DateUtil.atEndOfDay(day);
  	}  
  	if (day.length() == 17) {
  	  String[] days = day.split("-");   
      start = DateUtil.atStartOfDay(days[0]);
      end = DateUtil.atEndOfDay(days[1]); 
  	}

	  if(day != null) { 
	    User u = userService.findById(user.getId());
	    Set<CalEvent> calEvents = u.getCalEvents();
	    List<Object> ids = new ArrayList<Object>();
	    
	    for (CalEvent calEvent : calEvents) {
	      ids.add(calEvent.getId());
//	      if (calEvent != null) {
//	        calEvents.add(calEvent);
//	      }
      }
	    calEvents = calEventService.findByStartAt(start, end, ids);
//	    calEvents = calEventService.findByStartAt(start, end, user.getId());
	    for (CalEvent calEvent : calEvents) {
        calEvent.setAlert(alertService.findByCalEventId(calEvent.getId()));
      }
      return GsonBuilderFactory.createGsonBuilder(
          Arrays.asList("createTime", "calEvent", "creator", "role", "departments",
                       "managedDepartments", "userAccount", "calEvents")
        ).toJson(calEvents); 
	  } else {
	    return "";
	  }
  }
	
	@PostMapping("/cal_events/search")
	public String search(@RequestBody Map criteria) {  
	  List<CalEvent> calEvents = calEventService.search(criteria);
	  for (CalEvent calEvent : calEvents) {
	    calEvent.setAlert(alertService.findByCalEventId(calEvent.getId()));
    }
	  return GsonBuilderFactory.createGsonBuilder(
        Arrays.asList("createTime", "calEvent", "creator", "role", "departments",
                      "managedDepartments", "userAccount", "calEvents")
      ).toJson(calEvents); 
	}
}
