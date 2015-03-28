package me.eting.server.core.service.reply.check;

import me.eting.common.domain.EtingLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
public class ReplyCheckerRegistry {
    /**
     */
    public Map<EtingLang, ReplyChecker> replyCheckerMap;

    @Autowired
    private ApplicationContext context;

    /**
     */
    public ReplyCheckerRegistry() {
        replyCheckerMap = new HashMap<EtingLang, ReplyChecker>();
    }

    /**
     * 이 부분이 계속 변할것이다. 지원하는 언어가 늘어날수록 복잡해 질 것이다. 어떻게 관리하는게 효율적인가?
     */
    @PostConstruct
    public void init() {
        replyCheckerMap.put(EtingLang.basic, context.getBean(BasicReplyChecker.class));
        replyCheckerMap.put(EtingLang.koKR, context.getBean(KoreanReplyChecker.class));
        replyCheckerMap.put(EtingLang.enUS, context.getBean(EnglishReplyChecker.class));
    }


    /**
     * 언어설정에 맞는 검사기를 반환한다.
     */
    public ReplyChecker getChecker(EtingLang etingLang) {
        ReplyChecker replyChecker = replyCheckerMap.get(etingLang);
        if (replyChecker != null) {
            return replyChecker;
        } else {
            //해당하는 언어의 분류기가 없으면 기본 분류기를 반환한다.
            return replyCheckerMap.get(EtingLang.basic);
        }
    }
}
