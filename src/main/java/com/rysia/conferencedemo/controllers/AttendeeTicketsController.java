package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.AttendeeTicket;
import com.rysia.conferencedemo.repositories.AttendeeTicketRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class AttendeeTicketsController {
    @Autowired
    private AttendeeTicketRepository attendeeTicketRepository;

    @ApiOperation(value = "LIST ALL ATTENDEES' TICKETS")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Attendees Tickets Not Found")})
    @GetMapping("/attendees/tickets")
    public ResponseEntity<List<AttendeeTicket>> list() {
        List<AttendeeTicket> attendeeTickets = this.attendeeTicketRepository.findAll();

        if (attendeeTickets.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        attendeeTickets.stream().forEach(attendeeTicket ->
                attendeeTicket.add(linkTo(methodOn(AttendeeTicketsController.class)
                        .get(attendeeTicket.getId()))
                        .withSelfRel()));

        return new ResponseEntity<List<AttendeeTicket>>(attendeeTickets, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE ATTENDEE TICKET")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Attendee Ticket Not Found")})
    @GetMapping("/attendee/ticket/{id}")
    public ResponseEntity<AttendeeTicket> get(@PathVariable(value = "id") Long id) {
        Optional<AttendeeTicket> optionalAttendeeTicket = this.attendeeTicketRepository.findById(id);

        if (!optionalAttendeeTicket.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalAttendeeTicket.get().add(linkTo(methodOn(AttendeeTicketsController.class)
                .list())
                .withRel("List of Attendee Tickets"));

        return new ResponseEntity<AttendeeTicket>(optionalAttendeeTicket.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE AN ATTENDEE TICKET")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Attendee Ticket Created")})
    @PostMapping("/attendee/ticket")
    public ResponseEntity<AttendeeTicket> create(@RequestBody @Valid final AttendeeTicket attendeeTicket) {
        return new ResponseEntity<AttendeeTicket>(this.attendeeTicketRepository.saveAndFlush(attendeeTicket),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE AN ATTENDEE TICKET")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Updated Attendee Ticket"),
            @ApiResponse(code = 404, message = "Attendee Ticket Not Found")
    })
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted Attendee Ticket"),
            @ApiResponse(code = 404, message = "Attendee Ticket Not Found")
    })
    @RequestMapping(value = "/attendee/ticket/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.attendeeTicketRepository.findById(id)).isPresent()) {
            this.attendeeTicketRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
