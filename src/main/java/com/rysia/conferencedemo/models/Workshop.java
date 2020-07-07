package com.rysia.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "workshops")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workshop_id")
    private Long id;
    @Column(name = "workshop_name")
    private String workshopName;
    private String description;
    private String requirements;
    private String room;
    private Integer capacity;

    @ManyToMany
    @JoinTable(
            name = "workshop_speakers",
            joinColumns = @JoinColumn(name = "workshop_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers;
    @ManyToMany
    @JoinTable(
            name = "workshop_registrations",
            joinColumns = @JoinColumn(name = "workshop_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_ticket_id")
    )
    private List<AttendeeTicket> attendeeTickets;

    public Workshop(){
        this.speakers = new ArrayList<>();
        this.attendeeTickets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public List<AttendeeTicket> getAttendeeTickets() {
        return attendeeTickets;
    }

    public void setAttendeeTickets(List<AttendeeTicket> attendeeTickets) {
        this.attendeeTickets = attendeeTickets;
    }
}
