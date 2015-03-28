package me.eting.server.core.service.reply.check;

import me.eting.common.domain.reply.Reply;

/**
 * Created by lifenjoy51 on 2015-03-28.
 */
public class BasicReplyChecker  extends ReplyChecker{
    @Override
    public boolean isValid(Reply reply) {
        return false;
    }
}
