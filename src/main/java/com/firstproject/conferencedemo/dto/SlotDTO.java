package com.firstproject.conferencedemo.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class SlotDTO {
    private Long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date timeSlotDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date startTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
    private Date endTime;
    private Boolean keynoteTimeSlot;


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
}
