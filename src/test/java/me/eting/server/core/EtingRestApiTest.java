package me.eting.server.core;

import com.fasterxml.jackson.annotation.JsonValue;
import me.eting.common.domain.reply.Emoticon;
import me.eting.common.util.EtingUtil;
import me.eting.common.util.TestUtil;
import net.minidev.json.JSONUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EtingRestApiTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    String exchangedId;

    @Before
    public void setUp() {
        // We have to reset our mock between tests because the mock objects
        // are managed by the Spring container. If we would not reset them,
        // stubbing and verified behavior would "leak" from one test to another.

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        try {
            test01Registration(); //1
            test01Registration(); //2
            test02SaveStory();    //1
            test03Exchange();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //@Test
    @Transactional
    public void test01Registration() throws Exception {

        System.err.println(new Date().toString());

        String dt = String.valueOf(new Date().getTime());

        String result = mockMvc.perform(post("/api/v1/device")
                        .param("uuid", UUID.randomUUID().toString())
                        .param("pushKey", RandomStringUtils.randomAscii(160))
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

    //@Test
    @Transactional
    public void test02SaveStory() throws Exception {

        System.out.println("testSaveStory");
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

    //@Test
    @Transactional
    public void test03Exchange() throws Exception {

        System.out.println("testExchange");
        System.err.println(new Date().toString());

        String content = TestUtil.randomStoryText();
        long storyId = EtingUtil.generatedId(1);

        mockMvc.perform(get("/api/test"));

        String result = mockMvc.perform(get("/api/v1/exchange/story")
                        .param("incognitoId", String.valueOf(2))
        )
                .andExpect(status().isOk())
                        //.andExpect(content().contentType(MediaType.ALL))
                        //.andExpect(jsonPath("$uuid", is("16b72c7c-6d8d-471d-9615-bb06d40ea748")))
                .andReturn().getResponse().getContentAsString();

        System.err.println(result);

        String exchangedJsonString = result;
        JSONObject obj = (JSONObject) new JSONParser().parse(exchangedJsonString);
        exchangedId = String.valueOf(obj.get("id"));

    }



    @Test
    @Transactional
    public void test04SaveReply() throws Exception {

        System.out.println("testSaveReply");
        System.out.println("exchangedId " +exchangedId);
        System.err.println(new Date().toString());

        String content = TestUtil.randomReplyText();
        String emoticonList = Emoticon.FINE.toString();

        String result = mockMvc.perform(post("/api/v1/exchange/" + exchangedId + "/reply")
                        .param("exchangeId", exchangedId)
                        .param("content", content)
                        .param("emoticonList", emoticonList)
        ).andDo(print())
                .andExpect(status().isOk())
                        //.andExpect(content().contentType(MediaType.ALL))
                        //.andExpect(jsonPath("$uuid", is("16b72c7c-6d8d-471d-9615-bb06d40ea748")))
                .andReturn().getResponse().getContentAsString();

        System.err.println(result);

    }

    
    
}