package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.EtingKey;
import me.eting.common.domain.story.EnvelopedStory;
import me.eting.common.domain.story.Story;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 단순히 메세지를 유통시키는 우체통이다.
 */

public class NormalPostbox extends Postbox
{
    
    private Queue<Story> queue;
    
	/**
	 */
	public NormalPostbox(PostboxRegistry postboxRegistry){
        super(postboxRegistry);
        queue = new LinkedList<Story>();
	}

    @Override
    public void put(Story story) {
        queue.add(story);
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

