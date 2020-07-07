package com.rysia.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "sessionSchedule")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SessionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;
    private String room;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "session_id" )
    private Session session;

    public SessionSchedule() {
        this.slot = new Slot();
        this.session = new Session();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
