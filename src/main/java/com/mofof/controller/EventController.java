package com.mofof.controller;

import com.mofof.entity.relation.Event;
import com.mofof.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月18日
 */
@RestController
@RequestMapping(path = "/event")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping(path = "/all")
    public List<Event> allEvents(Long relationId) {
        return eventService.findAllEvents(relationId);
    }

    @PostMapping(path = "/save")
    public Event newRecord(@RequestBody Event event) {
        return eventService.save(event);
    }

    @GetMapping(path = "/event")
    public Event getEvent(Long id) {
        return eventService.findById(id);
    }
}
