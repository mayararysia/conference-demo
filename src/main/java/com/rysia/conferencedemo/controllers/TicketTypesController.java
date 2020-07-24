package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.TicketType;
import com.rysia.conferencedemo.repositories.TicketTypeRepository;
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
@RequestMapping("/api/v1/ticket")
public class TicketTypesController {
    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @ApiOperation(value = "LIST ALL TICKET TYPES")
    @GetMapping("/types")
    public ResponseEntity<List<TicketType>> list() {
        List<TicketType> ticketTypes = this.ticketTypeRepository.findAll();
        return ticketTypes.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<List<TicketType>>(ticketTypes, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE TICKET TYPE")
    @GetMapping("/type/{id}")
    public ResponseEntity<TicketType> get(@PathVariable(value = "id") Long id) {
        Optional<TicketType> optionalTicketType = this.ticketTypeRepository.findById(id);
        return !optionalTicketType.isPresent() ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<TicketType>(optionalTicketType.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE A TICKET TYPE")
    @PostMapping("/type")
    public ResponseEntity<TicketType> create(@RequestBody @Valid final TicketType ticketType) {
        return new ResponseEntity<TicketType>(this.ticketTypeRepository.saveAndFlush(ticketType), HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE A TICKET TYPE")
    @RequestMapping(value = "/type/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TicketType> update(@PathVariable(value = "id") Long id, @RequestBody @Valid TicketType ticketType) {
        Optional<TicketType> optionalTicketType = this.ticketTypeRepository.findById(id);
        TicketType existingTicketType = new TicketType();
        boolean existsTicket = optionalTicketType.isPresent();

        if (existsTicket) {
            existingTicketType = optionalTicketType.get();
            BeanUtils.copyProperties(ticketType, existingTicketType, "id");
        }

        return existsTicket ? new ResponseEntity<TicketType>(this.ticketTypeRepository.saveAndFlush(existingTicketType),
                HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE A TICKET TYPE")
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.ticketTypeRepository.findById(id)).isPresent()) {
            this.ticketTypeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
