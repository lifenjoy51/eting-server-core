package me.eting.server.core.service.reply.check;

import me.eting.common.domain.reply.Reply;
import me.eting.server.core.service.content.KoreanContentChecker;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lifenjoy51 on 2015-02-28.
 */
public class KoreanReplyChecker extends ReplyChecker {

    @Autowired
    KoreanContentChecker koreanContentChecker;

    @Override
    public boolean isValid(Reply reply) {
        //내용검사.
        if (!isNormalContent(reply)) return false;
        //비속어검사.
        if (hasTrash(reply)) return false;
        //길이검사
        if (!isEnoughReplyLength(reply)) return false;
        //검사통과하면 정상!
        return true;
    }

    /**
     * 작성한 내용이 정상적인 글인지 판별한다. 일반적으로 사용하는 단어를 사용했는지 검사한다.
     */
    private boolean isNormalContent(Reply reply) {
        return koreanContentChecker.isNormalContent(reply.getContent());
    }

    /**
     * 답글에서 체크할 비속어.
     */
    private boolean hasTrash(Reply reply) {
        String replyContent = reply.getContent();
        int storyContentLength = reply.getExchangedStory().getStory().getContent().length();
        return koreanContentChecker.replyHasTrashWord(replyContent, storyContentLength);
    }

    /**
     * 길이검사.
     * @param reply
     * @return
     */
    private boolean isEnoughReplyLength(Reply reply){
        return koreanContentChecker.isEnoughReplyLength(reply);
    }
}
