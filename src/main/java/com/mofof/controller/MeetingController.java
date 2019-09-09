package com.mofof.controller;

import com.mofof.dto.MeetingForShow;
import com.mofof.entity.fund.Meeting;
import com.mofof.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HanWeiFeng 2017年6月25日
 */
@RestController
@RequestMapping(path = "/meeting")
public class MeetingController {
    @Autowired
    MeetingService meetingService;

//    @GetMapping(path = "/all")
//    public List<Meeting> allMeetings(Long fundId) {
//        return meetingService.findAllMeetings(fundId);
//    }

    @GetMapping(path = "/all")
    public List<MeetingForShow> allMeetings(Long fundId) {
        return meetingService.findAllMeetings(fundId);
    }

    @PostMapping(path = "/save")
    public Meeting newRecord(@RequestBody Meeting meeting) {
        return meetingService.save(meeting);
    }

    @GetMapping(path = "/meeting")
    public Meeting getMeeting(Long id) {
        return meetingService.findById(id);
    }
}
