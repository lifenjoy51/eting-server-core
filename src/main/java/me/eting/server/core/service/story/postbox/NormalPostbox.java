package me.eting.server.core.service.story.postbox;


import me.eting.server.core.domain.EtingPostboxKey;
import me.eting.server.core.domain.entity.Story;
import me.eting.server.core.service.story.postbox.Postbox;

/**
 * 단순히 메세지를 유통시키는 우체통이다.
 */

public class NormalPostbox extends Postbox
{
	/**
	 */
	public NormalPostbox(EtingPostboxKey key){
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

