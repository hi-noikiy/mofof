package com.mofof.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mofof.entity.mywork.Alert;
import com.mofof.entity.mywork.CalEvent;
import com.mofof.repository.CalEventRepository;
import com.mofof.util.DateUtil;

@Service
public class CalEventService {

  @Autowired
  CalEventRepository CalEventRepository;
  @Autowired
  AlertService alertService;
  
  public Set<CalEvent> findAll() {
    return CalEventRepository.findAll();
  }
  
  public CalEvent save(CalEvent calEvent) {   
    Alert alert = calEvent.getAlert();
    calEvent = CalEventRepository.save(calEvent);
    if (alert != null) {
      LocalDateTime startedAt = calEvent.getStartedAt();
      LocalDateTime triggedAt = startedAt.minusMinutes(alert.getnMinBefore());
      alert.setTriggedAt(triggedAt);     
      alert.setCalEvent(calEvent);
      alertService.save(alert);
    }
    return calEvent;
  }

  @Modifying
  @Transactional
  public void delete(long id) {
    CalEventRepository.delete(id);
  }

  public CalEvent findById(long id) {
    CalEvent CalEvent = CalEventRepository.findOne(id);
    return CalEvent;
  }

  public Set<CalEvent> findAllByUserId(long id) {
    return CalEventRepository.findAllByCreatorUserId(id);   
  }

  public Set<CalEvent> findByStartAt(LocalDateTime start, LocalDateTime end, List<Object> ids) {
    return CalEventRepository.findByStartAt(start, end, ids);
  }

  public CalEvent update(CalEvent calEvent) {
//    Alert alert = alertService.findByCalEventId(calEvent.getId());
    Alert alert = calEvent.getAlert();
    LocalDateTime startedAt = calEvent.getStartedAt();
    LocalDateTime triggedAt = startedAt.minusMinutes(alert.getnMinBefore());
    alert.setTriggedAt(triggedAt);
    calEvent = CalEventRepository.save(calEvent);
    //alert.setNotifyVia(alert.getNotifyVia());
    alert.setCalEvent(calEvent);
    alertService.update(alert);  
    calEvent.setAlert(alert);
    return calEvent;
  }
  
  public List<CalEvent> search(Map criteria){
    return CalEventRepository.findAll(new Specification<CalEvent>() {
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
      List<Predicate> predicates = new ArrayList<>();
      if((String)criteria.get("month") !=null) {
        //@formatter:off
        predicates.add(cb.and(
            cb.lessThan(root.get("startedAt"), DateUtil.atEndOfMonth((String)criteria.get("month"))),
            cb.greaterThan(root.get("endedAt"), DateUtil.atStartOfMonth((String)criteria.get("month")))
           ));
        //@formatter:on
      }
      if((Integer)criteria.get("importance") != null) {
        predicates.add(cb.and(cb.equal(root.get("importance"), (Integer)criteria.get("importance"))));
      }
      if((Integer)criteria.get("topic") != null) {
        predicates.add(cb.and(cb.equal(root.get("topic"), (Integer)criteria.get("topic"))));
      }
//      if((Attendee)criteria.get("attendees") != null) {
//       
//        predicates.add(cb.and(root.get("a").in((Attendee)criteria.get("attendees"))));
//      }
      return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
  });
  }
}
