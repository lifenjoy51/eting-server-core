package me.eting.server.core.service.story.classifier;


import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.Story;
import org.springframework.stereotype.Component;

/**
 * 기본 분류기.영혼없이 분류한다.
 */
@Component
public class BasicStoryClassifier extends StoryClassifier {

    public BasicStoryClassifier() {
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

