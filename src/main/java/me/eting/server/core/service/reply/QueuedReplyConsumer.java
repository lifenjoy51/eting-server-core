package me.eting.server.core.service.reply;

import me.eting.common.domain.EtingLang;
import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.reply.ReplyStatus;
import me.eting.server.core.repository.ReplyRepository;
import me.eting.server.core.service.reply.check.ReplyChecker;
import me.eting.server.core.service.reply.check.ReplyCheckerRegistry;
import me.eting.server.core.service.reply.push.PushService;
import me.eting.server.core.service.reply.push.PushServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
public class QueuedReplyConsumer {

    @Autowired
    private ReplyQueue replyQueue;

    @Autowired
    private ReplyCheckerRegistry replyCheckerRegistry;
    
    @Autowired
    ReplyDuplicationService replyDuplicationService;
    
    @Autowired
    ReplyRepository replyRepository;
    
    @Autowired
    PushServiceRegistry pushServiceRegistry;

    /**
     * 주기적으로 ReplyQueue에 쌓인 이야기들을 불러와 작업한다.
     * <div>언어에 맞는 분류기에서 이야기를 분류하고 편지봉투로 감싼다.</div>
     * <div>편지봉투에 넣은 이야기를 분배기에게 넘겨준다.</div>
     */
    @Scheduled(fixedDelay = 1000) //queue가 비어있다면 잠시 쉰다.
    public void consumes() {
        Reply reply = null;
        while ((reply = replyQueue.poll()) != null) {
            // 작업도중 에러가 나면 어떻게 롤백할것인가?
            
            //답장이 중복이면 패스..
            if(replyDuplicationService.isDuplicated(reply)) continue;
            //이야기 작성자의 언어설정을 불러온다.
            EtingLang lang = reply.getExchangedStory().getIncognito().getEtingKey().getEtingLang();
            // 언어에 맞는 검사기를 가져온다.
            ReplyChecker replyChecker = replyCheckerRegistry.getChecker(lang);
            // 정상적인 답글인지 검사한다.
            if(replyChecker.isValid()){
                //작성자에게 푸쉬메세지 보내기.
                PushService pushService = pushServiceRegistry.getChecker(reply.getIncognito());
                pushService.push(reply);;
            }else{
                reply.setReplyStatus(ReplyStatus.Abnormal);
                replyRepository.save(reply);
            }
            
        }

    }
}
