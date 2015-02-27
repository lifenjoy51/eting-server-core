package me.eting.server.core.service.story.classifier;
import me.eting.common.domain.EtingLang;

import java.util.Map;


/**
 * 이야기 분류기를 등록하고 관리한다.
 * <div>Map&lt;EtingLang, StoryClassifier&gt;로 관리한다.</div>
 */

public class StoryClassifierRegistry
{
	/**
	 */
	public Map<EtingLang, StoryClassifier> storyClassifier;
	
	/**
	 */
	public StoryClassifierRegistry(){
        // TODO 초기화 필요함.
        // TODO 수동으로 설정.
	}

	/**
	 */
	public void getClassifier() {
		// TODO 파라미터는 뭘로하지?
	}
	
	/**
	 * 모든 분류기를 등록한다.
	 */
	public void registClassfiers() {
		// TODO 이게 꼭 필요한가?
	}
	
}

