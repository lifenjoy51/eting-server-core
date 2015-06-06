package me.eting.server.core;

import me.eting.common.util.EtingUtil;
import me.eting.common.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class EtingRestApiTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        // We have to reset our mock between tests because the mock objects
        // are managed by the Spring container. If we would not reset them,
        // stubbing and verified behavior would "leak" from one test to another.

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    @Transactional
    public void testRegistration() throws Exception {

        System.err.println(new Date().toString());

        String dt = String.valueOf(new Date().getTime());

        String result = mockMvc.perform(post("/api/v1/device")
                        .param("uuid", "16b72c7c-6d8d-471d-9615-bb06d40ea748")
                        .param("pushKey", "APA91bFrp1f8U1WfpB62vCVDX3qEv8SThBGng5yfpQwM3jk9pLuSijjMPpejp-1MSSulynAYjjwWkrTHxueS0MH8bMWRf4kIQcMLtW8LHKrH76MachQs_OL7AEE2c-PR0VmnIvnctfZXkTplxj69I0LuEBYB5Ch2vg")
                        .param("ts", dt)
                        .param("os", "Android")
                        .param("lang", "koKR")
        )
                .andExpect(status().isOk())
                        //.andExpect(content().contentType(MediaType.ALL))
                        //.andExpect(jsonPath("$uuid", is("16b72c7c-6d8d-471d-9615-bb06d40ea748")))
                .andReturn().getResponse().getContentAsString();

        System.err.println(result);

    }

    @Test
    @Transactional
    public void testSaveStory() throws Exception {

        System.err.println(new Date().toString());
        
        String content = TestUtil.randomStoryText();
        long storyId = EtingUtil.generatedId(1);

        String result = mockMvc.perform(post("/api/v1/story")
                        .param("id", String.valueOf(storyId))
                        .param("content", content)
        )
                .andExpect(status().isOk())
                        //.andExpect(content().contentType(MediaType.ALL))
                        //.andExpect(jsonPath("$uuid", is("16b72c7c-6d8d-471d-9615-bb06d40ea748")))
                .andReturn().getResponse().getContentAsString();

        System.err.println(result);

    }
    
    
}