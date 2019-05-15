package com.hotelreservation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String cnpj;
    private String title;
    private String address;
    private String description;
    private Double priceWeekdaysRegular;
    private Double priceWeekdaysRewards;
    private Double priceWeekendRegular;
    private Double priceWeekendRewards;
    private int rating;
    private Calendar blackoutI;
    private Calendar blackoutE;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceWeekdaysRegular() {
        return priceWeekdaysRegular;
    }

    public void setPriceWeekdaysRegular(Double priceWeekdaysRegular) {
        this.priceWeekdaysRegular = priceWeekdaysRegular;
    }

    public Double getPriceWeekdaysRewards() {
        return priceWeekdaysRewards;
    }

    public void setPriceWeekdaysRewards(Double priceWeekdaysRewards) {
        this.priceWeekdaysRewards = priceWeekdaysRewards;
    }

    public Double getPriceWeekendRegular() {
        return priceWeekendRegular;
    }

    public void setPriceWeekendRegular(Double priceWeekendRegular) {
        this.priceWeekendRegular = priceWeekendRegular;
    }

    public Double getPriceWeekendRewards() {
        return priceWeekendRewards;
    }

    public void setPriceWeekendRewards(Double priceWeekendRewards) {
        this.priceWeekendRewards = priceWeekendRewards;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Calendar getBlackoutI() {
        return blackoutI;
    }

    public void setBlackoutI(Calendar blackoutI) {
        this.blackoutI = blackoutI;
    }

    public Calendar getBlackoutE() {
        return blackoutE;
    }

    public void setBlackoutE(Calendar blackoutE) {
        this.blackoutE = blackoutE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(code, hotel.code);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code);
    }
}
