package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.models.TicketType;
import com.rysia.conferencedemo.repositories.TicketTypeRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketTypesController {
    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @ApiOperation(value = "LIST ALL TICKET TYPES")
    @GetMapping("/types")
    public List<TicketType> list() {
        return this.ticketTypeRepository.findAll();
    }

    @ApiOperation(value = "GET A UNIQUE TICKET TYPE")
    @GetMapping("/type/{id}")
    public TicketType get(@PathVariable Long id) {
        TicketType existingTicketType = this.ticketTypeRepository.getOne(id);
        if (id == null)
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        return existingTicketType;
    }

    @ApiOperation(value = "CREATE A TICKET TYPE")
    @PostMapping("/type")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketType create(@RequestBody final TicketType ticketType) {
        if (ticketType == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        return this.ticketTypeRepository.saveAndFlush(ticketType);
    }

    @ApiOperation(value = "UPDATE A TICKET TYPE")
    @RequestMapping(value = "/type/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public TicketType update(@PathVariable Long id, @RequestBody TicketType ticketType) {
        if (id == null || ticketType == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        TicketType existingTicketType = this.ticketTypeRepository.getOne(id);
        if (existingTicketType == null)
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        BeanUtils.copyProperties(ticketType, existingTicketType, "id");
        return this.ticketTypeRepository.saveAndFlush(existingTicketType);
    }

    @ApiOperation(value = "DELETE A TICKET TYPE")
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        if (id == null)
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        this.ticketTypeRepository.deleteById(id);
    }
}
