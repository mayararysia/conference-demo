package com.firstproject.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "attendees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendee_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String title;
    private String company;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "attendee")
    @JsonIgnore
    private List<AttendeeTicket> attendeeTickets;

    public Attendee() {
        this.attendeeTickets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<AttendeeTicket> getAttendeeTickets() {
        return attendeeTickets;
    }

    public void setAttendeeTickets(List<AttendeeTicket> attendeeTickets) {
        this.attendeeTickets = attendeeTickets;
    }
}
