package me.eting.server.core.service.story.postbox;


import me.eting.server.core.domain.EtingPostboxKey;
import me.eting.server.core.domain.entity.Story;
import me.eting.server.core.service.story.postbox.Postbox;

/**
 * 이팅모델을 적용한 우체통이다.
 * <div>연령별, 분위기별 그룹이 적용된다.</div>
 */

public class EtingModelPostbox extends Postbox
{
	/**
	 */
	public EtingModelPostbox(EtingPostboxKey key){
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
