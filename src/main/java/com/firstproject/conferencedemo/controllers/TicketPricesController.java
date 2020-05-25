package com.firstproject.conferencedemo.controllers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.firstproject.conferencedemo.models.TicketPrice;
import com.firstproject.conferencedemo.repositories.TicketPriceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketPricesController {
    @Autowired
    private TicketPriceRepository ticketPriceRepository;

    @GetMapping
    @ResponseBody
    public List<TicketPrice> list(){
        return this.ticketPriceRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    @ResponseBody
    public TicketPrice get(@PathVariable Long id){
        return this.ticketPriceRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketPrice create(@RequestBody final TicketPrice ticketPrice){
        return this.ticketPriceRepository.saveAndFlush(ticketPrice);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        this.ticketPriceRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public TicketPrice update(@PathVariable Long id, @RequestBody TicketPrice ticketPrice){
        TicketPrice existingTicketPrice = this.ticketPriceRepository.getOne(id);
        BeanUtils.copyProperties(ticketPrice, existingTicketPrice, "ticket_price_id");
        return this.ticketPriceRepository.saveAndFlush(existingTicketPrice);
    }
}
