package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.EtingKey;
import me.eting.common.domain.story.EnvelopedStory;
import me.eting.common.domain.story.Story;

/**
 * 유통이 금지된 우체통이다.<div>여기에 들어간 이야기는 자동으로 답장한다.</div>
 */

public class BanedPostbox extends Postbox
{
	/**
	 */
	public BanedPostbox(EtingKey key){
		super(key);
	}

    @Override
    public void put(EnvelopedStory envelopedStory) {

    }

    @Override
    public Story pick() {
        return null;
    }

}

