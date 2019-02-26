package com.jss.abhi.zealicon.model;

import java.io.Serializable;

public class EventData implements Serializable {

    private String name;
    private String description;
    private String category_id;
    private String winner1;
    private String winner2;
    private String contact_name;
    private String contact_no;
    private Integer is_active;

    public EventData() {

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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }
}
