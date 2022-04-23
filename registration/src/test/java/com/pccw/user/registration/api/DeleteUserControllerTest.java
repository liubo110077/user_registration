package com.pccw.user.registration.api;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class DeleteUserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void deleteUserTest() {

        MvcResult mvcResult = null;
        try {

            mvcResult = mockMvc.perform(delete("/v1/user/"+"9")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{'code':1002,'message':'user 9 does not exist.'}"))
                    .andReturn();

            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void batchDeleteUsersTest() {

        MvcResult mvcResult = null;
        try {

            mvcResult = mockMvc.perform(delete("/v1/user/"+"5,6"+"/batch")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{'code':1002,'message':'user 5 does not exist.user 6 does not exist.'}"))
                    .andReturn();

            log.info(mvcResult.getResponse().getContentAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
