package com.pccw.user.registration.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.user.registration.dto.request.ChangeUserEmailRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class RetrieveUserEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void retrieveUserTest() {

        MvcResult mvcResult = null;
        try {

            mvcResult = mockMvc.perform(get("/v1/user/"+"3")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{'code':1002,'message':'user 3 does not exist.'}"))
                    .andReturn();

            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
