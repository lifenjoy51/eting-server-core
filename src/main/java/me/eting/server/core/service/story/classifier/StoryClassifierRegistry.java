package me.eting.server.core.service.story.classifier;

import me.eting.common.domain.EtingLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * 이야기 분류기를 등록하고 관리한다.
 * <div>Map&lt;EtingLang, StoryClassifier&gt;로 관리한다.</div>
 */
@Component
public class StoryClassifierRegistry {
    /**
     */
    public Map<EtingLang, StoryClassifier> storyClassifier;

    @Autowired
    private ApplicationContext context;

    /**
     */
    public StoryClassifierRegistry() {
        storyClassifier = new HashMap<EtingLang, StoryClassifier>();
    }

    /**
     * 이 부분이 계속 변할것이다. 지원하는 언어가 늘어날수록 복잡해 질 것이다. 어떻게 관리하는게 효율적인가?
     */
    @PostConstruct
    public void init() {
        storyClassifier.put(EtingLang.basic, context.getBean(BasicStoryClassifier.class));
        storyClassifier.put(EtingLang.koKR, context.getBean(KoreanStoryClassifier.class));
        storyClassifier.put(EtingLang.enUS, context.getBean(EnglishStoryClassifier.class));
    }


    /**
     * 언어설정에 맞는 분류기를 반환한다.
     */
    public StoryClassifier getClassifier(EtingLang etingLang) {
        StoryClassifier classifier = storyClassifier.get(etingLang);
        if (classifier != null) {
            return classifier;
        } else {
            //해당하는 언어의 분류기가 없으면 기본 분류기를 반환한다.
            return storyClassifier.get(EtingLang.basic);
        }
    }
    
}

