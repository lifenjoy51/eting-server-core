package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.user.Device;
import me.eting.common.domain.story.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 우체통에서 이야기 하나를 받아온다!
 */
@Component
public class PostboxHandler
{
	/**
	 */
    @Autowired
	public PostboxRegistry postboxRegistry;
	
	/**
	 */
	public PostboxHandler(){
		super();
	}

	/**
	 * 기기에 맞는 우체통에서 이야기를 하나 가져온다.
	 */
	public Story pickStory(Device device) {
		// TODO implement me
		return null;	
	}
	
	/**
	 * 우체통에서 이야기를 제거한다.
	 */
	public void removeStory() {
		// TODO implement me	
	}
	
}

