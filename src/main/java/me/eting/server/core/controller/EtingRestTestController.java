package me.eting.server.core.controller;

import me.eting.common.domain.story.Story;
import me.eting.server.core.service.reply.QueuedReplyConsumer;
import me.eting.server.core.service.story.StoryQueue;
import me.eting.server.core.service.story.StoryQueueConsumer;
import me.eting.server.core.service.story.postbox.Postbox;
import me.eting.server.core.service.story.postbox.PostboxRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by lifenjoy51 on 2015-03-04.
 */
@Controller
@RequestMapping("/api")
public class EtingRestTestController {

    @Autowired
    StoryQueueConsumer storyQueueConsumer;

    @Autowired
    QueuedReplyConsumer queuedReplyConsumer;


    @Autowired
    StoryQueue storyQueue;


    @Autowired
    PostboxRegistry postboxRegistry;


    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        storyQueueConsumer.handleStories();
        queuedReplyConsumer.consumes();
        StringBuffer sb = new StringBuffer();

        //storyqueue 상태 출력.
        sb.append("story queue : ");
        for (Story s : storyQueue.print()) {
            sb.append(s.getId() + " | \n");
        }
        sb.append("");

        //postbox 상태 출력.
        sb.append("postbox : \n");
        for (Map.Entry<Long, Postbox> e : postboxRegistry.print().entrySet()) {
            sb.append(String.valueOf(e.getKey()) + "_" + e.getValue().getClass().getSimpleName() + " | \n");
        }
        sb.append("");

        return sb.toString();
    }


}
