package com.hotelreservation.repository.filter;

import java.util.Calendar;

public class HotelFilter {

    private Long code;
    private String cnpj;
    private String title;
    private Double priceNWK;
    private Double priceNWE;
    private Double priceAWK;
    private Double priceAWE;
    private int rating;
    private Calendar blackoutI;
    private Calendar blackoutE;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPriceNWK() {
        return priceNWK;
    }

    public void setPriceNWK(Double priceNWK) {
        this.priceNWK = priceNWK;
    }

    public Double getPriceNWE() {
        return priceNWE;
    }

    public void setPriceNWE(Double priceNWE) {
        this.priceNWE = priceNWE;
    }

    public Double getPriceAWK() {
        return priceAWK;
    }

    public void setPriceAWK(Double priceAWK) {
        this.priceAWK = priceAWK;
    }

    public Double getPriceAWE() {
        return priceAWE;
    }

    public void setPriceAWE(Double priceAWE) {
        this.priceAWE = priceAWE;
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
}
