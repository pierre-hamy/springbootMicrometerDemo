package com.pierre.hamy.micrometer.demo.util;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskService {
    @GET("/actuator/health")
    Call<Health> getHealth();
}
