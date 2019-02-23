package com.jss.abhi.zealicon.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BackofficeResponse  {

    @SerializedName("response")
    @Expose
    String response;    // response code 200 for ok 500 for errors

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("errors")
    @Expose
    ErrorResponse errors;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ErrorResponse getErrors() {
        return errors;
    }

    public void setErrors(ErrorResponse errors) {
        this.errors = errors;
    }
}
