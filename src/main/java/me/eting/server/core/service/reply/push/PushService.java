package me.eting.server.core.service.reply.push;

import me.eting.common.domain.reply.Reply;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
public abstract class PushService{
    public abstract void push(Reply reply);
}
