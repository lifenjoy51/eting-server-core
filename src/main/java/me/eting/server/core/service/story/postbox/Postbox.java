package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;

import java.util.Queue;

/**
 * 우체통 추상 클래스이다.
 * <div>랜덤으로 이야기를 받아올 수도 있다.</div>
 * <div>언어별로 우체통이 나눠져야할까?</div>
 * <div>각각의 우체통을 따로 만드는 이유는 뭐지?</div>
 */

public abstract class Postbox {

    Queue<Story> queue;

    PostboxRegistry postboxRegistry;

    /**
     */
    public Postbox(PostboxRegistry postboxRegistry) {
        this.postboxRegistry = postboxRegistry;
    }

    /**
     * 우체통에 이야기를 집어 넣는다.
     */
    public abstract void put(Story story);

    /**
     * 우체통에서 이야기를 하나 뽑는다.
     * @param incognito
     */
    public abstract Story pick(Incognito incognito);

    /**
     * 우체통에서 이야기를 삭제한다.*
     */
    public abstract void remove(Story story);

}

