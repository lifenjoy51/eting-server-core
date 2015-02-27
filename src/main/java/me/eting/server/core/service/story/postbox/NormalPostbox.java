package me.eting.server.core.service.story.postbox;


import me.eting.common.domain.EtingKey;
import me.eting.common.domain.story.Story;

/**
 * 단순히 메세지를 유통시키는 우체통이다.
 */

public class NormalPostbox extends Postbox
{
	/**
	 */
	public NormalPostbox(EtingKey key){
		super(key);
	}

    @Override
    public void put() {

    }

    @Override
    public Story pick() {
        return null;
    }

}

