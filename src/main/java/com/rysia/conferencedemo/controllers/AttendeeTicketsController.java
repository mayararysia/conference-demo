package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.AttendeeTicket;
import com.rysia.conferencedemo.repositories.AttendeeTicketRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class AttendeeTicketsController {
    @Autowired
    private AttendeeTicketRepository attendeeTicketRepository;

    @ApiOperation(value = "LIST ALL ATTENDEES' TICKETS")
    @GetMapping("/attendees/tickets")
    public List<AttendeeTicket> list() {
        return this.attendeeTicketRepository.findAll();
    }

    @ApiOperation(value = "GET A UNIQUE ATTENDEE TICKET")
    @GetMapping("/attendee/ticket/{id}")
    public AttendeeTicket get(@PathVariable Long id) {
        return this.attendeeTicketRepository.getOne(id);
    }

    @ApiOperation(value = "CREATE AN ATTENDEE TICKET")
    @PostMapping("/attendee/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public AttendeeTicket create(@RequestBody final AttendeeTicket attendeeTicket) {
        return this.attendeeTicketRepository.saveAndFlush(attendeeTicket);
    }

    @ApiOperation(value = "UPDATE AN ATTENDEE TICKET")
    @RequestMapping(value = "/attendee/ticket/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public AttendeeTicket update(@PathVariable Long id, @RequestBody AttendeeTicket attendeeTicket) {
        AttendeeTicket existingAttendeeTicket = this.attendeeTicketRepository.getOne(id);
        BeanUtils.copyProperties(attendeeTicket, existingAttendeeTicket, "id");
        return this.attendeeTicketRepository.saveAndFlush(existingAttendeeTicket);
    }

    @ApiOperation(value = "DELETE AN ATTENDEE TICKET")
    @RequestMapping(value = "/attendee/ticket/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.attendeeTicketRepository.deleteById(id);
    }
}
