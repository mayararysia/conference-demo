package com.firstproject.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "discountCodes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_code_id")
    private Long id;
    @Column(name = "discount_name")
    private String discountName;
    @Column(name = "discount_code")
    private String discountCode;
    @Column(name = "discount_type")
    private String discountType;
    @Column(name = "discount_amount")
    private Integer discountAmount;

    @OneToMany(mappedBy = "discountCode")
    @JsonIgnore
    private List<AttendeeTicket> attendeeTickets;

    public DiscountCode() {
        this.attendeeTickets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<AttendeeTicket> getAttendeeTickets() {
        return attendeeTickets;
    }

    public void setAttendeeTickets(List<AttendeeTicket> attendeeTickets) {
        this.attendeeTickets = attendeeTickets;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
