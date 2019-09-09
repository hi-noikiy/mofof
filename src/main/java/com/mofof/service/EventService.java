package com.mofof.service;

import com.mofof.entity.relation.Event;
import com.mofof.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author HanWeiFeng 2017年6月18日
 */
@Service
@Transactional
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents(Long relationId) {
        return eventRepository.findAllByInvestRelationIdOrderByIdDesc(relationId);
    }

    public Event findById(Long id) {
        return eventRepository.findOne(id);
    }

}
