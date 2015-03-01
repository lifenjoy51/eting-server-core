package me.eting.server.core.service.story;


import me.eting.common.domain.EtingLang;
import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.EnvelopedStory;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;
import me.eting.server.core.repository.IncognitoRepository;
import me.eting.server.core.service.story.classifier.StoryClassifier;
import me.eting.server.core.service.story.classifier.StoryClassifierRegistry;
import me.eting.server.core.service.story.postbox.Postbox;
import me.eting.server.core.service.story.postbox.PostboxRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 대기열에 있는 이야기를 처리한다.
 * <div>1. 이야기와 작성자를 토대로 분류해서 봉투(envelope)에 담고</div>
 * <div>2. 적절한 우체통에 넣는다.</div>
 */
@Component
public abstract class StoryQueueConsumer
{
	/**
	 */
    @Autowired
	private StoryQueue storyQueue;
	
	/**
	 */
    @Autowired
    private StoryClassifierRegistry storyClassifierRegistry;
	
	/**
	 */
    @Autowired
    private PostboxRegistry postboxRegistry;

    /**      
     */
    @Autowired
    private DuplicationService duplicationService;

    /**
     */
    @Autowired
    private IncognitoRepository incognitoRepository;
	
	/**
	 */
	public StoryQueueConsumer(){
		super();
	}

	/**
	 * 주기적으로 StoryQueue에 쌓인 이야기들을 불러와 작업한다.
     * <div>언어에 맞는 분류기에서 이야기를 분류하고 편지봉투로 감싼다.</div>
     * <div>편지봉투에 넣은 이야기를 분배기에게 넘겨준다.</div>
	 */
    @Scheduled(fixedDelay=1000) //queue가 비어있다면 잠시 쉰다.
	public void handleStories() {
        // storyQueue에서 이야기를 계속 불러와서 처리한다.
        Story story = null;
        while((story = storyQueue.poll()) != null){
            //중복체크를 한다.
            if(duplicationService.isDuplicated(story)) continue;
            //이야기 작성자의 언어설정을 불러온다.
            EtingLang lang = story.getIncognito().getEtingKey().getEtingLang();
            // 언어에 맞는 분류기를 받아온다.
            StoryClassifier classifier = storyClassifierRegistry.getClassifier(lang);
            // 분류해서 봉투에 담는다.
            EnvelopedStory envelopedStory = classifier.envelopStory(story);
            // 봉투에 적힌 정보를 통해 적절한 우체통을 가져온다.
            Postbox postbox = postboxRegistry.getPostbox(envelopedStory.getEtingKey());
            // 봉투에 담긴 이야기를 우체통에 넣는다.
            postbox.put(envelopedStory);
            // 후속처리를 한다.
            updateDeviceType(envelopedStory);
        }
	}
	
	/**
     * 기기 유형을 업데이트한다.
     * Envelope를 생성한 이후 Envelope의 유형에 맞게 기기유형을 바꾼다.
	 */
	private void updateDeviceType(EnvelopedStory envelopedStory) {
		//사용자의 원래 eting type
        EtingType baseEtingType = envelopedStory.getStory().getIncognito().getEtingKey().getEtingType();
        //봉투에 저힌 eting type
        EtingType newEtingType = envelopedStory.getEtingKey().getEtingType();
        //서로 다를 경우 업데이트.
        if(!baseEtingType.equals(newEtingType)){
            Incognito incognito = envelopedStory.getStory().getIncognito();
            incognito.getEtingKey().setEtingType(newEtingType); //TODO 제대로 업데이트 되는지 확인 필요.
            incognitoRepository.save(incognito);
        }
	}
	
}

