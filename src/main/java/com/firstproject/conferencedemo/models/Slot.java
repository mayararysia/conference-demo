package com.firstproject.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "timeSlots")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_slot_id")
    private Long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name ="time_slot_date")
    private Date timeSlotDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    @Column(name ="start_time")
    private Date startTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    @Column(name ="end_time")
    private Date endTime;
    @Column(name ="is_keynote_time_slot")
    private Boolean keynoteTimeSlot;

    @OneToMany(mappedBy = "slot")
    @JsonIgnore
    private List<SessionSchedule> sessionSchedules;

    public Slot() {
        this.sessionSchedules = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimeSlotDate() {
        return timeSlotDate;
    }

    public void setTimeSlotDate(Date timeSlotDate) {
        this.timeSlotDate = timeSlotDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getKeynoteTimeSlot() {
        return keynoteTimeSlot;
    }

    public void setKeynoteTimeSlot(Boolean keynoteTimeSlot) {
        this.keynoteTimeSlot = keynoteTimeSlot;
    }

    public List<SessionSchedule> getSessionSchedules() {
        return sessionSchedules;
    }

    public void setSessionSchedules(List<SessionSchedule> sessionSchedules) {
        this.sessionSchedules = sessionSchedules;
    }
}
