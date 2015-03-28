package me.eting.server.core.service.story.classifier;


import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.Story;
import me.eting.server.core.service.content.KoreanContentChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 한국어를 처리하는 이야기 분류기이다.
 */
@Component
public class KoreanStoryClassifier extends StoryClassifier {
    
    @Autowired
    KoreanContentChecker koreanContentChecker;

    public KoreanStoryClassifier() {
    }

    @Override
    protected boolean isNormalContent(Story story) {
        //정상적인 내용인지 판별한다.
        return koreanContentChecker.isNormalContent(story.getContent());
    }

    @Override
    protected EtingType checkTypeWords(Story story) {
        // 등록한 금지어 검사하기.
        return EtingType.NORMAL;
    }

}

