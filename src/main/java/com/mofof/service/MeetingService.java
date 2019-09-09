package com.mofof.service;

import com.mofof.dto.MeetingForShow;
import com.mofof.entity.administrator.TeamMember;
import com.mofof.entity.fund.Meeting;
import com.mofof.repository.MeetingRepository;
import com.mofof.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author HanWeiFeng 2017年6月25日
 */
@Service
@Transactional
public class MeetingService {
    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    TeamMemberRepository teamMemberRepository;

    public Meeting save(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

//    public List<Meeting> findAllMeetings(Long fundId) {
//        return meetingRepository.findAllByFundIdOrderByIdDesc(fundId);
//    }

    public List<MeetingForShow> findAllMeetings(Long fundId) {
        List<Meeting> meetings = meetingRepository.findAllByFundIdOrderByIdDesc(fundId);
        List<MeetingForShow> meetingForShowList = new ArrayList<>();
        meetings.forEach(item->{
            MeetingForShow meetingForShow = new MeetingForShow(item);
            if(item.getAttendees()!=null){
                List<String> arrayList = new ArrayList<String>(Arrays.asList(item.getAttendees().split(",")));
                List<Long> idList = new ArrayList<>();
                for (String idStr:arrayList) {
                    if(idStr!=null && !"".equals(idStr) && !"undefined".equals(idStr)){
                        idList.add(Long.parseLong(idStr));
                    }
                }
                Iterable<TeamMember> tms = teamMemberRepository.findByIdIn(idList);
                if(tms!=null){
                    String names = "";
                    for (TeamMember tm :tms){
                        names += (","+tm.getIndividual().getChineseName());
                    }
                    //names = names.substring(1);
                    meetingForShow.setAttendeesForShow(names);
                }
                meetingForShowList.add(meetingForShow);
            }
        });

        return meetingForShowList;
    }

    public Meeting findById(Long id) {
        return meetingRepository.findOne(id);
    }

}
