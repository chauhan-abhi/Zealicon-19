package com.jss.abhijeet.zealicon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class ErrorResponse {

    @SerializedName("email")
    @Expose
    private List<String> email = null;
    @SerializedName("contact")
    @Expose
    private List<String> contact = null;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getContact() {
        return contact;
    }

    public void setContact(List<String> contact) {
        this.contact = contact;
    }
}