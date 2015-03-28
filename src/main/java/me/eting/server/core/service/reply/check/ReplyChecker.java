package me.eting.server.core.service.reply.check;

/**
 * Created by lifenjoy51 on 2015-02-28.
 *
 */
public abstract class ReplyChecker {

    /**
     * 답장의 내용을 검사한다. 단순히 내용만..
     * 점수는 아니고, 이게 정상인지 아닌지에 해당하는 부분.
     * @return
     */
    public abstract boolean isValid();
}