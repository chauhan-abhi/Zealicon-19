package com.jss.abhijeet.zealicon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BackofficeResponse {

    @SerializedName("response")
    @Expose
    private String response;    // response code 200 for ok 500 for errors

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("errors")
    @Expose
    private ErrorResponse errors;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ErrorResponse getErrors() {
        return errors;
    }

    public String getContactErrors() {
        return (errors.getContact() != null && errors.getContact().size() > 0) ? errors.getContact().get(0)
                : "";
    }

    public String getEmailErrors() {
        return (errors.getEmail() != null && errors.getEmail().size() > 0) ? errors.getEmail().get(0) : "";
    }

    public void setErrors(ErrorResponse errors) {
        this.errors = errors;
    }
}
