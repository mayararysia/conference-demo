package com.rysia.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "pricingCategories")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PricingCategory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pricing_category_code")
    private String pricingCategoryCode;
    @Column(name="pricing_category_name")
    private String pricingCategoryName;
    @Column(name="pricing_start_date")
    private Date pricingStartDate;
    @Column(name="pricing_end_date")
    private Date pricingEndDate;

    @OneToMany(mappedBy = "pricingCategory")
    @JsonIgnore
    private List<TicketPrice> ticketPrices;

    public PricingCategory (){
        ticketPrices = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPricingCategoryCode() {
        return pricingCategoryCode;
    }

    public void setPricingCategoryCode(String pricingCategoryCode) {
        this.pricingCategoryCode = pricingCategoryCode;
    }

    public String getPricingCategoryName() {
        return pricingCategoryName;
    }

    public void setPricingCategoryName(String pricingCategoryName) {
        this.pricingCategoryName = pricingCategoryName;
    }

    public Date getPricingStartDate() {
        return pricingStartDate;
    }

    public void setPricingStartDate(Date pricingStartDate) {
        this.pricingStartDate = pricingStartDate;
    }

    public Date getPricingEndDate() {
        return pricingEndDate;
    }

    public void setPricingEndDate(Date pricingEndDate) {
        this.pricingEndDate = pricingEndDate;
    }

    public List<TicketPrice> getTicketPrices() {
        return ticketPrices;
    }

    public void setTicketPrices(List<TicketPrice> ticketPrices) {
        this.ticketPrices = ticketPrices;
    }


}
