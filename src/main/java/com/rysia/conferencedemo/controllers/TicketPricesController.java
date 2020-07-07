package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.dto.TicketPriceDTO;
import com.rysia.conferencedemo.models.TicketPrice;
import com.rysia.conferencedemo.repositories.TicketPriceRepository;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class TicketPricesController {
    @Autowired
    private TicketPriceRepository ticketPriceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value="LIST ALL TICKET PRICES")
    @GetMapping("/tickets")
    public List<TicketPrice> list(){
        return this.ticketPriceRepository.findAll();
    }

    @ApiOperation(value="GET A UNIQUE TICKET PRICE")
    @GetMapping("/ticket/{id}")
    public TicketPrice get(@PathVariable Long id){
        return this.ticketPriceRepository.getOne(id);
    }

    @ApiOperation(value="CREATE A TICKET")
    @PostMapping("/ticket")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketPrice create(@RequestBody final TicketPriceDTO ticketPriceDTO){
        TicketPrice ticketPrice = convertToEntity(ticketPriceDTO);
        return this.ticketPriceRepository.saveAndFlush(ticketPrice);
    }

    @ApiOperation(value="DELETE A TICKET")
    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        this.ticketPriceRepository.deleteById(id);
    }

    @ApiOperation(value="UPDATE TICKET")
    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public TicketPrice update(@PathVariable Long id, @RequestBody TicketPriceDTO ticketPriceDTO){
        TicketPrice ticketPrice = convertToEntity(ticketPriceDTO);
        TicketPrice existingTicketPrice = this.ticketPriceRepository.getOne(id);
        BeanUtils.copyProperties(ticketPrice, existingTicketPrice, "ticket_price_id");
        return this.ticketPriceRepository.saveAndFlush(existingTicketPrice);
    }

    private TicketPrice convertToEntity(TicketPriceDTO ticketPriceDTO) {
        TicketPrice ticketPrice = modelMapper.map(ticketPriceDTO, TicketPrice.class);
        return ticketPrice;
    }
}
