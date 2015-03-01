package me.eting.server.core.service.story.classifier;


import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.Story;
import org.springframework.stereotype.Component;

/**
 * 영어를 처리하는 이야기 분류기이다.
 */
@Component
public class EnglishStoryClassifier extends StoryClassifier {

    public EnglishStoryClassifier() {

    }

    @Override
    protected boolean isNormalContent(Story story) {
        return true;
    }

    @Override
    protected EtingType checkTypeWords(Story story) {
        return EtingType.NORMAL;
    }

}

