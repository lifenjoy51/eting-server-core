package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 우체통에서 이야기 하나를 받아온다!
 */
@Component("PostboxServiceImpl")
public class PostboxServiceImpl implements PostboxService {
    /**
     */
    @Autowired
    public PostboxRegistry postboxRegistry;

    /**
     */
    public PostboxServiceImpl() {
        super();
    }

    /**
     * 기기에 맞는 우체통에서 이야기를 하나 가져온다.
     */
    @Override
    public Story pickStory(Incognito incognito) {
        // TODO 개발필요.
        return null;
    }

    /**
     * 우체통에서 이야기를 제거한다.
     */
    @Override
    public void removeStory() {
        // TODO implement me
    }

}

