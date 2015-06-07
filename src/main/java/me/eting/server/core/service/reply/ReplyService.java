package me.eting.server.core.service.reply;

import me.eting.common.domain.reply.ExchangedReply;
import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.user.Incognito;
import me.eting.server.core.repository.ReplyRepository;
import me.eting.server.core.service.reply.postbox.ReplyPostbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
@Service
public class ReplyService {
    
    @Autowired
    ReplyRepository replyRepository;
    
    @Autowired
    ReplyQueue replyQueue;

    @Autowired
    ReplyPostbox replyPostbox;
    
    
    /**
     * 답장달기 
     * @param reply
     */
    public Reply save(Reply reply){
        // 저장!
        Reply savedReply = replyRepository.save(reply);
        // 대기열에 등록
        replyQueue.offer(savedReply);
        // 저장한놈 반환.
        return savedReply;
    }

    /**
     * 내가 작성한 이야기에 달린 답글들을 가져온다.
     * @param incognito
     * @return
     */
    public List<Reply> findReplyOnMyStory(Incognito incognito){
        List<Reply> replies = replyPostbox.find(incognito);
        return replies;
    }

    /**
     * 답장 수취확인.*
     * @param replies
     * @return
     */
    public boolean confirmReceipt(List<Reply> replies){
        return replyPostbox.confirmReceipt(replies);
    }

    /**
     * 받은 답장 신고하기.
     * @param exchangedReply
     */
    public void report(ExchangedReply exchangedReply){
        
    }
    
}
