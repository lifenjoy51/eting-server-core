package me.eting.server.core.service.story.classifier;


import me.eting.common.domain.EtingLang;
import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.EnvelopedStory;
import me.eting.common.domain.story.Story;

/**
 * 이야기 분류기 추상 객체이다.
 * <div>이야기 분류기는 말 그대로 이야기를 분류하는 작업을 한다.</div>
 * <div>봉투(envelope)에는 언어(lang)과 유형(eType)이 붙는다.</div>
 */
public abstract class StoryClassifier
{
    protected EtingLang etingLang;

	/**
	 */
	public StoryClassifier(EtingLang etingLang){
        this.etingLang = etingLang;
	}

	/**
	 */
	public EnvelopedStory envelopStory(Story parameter) {
        //TODO 1. 먼저 기기 유형을 검사해서 만든다.
        //TODO 2. 정상적인 내용인지 검사한다.
        //TODO 3. 특정 단어(정규식)을 포함했는지 검사한다.
        return null;
    }
	
	/**
	 * 기기가 정상인지/신고누적인지/애매한놈인지/똥인지 구분한다.
	 */
	protected EtingType checkDevice() {
        //TODO 기기에 맞게 Envelope를 생성한다??
        //TODO 언어는 어디서 입력받아야 하는가?
        return null;
    }
	
	/**
	 * 작성한 내용이 정상적인 글인지 판별한다. 일반적으로 사용하는 단어를 사용했는지 검사한다.
	 */
	protected abstract EtingType checkNormalContent();
	
	/**
	 * 지정된 단어(정규식)을 포함하고 있는지 검사한다.
	 */
	protected abstract EtingType checkTypeWords();
	
}