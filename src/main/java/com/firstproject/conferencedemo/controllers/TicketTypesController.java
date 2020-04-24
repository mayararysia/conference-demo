package com.firstproject.conferencedemo.controllers;

import com.firstproject.conferencedemo.models.TicketType;
import com.firstproject.conferencedemo.repositories.TicketTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets/types")
public class TicketTypesController {
    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @GetMapping
    public List<TicketType> list (){
        return this.ticketTypeRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public TicketType get(@PathVariable Long id){
        return this.ticketTypeRepository.getOne(id);
    }

    @PostMapping
    public TicketType create(@RequestBody final TicketType ticketType){
        return this.ticketTypeRepository.saveAndFlush(ticketType);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public TicketType update(@PathVariable Long id, @RequestBody TicketType ticketType){
        TicketType existingTicketType = this.ticketTypeRepository.getOne(id);
        BeanUtils.copyProperties(ticketType, existingTicketType, "id");
        return this.ticketTypeRepository.saveAndFlush(existingTicketType);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        this.ticketTypeRepository.deleteById(id);
    }
}
