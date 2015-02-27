package me.eting.server.core.service.story;

import me.eting.common.domain.story.Story;
import me.eting.server.core.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 2/27/15.
 */
@Service
public class StoryService {

    @Autowired
    StoryQueue storyQueue;

    @Autowired
    StoryRepository storyRepository;

    /**
     * 작성한 이야기를 저장한다.
     * 저장한 이야기는 대기열에 넣는다.
     * @param story
     * @return
     */
    public Story save(Story story){
        //저장하고.
        Story savedStory = storyRepository.save(story);
        //큐에 담는다.
        storyQueue.offer(savedStory);
        //반환.
        return savedStory;
    }

}
