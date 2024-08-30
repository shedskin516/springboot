package com.example.demo.hm1.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class University {

    private List<String> domains;

    @JsonProperty("state-province")
    private String state_province;

    private String country;

    private List<String> web_pages;

    private String name;

    private String alpha_two_code;

    public University() {
    }

    public University(List<String> domains, String stateProvince, String country, List<String> web_pages, String name, String alpha_two_code) {
        this.domains = domains;
        this.state_province = stateProvince;
        this.country = country;
        this.web_pages = web_pages;
        this.name = name;
        this.alpha_two_code = alpha_two_code;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getStateProvince() {
        return state_province;
    }

    public void setStateProvince(String state_province) {
        this.state_province = state_province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getWeb_pages() {
        return web_pages;
    }

    public void setWeb_pages(List<String> web_pages) {
        this.web_pages = web_pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha_two_code() {
        return alpha_two_code;
    }

    public void setAlpha_two_code(String alpha_two_code) {
        this.alpha_two_code = alpha_two_code;
    }

}
