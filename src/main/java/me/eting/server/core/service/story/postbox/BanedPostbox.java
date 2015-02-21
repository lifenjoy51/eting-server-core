package me.eting.server.core.service.story.postbox;


import me.eting.server.core.domain.EtingPostboxKey;
import me.eting.server.core.domain.entity.Story;
import me.eting.server.core.service.story.postbox.Postbox;

/**
 * 유통이 금지된 우체통이다.<div>여기에 들어간 이야기는 자동으로 답장한다.</div>
 */

public class BanedPostbox extends Postbox
{
	/**
	 */
	public BanedPostbox(EtingPostboxKey key){
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
