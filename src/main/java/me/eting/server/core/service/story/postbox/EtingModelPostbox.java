package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;

import java.util.LinkedList;

/**
 * 이팅모델을 적용한 우체통이다.
 * <div>연령별, 분위기별 그룹이 적용된다.</div>
 */

public class EtingModelPostbox extends Postbox {

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
    public Story pick(Incognito incognito) {
        for(Story s : queue){
            if(!s.getIncognito().equals(incognito)) return s;
        }
        return null;
    }

    @Override
    public void remove(Story story) {
        queue.remove(story);
    }

}

