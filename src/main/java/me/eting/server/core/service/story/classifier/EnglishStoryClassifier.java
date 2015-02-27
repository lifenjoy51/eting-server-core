package me.eting.server.core.service.story.classifier;


import me.eting.common.domain.EtingLang;
import me.eting.common.domain.EtingType;

/**
 * 영어를 처리하는 이야기 분류기이다.
 */

public class EnglishStoryClassifier extends StoryClassifier
{
	/**
	 */
	public EnglishStoryClassifier(EtingLang etingLang){
		super(etingLang);
	}

    @Override
    protected EtingType checkNormalContent() {
        return null;
    }

    @Override
    protected EtingType checkTypeWords() {
        return null;
    }

}

