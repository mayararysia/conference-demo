package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.AttendeeTicket;
import com.firstproject.conferencedemo.repositories.AttendeeTicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendees/tickets")
public class AttendeeTicketsController {
    @Autowired
    private AttendeeTicketRepository attendeeTicketRepository;

    @GetMapping
    @ResponseBody
    public List<AttendeeTicket> list() {
        return this.attendeeTicketRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    @ResponseBody
    public AttendeeTicket get(@PathVariable Long id) {
        return this.attendeeTicketRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttendeeTicket create(@RequestBody final AttendeeTicket attendeeTicket) {
        return this.attendeeTicketRepository.saveAndFlush(attendeeTicket);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public AttendeeTicket update(@PathVariable Long id, @RequestBody AttendeeTicket attendeeTicket) {
        AttendeeTicket existingAttendeeTicket = this.attendeeTicketRepository.getOne(id);
        BeanUtils.copyProperties(attendeeTicket, existingAttendeeTicket, "id");
        return this.attendeeTicketRepository.saveAndFlush(existingAttendeeTicket);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.attendeeTicketRepository.deleteById(id);
    }
}
