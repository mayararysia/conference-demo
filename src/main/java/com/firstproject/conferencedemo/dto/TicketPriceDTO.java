package com.firstproject.conferencedemo.dto;

public class TicketPriceDTO {
    private Long ticketPriceId;
    private Long ticketTypeId;
    private Long pricingCategoryId;
    private Integer basePrice;

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

    public Long getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Long getPricingCategoryId() {
        return pricingCategoryId;
    }

    public void setPricingCategoryId(Long pricingCategoryId) {
        this.pricingCategoryId = pricingCategoryId;
    }
}
