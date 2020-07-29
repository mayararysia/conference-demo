package com.rysia.conferencedemo.controllers;

import com.rysia.conferencedemo.dto.TicketPriceDTO;
import com.rysia.conferencedemo.models.TicketPrice;
import com.rysia.conferencedemo.repositories.TicketPriceRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
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
public class TicketPricesController {
    @Autowired
    private TicketPriceRepository ticketPriceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "LIST ALL TICKET PRICES")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Tickets Not Found")})
    @GetMapping("/tickets")
    public ResponseEntity<List<TicketPrice>> list() {
        List<TicketPrice> ticketPrices = this.ticketPriceRepository.findAll();

        if (ticketPrices.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ticketPrices.stream().forEach(ticketPrice -> ticketPrice.add(linkTo(methodOn(TicketPricesController.class)
                .get(ticketPrice.getTicketPriceId()))
                .withSelfRel()));

        return new ResponseEntity<List<TicketPrice>>(ticketPrices, HttpStatus.OK);
    }

    @ApiOperation(value = "GET A UNIQUE TICKET PRICE")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Ticket Not Found")})
    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketPrice> get(@PathVariable(value = "id") Long id) {
        Optional<TicketPrice> optionalTicketPrice = this.ticketPriceRepository.findById(id);

        if (!optionalTicketPrice.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        optionalTicketPrice.get().add(linkTo(methodOn(TicketPricesController.class)
                .list())
                .withRel("List of Ticket Prices"));

        return new ResponseEntity<TicketPrice>(optionalTicketPrice.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "CREATE A TICKET")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Ticket Created")})
    @PostMapping("/ticket")
    public ResponseEntity<TicketPrice> create(@RequestBody @Valid final TicketPriceDTO ticketPriceDTO) {
        return new ResponseEntity<TicketPrice>(this.ticketPriceRepository.saveAndFlush(convertToEntity(ticketPriceDTO)),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "UPDATE TICKET")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Updated Ticket"),
            @ApiResponse(code = 404, message = "Ticket Not Found")
    })
    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TicketPrice> update(@PathVariable(value = "id") Long id, @RequestBody @Valid TicketPriceDTO ticketPriceDTO) {
        Optional<TicketPrice> optionalTicketPrice = this.ticketPriceRepository.findById(id);
        boolean existsTicket = optionalTicketPrice.isPresent();
        TicketPrice existingTicketPrice = new TicketPrice();

        if (existsTicket) {
            existingTicketPrice = optionalTicketPrice.get();
            BeanUtils.copyProperties(convertToEntity(ticketPriceDTO), existingTicketPrice, "ticket_price_id");
        }
        return existsTicket ? new ResponseEntity<TicketPrice>(this.ticketPriceRepository.saveAndFlush(existingTicketPrice), HttpStatus.ACCEPTED) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "DELETE A TICKET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted Ticket"),
            @ApiResponse(code = 404, message = "Ticket Not Found")
    })
    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        if (Optional.ofNullable(this.ticketPriceRepository.findById(id)).isPresent()) {
            this.ticketPriceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private TicketPrice convertToEntity(TicketPriceDTO ticketPriceDTO) {
        TicketPrice ticketPrice = modelMapper.map(ticketPriceDTO, TicketPrice.class);
        return ticketPrice;
    }
}
