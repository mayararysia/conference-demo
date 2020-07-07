package com.rysia.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ticketPrices")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_price_id")
    private Long ticketPriceId;
    @Column(name = "base_price")
    private Integer basePrice;

    @ManyToOne
    @JoinColumn(name="ticket_type_id", referencedColumnName = "id")
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "pricing_id", referencedColumnName = "id")
    private PricingCategory pricingCategory;

    @OneToMany(mappedBy = "ticketPrice")
    @JsonIgnore
    private List<AttendeeTicket> attendeeTickets;

    public TicketPrice(){
        this.pricingCategory = new PricingCategory();
        this.ticketType = new TicketType();
        this.attendeeTickets = new ArrayList<>();
    }

    public Long getTicketPriceId() {
        return ticketPriceId;
    }

    public void setTicketPriceId(Long ticketPriceId) {
        this.ticketPriceId = ticketPriceId;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public PricingCategory getPricingCategory() {
        return pricingCategory;
    }

    public void setPricingCategory(PricingCategory pricingCategory) {
        this.pricingCategory = pricingCategory;
    }

    public List<AttendeeTicket> getAttendeeTickets() {
        return attendeeTickets;
    }

    public void setAttendeeTickets(List<AttendeeTicket> attendeeTickets) {
        this.attendeeTickets = attendeeTickets;
    }
}
