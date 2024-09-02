package top.zxk.springboot.quickstart.service;

import org.springframework.stereotype.Service;
import top.zxk.springboot.quickstart.entity.Meeting;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {
    private final List<Meeting> meetings = new ArrayList<>();
    public boolean issRoomAvailable(Meeting newMeeting){
        return meetings.stream().noneMatch(exitingMeeting -> exitingMeeting.isOverlapping(newMeeting));

    }
    public void addMeeting(Meeting meeting){
        meetings.add(meeting);
    }

}