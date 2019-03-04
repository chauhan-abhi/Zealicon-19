package com.jss.abhijeet.zealicon.model;

import java.io.Serializable;

public class EventData implements Serializable {

    private String id;
    private String name;
    private String description;
    private String societyId;
    private String winner1;
    private String winner2;
    private String contact_name;
    private String contact_no;
    private String fullDate;
    private String timing;
    private String venue;

    public EventData() {

    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    public String getWinner1() {
        return winner1;
    }

    public void setWinner1(String winner1) {
        this.winner1 = winner1;
    }

    public String getWinner2() {
        return winner2;
    }

    public void setWinner2(String winner2) {
        this.winner2 = winner2;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
}
