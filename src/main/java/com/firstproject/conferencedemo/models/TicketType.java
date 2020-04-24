package com.firstproject.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ticketTypes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ticket_type_code")
    private String ticketTypeCode;
    @Column(name="ticket_type_name")
    private String ticketTypeName;
    private String description;
    @Column(name = "includes_workshop")
    private Boolean includesWorkshop;

    @OneToMany(mappedBy = "ticketType")
    @JsonIgnore
    private List<TicketPrice> ticketPrices;

    public TicketType(){
        this.ticketPrices = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketTypeCode() {
        return ticketTypeCode;
    }

    public void setTicketTypeCode(String ticketTypeCode) {
        this.ticketTypeCode = ticketTypeCode;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIncludesWorkshop() {
        return includesWorkshop;
    }

    public void setIncludesWorkshop(Boolean includesWorkshop) {
        this.includesWorkshop = includesWorkshop;
    }

    public List<TicketPrice> getTicketPrices() {
        return ticketPrices;
    }

    public void setTicketPrices(List<TicketPrice> ticketPrices) {
        this.ticketPrices = ticketPrices;
    }
}
