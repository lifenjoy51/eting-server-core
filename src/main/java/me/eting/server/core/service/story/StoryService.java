package me.eting.server.core.service.story;

import me.eting.common.domain.story.ExchangedStory;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;
import me.eting.server.core.repository.ExchangedStoryRepository;
import me.eting.server.core.repository.StoryRepository;
import me.eting.server.core.service.story.postbox.PostboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 2/27/15.
 */
@Service
public class StoryService {

    @Autowired
    StoryQueue storyQueue;

    @Autowired
    @Qualifier("PostboxServiceImpl")
    PostboxService postboxService;

    @Autowired
    StoryRepository storyRepository;
    
    @Autowired
    ExchangedStoryRepository exchangedStoryRepository;

    /**
     * 작성한 이야기를 저장한다.
     * 저장한 이야기는 대기열에 넣는다.
     * @param story
     * @return
     */
    public Story save(Story story){
        //저장하고.
        Story savedStory = storyRepository.save(story);
        //TODO flushing이 맞나??
        //storyRepository.flush();
        //큐에 담는다.
        storyQueue.offer(savedStory);
        //반환.
        return savedStory;
    }

    /**
     * 이야기를 받아온다. 
     * @param incognito
     * @return
     */
    public ExchangedStory exchange(Incognito incognito){
        //이야기를 받아온다.
        Story pickedStory = postboxService.pickStory(incognito);
        //교환된 이야기 생성.
        ExchangedStory exchangedStory = new ExchangedStory(pickedStory, incognito);
        //교환한 이야기 저장해야지
        exchangedStoryRepository.save(exchangedStory);
        //반환
        return exchangedStory;
    }

    /**
     * 교환한 이야기 정보를 받아온다.
     * @param exchangedStoryId
     * @return
     */
    public ExchangedStory getExchangedStory(long exchangedStoryId){
        ExchangedStory exchangedStory = exchangedStoryRepository.findOne(exchangedStoryId);
        return exchangedStory;
    }

    /**
     * 받은 이야기를 패스한다. 
     * @param exchangedStory
     */
    public void pass(ExchangedStory exchangedStory){
        //패스 상태 변경.
        exchangedStory.setPassed(true);
        //업데이트
        exchangedStoryRepository.save(exchangedStory);
    }

    /**
     * 받은 이야기를 신고한다.
     * @param exchangedStory
     */
    public void report(ExchangedStory exchangedStory){
        
    }

}
