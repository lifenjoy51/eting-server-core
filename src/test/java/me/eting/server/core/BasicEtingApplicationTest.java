package me.eting.server.core;

import me.eting.common.domain.EtingLang;
import me.eting.common.domain.reply.Emoticon;
import me.eting.common.domain.reply.ExchangedReply;
import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.story.ExchangedStory;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;
import me.eting.common.util.EtingUtil;
import me.eting.common.util.TestUtil;
import me.eting.server.core.service.reply.ReplyService;
import me.eting.server.core.service.story.StoryQueue;
import me.eting.server.core.service.story.StoryQueueConsumer;
import me.eting.server.core.service.story.StoryService;
import me.eting.server.core.service.story.postbox.Postbox;
import me.eting.server.core.service.story.postbox.PostboxRegistry;
import me.eting.server.core.service.user.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
public class BasicEtingApplicationTest {

    //사용자.
    Map<String, Incognito> users;

    //작성한 이야기.
    Map<String, Story> storyMap;

    //교환한 이야기.
    Map<String, ExchangedStory> exchangedStoryMap;

    //답장들
    Map<String, Reply> replyMap;

    //교환한 답장
    Map<String, ExchangedReply> exchangedReplyMap;

    @Autowired
    UserService userService;

    @Autowired
    StoryService storyService;

    @Autowired
    ReplyService replyService;

    @Autowired
    StoryQueue storyQueue;

    @Autowired
    PostboxRegistry postboxRegistry;

    @Autowired
    StoryQueueConsumer storyQueueConsumer;

    @Before
    public void setUp() throws Exception {
        //유저 준비. tom, amy, ted.
        users = new HashMap<String, Incognito>();
        //등록 @1 - TOM
        users.put("tom", userService.register(randomDevice(), EtingLang.koKR));
        //등록 @2 - AMY
        users.put("amy", userService.register(randomDevice(), EtingLang.koKR));
        //등록 @3 - TED
        users.put("ted", userService.register(randomDevice(), EtingLang.koKR));

        //맵 초기화
        storyMap = new HashMap<String, Story>();
        replyMap = new HashMap<String, Reply>();
        exchangedStoryMap = new HashMap<String, ExchangedStory>();
        exchangedReplyMap = new HashMap<String, ExchangedReply>();
    }

    @Test
    @Transactional
    public void testMain() throws Exception {
        //사용자.
        Incognito tom = users.get("tom");
        Incognito amy = users.get("amy");
        Incognito ted = users.get("ted");

        // 등록 @ 
        // 이야기 작성 # 
        // 이야기 받기 $ 
        // 답장 쓰기 % 
        // 이야기 신고 ^
        // 답장신고 & 
        // 답장삭제 * 
        // 답장즐찾 +

        //작성 @1 - #1 > [@1#1]
        storyMap.put("#1", storyService.save(randomStory(tom)));
        storyQueueConsumer.handleStories();
        monitoring("작성 @1 - #1 > [@1#1]");
        assertNotNull(storyMap.get("#1"));

        //받기 @1 - [@1#1] > 못받음.
        //assertNull(storyService.exchange(tom));

        //작성 @2 - #2 > [@1#1, @2#2]
        storyMap.put("#2", storyService.save(randomStory(amy)));
        storyQueueConsumer.handleStories();
        monitoring("작성 @2 - #2 > [@1#1, @2#2]");
        assertNotNull(storyMap.get("#2"));

        //받기 @2 - [@1#1, @2#2] > @1#1 받음 > $1
        //잠시 쉬었다가.
        exchangedStoryMap.put("$1", storyService.exchange(tom));
        monitoring("받기 @2 - [@1#1, @2#2] > @1#1 받음 > $1");
        assertEquals(storyMap.get("#1"), exchangedStoryMap.get("$1").getStory());

        //작성 @1 - #3 > [@1#1, @2#2, @1#3]
        storyMap.put("#3", storyService.save(randomStory(tom)));
        storyQueueConsumer.handleStories();
        monitoring("작성 @1 - #3 > [@1#1, @2#2, @1#3]");
        assertNotNull(storyMap.get("#3"));

        //받기 @1 - [@1#1, @2#2, @1#3] > @2#2 받음 > $2
        exchangedStoryMap.put("$2", storyService.exchange(tom));
        monitoring("받기 @1 - [@1#1, @2#2, @1#3] > @2#2 받음 > $2");
        assertEquals(storyMap.get("#2"), exchangedStoryMap.get("$2").getStory());

        //답장 @2 - $1 > %1 > [@2#2, @1#3]
        replyMap.put("%1", replyService.save(newReply(exchangedStoryMap.get("$1"))));

        //받기 @3 - [@2#2, @1#3] > @2#2 받음 > $3
        //답장 @1 - $2 > %2 > [@1#3] > 이야기 작성자에게 전송.
        //답장 @3 - $3 > %3 > [@1#3] > 이건 무시.
        //작성 @2 - #4 > [@1#3, @2#4]
        //작성 @2 - #5 > [@1#3, @2#4, @2#5]
        //받기 @1 - [@1#3, @2#4, @2#5] > @2#4 > $4
        //패스 @1 - $4
        //작성 @3 - #6 > [@1#3, @2#4, @2#5, @3#6]
        //받기 @3 - [@1#3, @2#4, @2#5, @3#6] > @1#3 > $5
        //신고 @3 - $5 > ^1
        //받기 @1 - [@1#3, @2#4, @2#5, @3#6] > @2#4 > $6
    }

    /**
     * 현재 queue 상태를 출력한다.
     */
    private void monitoring(String msg) {
        System.out.println(msg);

        //storyqueue 상태 출력.
        System.out.print("story queue : ");
        for (Story s : storyQueue.print()) {
            System.out.print(s.getId() + " | ");
        }
        System.out.println();

        //postbox 상태 출력.
        System.out.print("postbox : ");
        for (Map.Entry<Long, Postbox> e : postboxRegistry.print().entrySet()) {
            System.out.print(String.valueOf(e.getKey()) + "_" + e.getValue() + " | ");
        }
        System.out.println();

    }

    /**
     * 기기 자동생성.
     *
     * @return
     */
    private Device randomDevice() throws InterruptedException {
        Device device = new Device();
        device.setUuid(UUID.randomUUID().toString());
        device.setOs("A");
        device.setPushKey(RandomStringUtils.randomAscii(160));
        Thread.sleep(100); //휴식.
        return device;

    }

    /**
     * 이야기를 하나 생성한다.
     *
     * @param incognito
     * @return
     */
    private Story randomStory(Incognito incognito) throws InterruptedException {
        Story story = new Story();
        story.setContent(TestUtil.randomStoryText());
        story.setId(EtingUtil.generatedId(incognito.getId()));
        story.setIncognito(incognito);
        Thread.sleep(1000); //휴식.
        return story;
    }

    /**
     * 답장을 작성한다.
     *
     * @param exchangedStory
     * @return
     */
    private Reply newReply(ExchangedStory exchangedStory) {
        Reply reply = new Reply(exchangedStory);
        reply.setContent(TestUtil.randomReplyText());
        reply.getEmoticon().add(Emoticon.FINE); //이거 맞어??
        return reply;
    }
}