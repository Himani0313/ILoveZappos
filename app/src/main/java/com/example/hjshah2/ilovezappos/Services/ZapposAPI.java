package com.example.hjshah2.ilovezappos.Services;

/**
 * Created by hjshah2 on 2/5/2017.
 */
import com.example.hjshah2.ilovezappos.Model.productList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ZapposAPI {
    @GET("/Search?key=b743e26728e16b81da139182bb2094357c31d331")
    Call<productList> getProducts(@Query("term") String searchTerm);
}
