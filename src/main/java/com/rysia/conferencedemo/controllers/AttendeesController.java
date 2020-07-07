package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.Attendee;
import com.rysia.conferencedemo.repositories.AttendeeRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class AttendeesController {
    @Autowired
    private AttendeeRepository attendeeRepository;

    @ApiOperation(value="LIST ALL ATTENDEES")
    @GetMapping("/attendees")
    public List<Attendee> list() {
        return this.attendeeRepository.findAll();
    }

    @ApiOperation(value="GET A UNIQUE ATTENDEE")
    @GetMapping("/attendee/{id}")
    public Attendee get(@PathVariable Long id) {
        return this.attendeeRepository.getOne(id);
    }

    @ApiOperation(value="CREATE AN ATTENDEE")
    @PostMapping("/attendee")
    @ResponseStatus(HttpStatus.CREATED)
    public Attendee create(@RequestBody final Attendee attendee) {
        return this.attendeeRepository.saveAndFlush(attendee);
    }

    @ApiOperation(value = "UPDATE AN ATTENDEE")
    @RequestMapping(value = "/attendee/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Attendee update(@PathVariable Long id, @RequestBody Attendee attendee) {
        Attendee existingAttendee = this.attendeeRepository.getOne(id);
        BeanUtils.copyProperties(attendee, existingAttendee, "id");
        return this.attendeeRepository.saveAndFlush(existingAttendee);
    }

    @ApiOperation(value = "DELETE AN ATTENDEE")
    @RequestMapping(value = "/attendee/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.attendeeRepository.deleteById(id);
    }
}
