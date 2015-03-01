package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.story.Story;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 이팅모델을 적용한 우체통이다.
 * <div>연령별, 분위기별 그룹이 적용된다.</div>
 */

public class EtingModelPostbox extends Postbox {
    private Queue<Story> queue;

    /**
     */
    public EtingModelPostbox(PostboxRegistry postboxRegistry) {
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

