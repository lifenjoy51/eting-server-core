package me.eting.server.core.service.story;

import me.eting.TestConfig;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;
import me.eting.server.core.service.user.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class StoryServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    StoryService storyService;

    Incognito tom;

    @Before
    public void setUp() throws Exception {
        tom = registUser();   //유저를 등록한다.
    }

    private Incognito registUser() {

        //유저 생성. tom.
        Device tomsDevice = new Device();
        tomsDevice.setUuid(UUID.randomUUID().toString());
        tomsDevice.setTs(new Date());
        tomsDevice.setOs("A");
        tomsDevice.setPushKey(RandomStringUtils.randomAscii(160));

        //등록.
        Incognito tomsIncognito = userService.register(tomsDevice);
        return tomsIncognito;
    }

    @Test
    public void testSave() throws Exception {
        //저장할 이야기.
        Story story = new Story();
        story.setContent("test content....");
        story.setTs(new Date());
        story.setId(1234543123);
        story.setIncognito(tom);

        //저장.
        Story savedStory = storyService.save(story);

        //확인
        assertNotNull(savedStory);

        //출력
        System.out.println(savedStory);

    }
}