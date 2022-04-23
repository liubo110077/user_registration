package com.pccw.user.registration.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.user.registration.dto.request.ChangeUserEmailRequest;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ChangeUserEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void changeEmailTest() {

        MvcResult mvcResult = null;
        try {

            final ChangeUserEmailRequest request = new ChangeUserEmailRequest();
            request.setUserId(8);
            request.setEmail("jack@sina.cn");
            ObjectMapper mapper = new ObjectMapper();
            final String json = mapper.writeValueAsString(request);

            mvcResult = mockMvc.perform(put("/v1/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{'code':1002,'message':'user 8 does not exist.'}"))
                    .andReturn();

            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
