package me.eting.server.core.service.story.classifier;


import me.eting.server.core.domain.EtingLang;
import me.eting.server.core.domain.EtingType;
import me.eting.server.core.domain.entity.Envelope;
import me.eting.server.core.domain.entity.Story;

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

