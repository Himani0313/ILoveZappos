package com.example.hjshah2.ilovezappos.Utils;

import com.example.hjshah2.ilovezappos.Services.ZapposAPI;
import com.example.hjshah2.ilovezappos.Services.retrofitClient;

/**
 * Created by hjshah2 on 2/5/2017.
 */

public class APIUtils {
    public static final String BASE_URL = "https://api.zappos.com/";
    public static ZapposAPI getService() {
        return retrofitClient.getClient(BASE_URL).create(ZapposAPI.class);
    }
}
