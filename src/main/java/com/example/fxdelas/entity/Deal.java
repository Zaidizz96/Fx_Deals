package com.example.fxdelas.entity;

import jakarta.persistence.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "deal")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deal-unique-id" , unique = true)
    private Long dealUniqueId;

    @Column(name = "from-currency" , nullable = false)
    @Pattern(regexp = "[A-Z]{3}" , message = "From currency ISO code must be 3 uppercase")
    private String fromCurrencyISOCode;

    @Column(name = "to-currency" , nullable = false)
    @Pattern(regexp = "[A-Z]{3}" , message = "to currency ISO code must be 3 uppercase")
    private String toCurrencyISOCode;

    @Column(name = "deal-timestamp" , nullable = false)
    private LocalDateTime dealTimeStamp;

    @Column(name = "deal-amount" , nullable = false)
    private Double dealAmount;

    public Deal() {
    }

    public Deal(Long dealUniqueId, String fromCurrencyISOCode, String toCurrencyISOCode, LocalDateTime dealTimeStamp, Double dealAmount) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrencyISOCode = fromCurrencyISOCode;
        this.toCurrencyISOCode = toCurrencyISOCode;
        this.dealTimeStamp = dealTimeStamp;
        this.dealAmount = dealAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDealUniqueId() {
        return dealUniqueId;
    }

    public void setDealUniqueId(Long dealUniqueId) {
        this.dealUniqueId = dealUniqueId;
    }

    public String getFromCurrencyISOCode() {
        return fromCurrencyISOCode;
    }

    public void setFromCurrencyISOCode(String fromCurrencyISOCode) {
        this.fromCurrencyISOCode = fromCurrencyISOCode;
    }

    public String getToCurrencyISOCode() {
        return toCurrencyISOCode;
    }

    public void setToCurrencyISOCode(String toCurrencyISOCode) {
        this.toCurrencyISOCode = toCurrencyISOCode;
    }

    public LocalDateTime getDealTimeStamp() {
        return dealTimeStamp;
    }

    public void setDealTimeStamp(LocalDateTime dealTimeStamp) {
        this.dealTimeStamp = dealTimeStamp;
    }

    public Double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Double dealAmount) {
        this.dealAmount = dealAmount;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", dealUniqueId=" + dealUniqueId +
                ", fromCurrencyISOCode='" + fromCurrencyISOCode + '\'' +
                ", toCurrencyISOCode='" + toCurrencyISOCode + '\'' +
                ", dealTimeStamp=" + dealTimeStamp +
                ", dealAmount=" + dealAmount +
                '}';
    }
}
