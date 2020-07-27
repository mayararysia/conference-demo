package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.Attendee;
import com.rysia.conferencedemo.repositories.AttendeeRepository;
import io.swagger.annotations.ApiOperation;
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
public class AttendeesController {
    @Autowired
    private AttendeeRepository attendeeRepository;

    @ApiOperation(value = "LIST ALL ATTENDEES")
    @GetMapping("/attendees")
    public ResponseEntity<List<Attendee>> list() {
        List<Attendee> attendees = this.attendeeRepository.findAll();
        if (attendees.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        attendees.stream().forEach(attendee -> attendee.add(linkTo(methodOn(AttendeesController.class)
                .get(attendee.getId()))
                .withSelfRel()));

        return new ResponseEntity<List<Attendee>>(attendees, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE ATTENDEE")
    @GetMapping("/attendee/{id}")
    public ResponseEntity<Attendee> get(@PathVariable(value = "id") Long id) {
        Optional<Attendee> optionalAttendee = this.attendeeRepository.findById(id);

        if (!optionalAttendee.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalAttendee.get().add(linkTo(methodOn(AttendeesController.class)
                .list()).withRel("List of Attendees"));

        return new ResponseEntity<Attendee>(optionalAttendee.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE AN ATTENDEE")
    @PostMapping("/attendee")
    public ResponseEntity<Attendee> create(@RequestBody @Valid final Attendee attendee) {
        return new ResponseEntity<Attendee>(this.attendeeRepository.saveAndFlush(attendee), HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE AN ATTENDEE")
    @RequestMapping(value = "/attendee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Attendee> update(@PathVariable(value = "id") Long id, @RequestBody @Valid Attendee attendee) {
        Optional<Attendee> optionalAttendee = this.attendeeRepository.findById(id);
        boolean existsAttendee = optionalAttendee.isPresent();
        Attendee existingAttendee = new Attendee();

        if (existsAttendee) {
            existingAttendee = optionalAttendee.get();
            BeanUtils.copyProperties(attendee, existingAttendee, "id");
        }

        return existsAttendee ? new ResponseEntity<>(this.attendeeRepository.saveAndFlush(existingAttendee), HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE AN ATTENDEE")
    @RequestMapping(value = "/attendee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.attendeeRepository.findById(id)).isPresent()) {
            this.attendeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
