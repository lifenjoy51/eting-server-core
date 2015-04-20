package me.eting.server.core.service.reply;


import me.eting.common.domain.reply.Reply;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
@Component
public class ReplyQueue {
    /**
     */
    private Queue<Reply> replies;

    /**
     */
    public ReplyQueue() {
        replies = new LinkedList<Reply>();
    }

    /**
     * queue에 있는 이야기를 하나씩 빼온다.
     */
    public Reply poll() {
        Reply reply = replies.poll();
        return reply;
    }

    /**
     * quque에 이야기를 넣는다.
     */
    public boolean offer(Reply reply) {
        boolean offer = replies.offer(reply);
        return offer;
    }

    /**
     * 출력용.
     * @return
     */
    public Queue<Reply> print(){
        return new LinkedList<Reply>(replies);
    }

}
