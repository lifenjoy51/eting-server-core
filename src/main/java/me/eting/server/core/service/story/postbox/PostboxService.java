package me.eting.server.core.service.story.postbox;

import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
public interface PostboxService {
    
    Story pickStory(Incognito incognito);

    void removeStory();
}
