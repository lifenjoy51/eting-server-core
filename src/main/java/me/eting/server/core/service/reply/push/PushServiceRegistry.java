package me.eting.server.core.service.reply.push;

import me.eting.common.domain.EtingLang;
import me.eting.common.domain.user.Incognito;
import me.eting.common.domain.user.UserOS;
import me.eting.server.core.service.reply.check.BasicReplyChecker;
import me.eting.server.core.service.reply.check.EnglishReplyChecker;
import me.eting.server.core.service.reply.check.KoreanReplyChecker;
import me.eting.server.core.service.reply.check.ReplyChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
public class PushServiceRegistry {
    /**
     */
    public Map<UserOS, PushService> pushServiceMap;

    @Autowired
    private ApplicationContext context;

    /**
     */
    public PushServiceRegistry() {
        pushServiceMap = new HashMap<UserOS, PushService>();
    }

    /**
     * 이 부분이 계속 변할것이다. 지원하는 언어가 늘어날수록 복잡해 질 것이다. 어떻게 관리하는게 효율적인가?
     */
    @PostConstruct
    public void init() {
        /// 여기서 이프문 거는거나.... 빼올떄 컨텍스트에서 빈을 가져오는거나... 그게 그거 아닌가?!
        pushServiceMap.put(UserOS.Android, context.getBean(GcmService.class));
        pushServiceMap.put(UserOS.Iphone, context.getBean(ApnsService.class));
    }


    /**
     * 언어설정에 맞는 검사기를 반환한다.
     */
    public PushService getChecker(Incognito incognito) {
        PushService pushService = pushServiceMap.get(incognito.getDevice().getOs());
        if (pushService != null) {
            return pushService;
        } else {
            //해당하는 언어의 분류기가 없으면 기본 분류기를 반환한다.
            return context.getBean(BasicPushService.class);
        }
    }
}
