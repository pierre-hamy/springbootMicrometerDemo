package com.pierre.hamy.micrometer.demo;

import com.pierre.hamy.micrometer.demo.util.APIClient;
import com.pierre.hamy.micrometer.demo.util.Health;
import com.pierre.hamy.micrometer.demo.util.TaskService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.annotation.PostConstruct;

@Slf4j
//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class MicrometerTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private MockMvc mockMvc;

    Retrofit retrofit;
    @PostConstruct
    public void init() {
        retrofit = APIClient.getRetrofitInstance(port);
    }

    @Test
    void getSimpleMetrics() {
        String test = "aa";
        assert(test).contains("a");
    }

    @Test
    @SneakyThrows
    void getHealthWithRetrofit() {

        TaskService taskService = retrofit.create(TaskService.class);
        Call<Health> result = taskService.getHealth();
        Response<Health> response = result.execute();
        assert(response).isSuccessful();
        assert(response).body().getStatus().equals("UP");

    }
    @Test
    @SneakyThrows
    void getHealthWithMockMvc() {
        this.mockMvc
                .perform(get("/actuator/health"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"status\":\"UP\"}")));

    }
}
