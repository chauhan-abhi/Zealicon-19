package com.jss.abhi.zealicon.model;

import java.io.Serializable;

public class EventData implements Serializable {

    private String name;
    private String description;
    private Integer id;
    private Integer category_id;
    private Integer society_id;
    private Integer winner1;
    private Integer winner2;
    private String contact_name;
    private Long contact_no;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getSociety_id() {
        return society_id;
    }

    public void setSociety_id(Integer society_id) {
        this.society_id = society_id;
    }

    public Integer getWinner1() {
        return winner1;
    }

    public void setWinner1(Integer winner1) {
        this.winner1 = winner1;
    }

    public Integer getWinner2() {
        return winner2;
    }

    public void setWinner2(Integer winner2) {
        this.winner2 = winner2;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public Long getContact_no() {
        return contact_no;
    }

    public void setContact_no(Long contact_no) {
        this.contact_no = contact_no;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }
}
