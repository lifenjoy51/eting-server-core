package me.eting.server.core.service.reply;

import me.eting.common.domain.EtingLang;
import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.story.ExchangedStory;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;
import me.eting.common.domain.user.UserOS;
import me.eting.server.core.TestConfig;
import me.eting.server.core.service.story.StoryQueueConsumer;
import me.eting.server.core.service.story.StoryService;
import me.eting.server.core.service.user.UserService;
import me.eting.server.core.util.NoAvailableStoryException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@Transactional
public class ReplyServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    StoryService storyService;

    @Autowired
    StoryQueueConsumer storyQueueConsumer;

    @Autowired
    ReplyService replyService;

    Incognito tom;
    Incognito amy;

    ExchangedStory amys;

    @Before
    public void setUp() throws Exception, NoAvailableStoryException {
        tom = registUser();   //유저를 등록한다.
        amy = registUser();
        //이야기먼저넣어놓고..
        testSave();
        //교환하고..
        testExchange();
    }

    private Incognito registUser() {

        //유저 생성. tom.
        Device device = new Device();
        device.setUuid(UUID.randomUUID().toString());
        device.setTs(new Date());
        device.setOs(UserOS.Android);
        device.setPushKey(RandomStringUtils.randomAscii(160));

        //등록.
        Incognito tomsIncognito = userService.register(device, EtingLang.basic);
        return tomsIncognito;
    }

    @Test
    @Transactional  //lazy-loading 에러나지 않게 하기 위해.
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

        //대기열확인.
        storyQueueConsumer.handleStories();

    }

    @Test
    @Transactional
    public void testExchange() throws Exception, NoAvailableStoryException {
        //받기
        ExchangedStory toms = storyService.exchange(tom);

        //확인
        //assertNull(toms.getStory());


        //받기
        amys = storyService.exchange(amy);

        //확인
        assertNotNull(amys.getStory());

        //출력
        System.out.println("exchanged");
        System.out.println(toms);
        System.out.println(amys);
        
    }

    @Test
    @Transactional
    public void testReply() throws Exception {
        //답장을 작성해봅시다아..
        Reply reply = new Reply(amys);
        reply.setContent("test reply");

        Reply saved = replyService.save(reply);
        System.out.println(saved);
    }

    public void testPass() throws Exception {

    }

    public void testReport() throws Exception {

    }
}