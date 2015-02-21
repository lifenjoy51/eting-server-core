package me.eting.server.core.service.story;


import me.eting.server.core.domain.entity.Envelope;
import me.eting.server.core.service.story.classifier.StoryClassifierRegistry;
import me.eting.server.core.service.story.postbox.PostboxRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 대기열에 있는 이야기를 처리한다.
 * <div>1. 이야기와 작성자를 토대로 분류해서 봉투(envelope)에 담고</div>
 * <div>2. 적절한 우체통에 넣는다.</div>
 */
@Component
public abstract class StoryQueueHandler
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
	public StoryQueueHandler(){
		super();
	}

	/**
	 * 주기적으로 StoryQueue에 쌓인 이야기들을 불러와 작업한다.
     * <div>언어에 맞는 분류기에서 이야기를 분류하고 편지봉투로 감싼다.</div>
     * <div>편지봉투에 넣은 이야기를 분배기에게 넘겨준다.</div>
	 */
	public void handleStories() {
        // TODO storyQueue에서 이야기를 계속 불러와서 처리한다.
		// TODO storyClassifierRegistry에서 언어에 맞는 StoryClassifier를 가져와서 작업한다.
        // TODO StoryClassifier의 envelopStory를 통해 Envelope를 생성한다.
        // TODO putIntoPostbox를 호출해 우체통에 Envelope를 넣는다.

	}
	
	/**
	 * 봉투(envelope)에 있는 정보를 통해 알맞은 우체통을 찾아 넣는다.
     * <div>중복처리를 여기서 하는게 맞는가?</div>
	 */
	public void putIntoPostbox(Envelope parameter) {
		// TODO implement me	
	}
	
	/**
     * 기기 유형을 업데이트한다.
     * Envelope를 생성한 이후 Envelope의 유형에 맞게 기기유형을 바꾼다.
	 */
	public void updateDeviceType(Envelope parameter) {
		// TODO implement me	
	}
	
}

