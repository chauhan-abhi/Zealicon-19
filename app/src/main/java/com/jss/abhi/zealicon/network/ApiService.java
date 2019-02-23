package com.jss.abhi.zealicon.network;

import com.jss.abhi.zealicon.model.BackofficeResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/register")
    Call<BackofficeResponse> doRegister(
            @Body HashMap<String, String> params
    );

}
