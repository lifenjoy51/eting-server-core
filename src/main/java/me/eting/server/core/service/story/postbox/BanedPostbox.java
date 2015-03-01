package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.story.Story;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 유통이 금지된 우체통이다.<div>여기에 들어간 이야기는 자동으로 답장한다.</div>
 */

public class BanedPostbox extends Postbox {
    private Queue<Story> queue;

    /**
     */
    public BanedPostbox(PostboxRegistry postboxRegistry) {
        super(postboxRegistry);
        queue = new LinkedList<Story>();
    }

    @Override
    public void put(Story story) {
        queue.add(story);
        postboxRegistry.registStory(story, this);
    }

    @Override
    public Story pick() {
        return queue.peek();
    }

    @Override
    public void remove(Story story) {
        queue.remove(story);
    }

}

