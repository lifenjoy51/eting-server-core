package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;

import java.util.LinkedList;

/**
 * 단순히 메세지를 유통시키는 우체통이다.
 */

public class BasicPostbox extends Postbox {

    /**
     */
    public BasicPostbox(PostboxRegistry postboxRegistry) {
        super(postboxRegistry);
        queue = new LinkedList<Story>();
    }

    @Override
    public void put(Story story) {
        queue.add(story);
        postboxRegistry.registStory(story, this);
    }

    @Override
    public Story pick(Incognito incognito) {
        for(Story s : queue){
            if(!s.getIncognito().equals(incognito)) return s;
        }

        //TODO 아무것도 없을 때 그냥 빈 이야기를 반환?
        return null;
    }

    @Override
    public void remove(Story story) {
        queue.remove(story);
    }

}

