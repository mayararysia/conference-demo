package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.AttendeeTicket;
import com.rysia.conferencedemo.repositories.AttendeeTicketRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class AttendeeTicketsController {
    @Autowired
    private AttendeeTicketRepository attendeeTicketRepository;

    @ApiOperation(value = "LIST ALL ATTENDEES' TICKETS")
    @GetMapping("/attendees/tickets")
    public ResponseEntity<List<AttendeeTicket>> list() {
        List<AttendeeTicket> attendeeTickets = this.attendeeTicketRepository.findAll();

        return attendeeTickets.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<List<AttendeeTicket>>(attendeeTickets, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE ATTENDEE TICKET")
    @GetMapping("/attendee/ticket/{id}")
    public ResponseEntity<AttendeeTicket> get(@PathVariable(value = "id") Long id) {
        Optional<AttendeeTicket> optionalAttendeeTicket = this.attendeeTicketRepository.findById(id);
        return !optionalAttendeeTicket.isPresent() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<AttendeeTicket>(optionalAttendeeTicket.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE AN ATTENDEE TICKET")
    @PostMapping("/attendee/ticket")
    public ResponseEntity<AttendeeTicket> create(@RequestBody @Valid final AttendeeTicket attendeeTicket) {
        return new ResponseEntity<AttendeeTicket>(this.attendeeTicketRepository.saveAndFlush(attendeeTicket),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE AN ATTENDEE TICKET")
    @RequestMapping(value = "/attendee/ticket/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AttendeeTicket> update(@PathVariable(value = "id") Long id, @RequestBody @Valid AttendeeTicket attendeeTicket) {
        Optional<AttendeeTicket> optionalAttendeeTicket = this.attendeeTicketRepository.findById(id);
        boolean existsAttendeeTicket = optionalAttendeeTicket.isPresent();
        AttendeeTicket existingAttendeeTicket = new AttendeeTicket();

        if (existsAttendeeTicket) {
            existingAttendeeTicket = optionalAttendeeTicket.get();
            BeanUtils.copyProperties(attendeeTicket, existingAttendeeTicket, "id");
        }

        return existsAttendeeTicket ?
                new ResponseEntity<AttendeeTicket>(this.attendeeTicketRepository.saveAndFlush(existingAttendeeTicket),
                        HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE AN ATTENDEE TICKET")
    @RequestMapping(value = "/attendee/ticket/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.attendeeTicketRepository.findById(id)).isPresent()) {
            this.attendeeTicketRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
