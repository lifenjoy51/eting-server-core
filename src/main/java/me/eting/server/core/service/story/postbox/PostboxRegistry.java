package me.eting.server.core.service.story.postbox;
import me.eting.common.domain.EtingKey;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 우체통을 등록하고 관리한다.
 * <div>Map&lt;EtingPostboxKey, Postbox&gt;로 관리한다.</div>
 * <div>여러가지 유형에 따라 알맞는 우체통을 만들어 넣고 관리한다.</div>
 */
@Component
public class PostboxRegistry
{
	/**
	 */
	private Map<EtingKey, Postbox> postbox;
	
	/**
	 */
	public PostboxRegistry(){
		//TODO 초기화한다. 모든 EtingLang에 대하여 각 EtingType에 맞는 우체통을 만들어서 등록한다.
	}

    /**
     * 적절한 우체통을 받아온다.
     * @param etingKey
     * @return
     */
    public Postbox getPostbox(EtingKey etingKey){
        return null;
    }

}

