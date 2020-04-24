package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.Attendee;
import com.firstproject.conferencedemo.repositories.AttendeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
public class AttendeesController {
    @Autowired
    private AttendeeRepository attendeeRepository;

    @GetMapping
    public List<Attendee> list() {
        return this.attendeeRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Attendee get(@PathVariable Long id) {
        return this.attendeeRepository.getOne(id);
    }

    @PostMapping
    public Attendee create(@RequestBody final Attendee attendee) {
        return this.attendeeRepository.saveAndFlush(attendee);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Attendee update(@PathVariable Long id, @RequestBody Attendee attendee) {
        Attendee existingAttendee = this.attendeeRepository.getOne(id);
        BeanUtils.copyProperties(attendee, existingAttendee, "id");
        return this.attendeeRepository.saveAndFlush(existingAttendee);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.attendeeRepository.deleteById(id);
    }
}
