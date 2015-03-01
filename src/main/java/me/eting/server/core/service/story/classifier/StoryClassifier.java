package me.eting.server.core.service.story.classifier;


import me.eting.common.domain.EtingKey;
import me.eting.common.domain.EtingLang;
import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.EnvelopedStory;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;

/**
 * 이야기 분류기 추상 객체이다.
 * <div>이야기 분류기는 말 그대로 이야기를 분류하는 작업을 한다.</div>
 * <div>봉투(envelope)에는 언어(lang)과 유형(eType)이 붙는다.</div>
 */
public abstract class StoryClassifier {

    /**
     */
    public StoryClassifier() {
    }

    /**
     */
    public EnvelopedStory envelopStory(Story story) {
        //기본값은 작성자의 lang, type에 따른다.
        EtingLang lang = story.getIncognito().getEtingKey().getEtingLang();
        EtingType type = story.getIncognito().getEtingKey().getEtingType();

        //1. 먼저 기기 유형을 검사해서 정상인것만 처리한다.
        //정상이 아니면 해당 type이 유지된다. ex)신고,애매,똥.
        if (!skipContentCheck(story.getIncognito())) {
            //2. 정상적인 내용인지 검사한다.
            if (isNormalContent(story)) {
                //3. 특정 단어(정규식)을 포함했는지 검사한다.
                type = checkTypeWords(story);
            } else {
                type = EtingType.MEANINGLESS;
            }
        }

        //enveloped story를 생성한다.
        EtingKey key = new EtingKey(lang, type);
        EnvelopedStory envelopedStory = new EnvelopedStory(key, story);

        return envelopedStory;
    }

    /**
     * 기기가 정상인지/신고누적인지/애매한놈인지/똥인지 확인한다.
     */
    protected boolean skipContentCheck(Incognito incognito) {
        switch (incognito.getEtingKey().getEtingType()) {
            case BLOCKED:   //똥
                return true;
            case REPORTED:  //신고누적
                return true;
            case TRASH: //애매한놈
                return true;

            default:
                return false;
        }
    }

    /**
     * 작성한 내용이 정상적인 글인지 판별한다. 일반적으로 사용하는 단어를 사용했는지 검사한다.
     */
    protected abstract boolean isNormalContent(Story story);

    /**
     * 지정된 단어(정규식)을 포함하고 있는지 검사한다.
     */
    protected abstract EtingType checkTypeWords(Story story);

}