package com.pccw.user.registration.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.user.registration.dto.request.RegisterUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class RegisterUserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void registerUserTest() {

        MvcResult mvcResult = null;
        try {

            final RegisterUserRequest registerUserRequest = new RegisterUserRequest();
            registerUserRequest.setEmail("jack@sina.cn");
            registerUserRequest.setPassword("password");
            ObjectMapper mapper = new ObjectMapper();
            final String json = mapper.writeValueAsString(registerUserRequest);

            mvcResult = mockMvc.perform(post("/v1/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                     ).andDo(print())
                     .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{'code':200,'data':{'id':1,'email':'jack@sina.cn'},'message':'new user registered'}"))
                    .andReturn();

            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
