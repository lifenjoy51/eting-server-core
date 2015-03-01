package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.EtingKey;
import me.eting.common.domain.story.EnvelopedStory;
import me.eting.common.domain.story.Story;

/**
 * 우체통 추상 클래스이다.
 * <div>랜덤으로 이야기를 받아올 수도 있다.</div>
 * <div>언어별로 우체통이 나눠져야할까?</div>
 * <div>각각의 우체통을 따로 만드는 이유는 뭐지?</div>
 */

public abstract class Postbox
{
    protected EtingKey etingKey;
	/**
	 */
	public Postbox(EtingKey key){
		this.etingKey = key;
	}

	/**
     * 우체통에 이야기를 집어 넣는다.
	 */
	public abstract void put(EnvelopedStory envelopedStory);
	
	/**
     * 우체통에서 이야기를 하나 뽑는다.
	 */
	public  abstract Story pick();
	
}

