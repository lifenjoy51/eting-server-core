package me.eting.server.core.service.reply;

import me.eting.common.domain.reply.ExchangedReply;
import me.eting.common.domain.reply.Reply;
import me.eting.server.core.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
@Service
public class ReplyService {
    
    @Autowired
    ReplyRepository replyRepository;
    
    @Autowired
    ReplyQueue replyQueue;

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
     * 받은 답장 신고하기.
     * @param exchangedReply
     */
    public void report(ExchangedReply exchangedReply){
        
    }
}
