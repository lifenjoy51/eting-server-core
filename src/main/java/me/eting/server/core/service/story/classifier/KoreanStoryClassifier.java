package me.eting.server.core.service.story.classifier;


import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.Story;
import me.eting.server.core.service.content.KoreanContentChecker;
import me.eting.server.model.domain.EtingModelSource;
import me.eting.server.model.domain.ModelType;
import me.eting.server.model.service.ModelQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 한국어를 처리하는 이야기 분류기이다.
 */
@Component
public class KoreanStoryClassifier extends StoryClassifier {
    
    @Autowired
    KoreanContentChecker koreanContentChecker;

    @Autowired
    ModelQueue modelQueue;

    public KoreanStoryClassifier() {
    }

    @Override
    protected boolean isNormalContent(Story story) {
        //정상적인 내용인지 판별한다.
        if(koreanContentChecker.isNormalContent(story.getContent())) {
            return true;
        }else{
            //TODO 모델에 점수 보내기.
            //모델 호출!
            EtingModelSource source = new EtingModelSource(story.getIncognito(), ModelType.AbnormalContentStory, story);
            modelQueue.offer(source);
            return false;
        }

    }

    @Override
    protected EtingType checkTypeWords(Story story) {
        // TODO 등록한 금지어 검사하기.
        // 비정상일 경우 점수처리 어떻게 할지 결정해야...
        return EtingType.NORMAL;
    }

}

