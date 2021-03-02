package com.pierre.hamy.micrometer.demo.util;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class APIClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://localhost";


    public static Retrofit getRetrofitInstance(int webEnvironment) {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL + ":" + webEnvironment)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
