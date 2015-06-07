package me.eting.server.core.service.reply.postbox;

import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.user.Incognito;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lifenjoy51 on 2015-06-07.
 */
@Component
public class ReplyPostbox {
    /**
     */
    private Map<Incognito, List<Reply>> replyByIncognito = new HashMap<Incognito, List<Reply>>();

    /**
     * 답장의 원 작성글을 작성한 사람에 맞추어 작업..*
     *
     * @param reply
     */
    public void add(Reply reply) {
        //답글의 원래 이야기 작성자.
        Incognito incognito = reply.getExchangedStory().getStory().getIncognito();
        if (replyByIncognito.get(incognito) == null) {
            replyByIncognito.put(incognito, new ArrayList());
        }
        replyByIncognito.get(incognito).add(reply);
    }

    /**
     * 해당 incognito의 답장을 가져온다.*
     *
     * @param incognito
     * @return
     */
    public List<Reply> find(Incognito incognito) {
        List<Reply> replies = replyByIncognito.get(incognito);
        return replies == null ? new ArrayList<Reply>() : replies;
    }

    /**
     * 수취확인.*
     *
     * @param replies
     * @return
     */
    public boolean confirmReceipt(List<Reply> replies) {
        try {
            for (int i = 0; i < replies.size(); i++) {
                Reply r = replies.get(i);
                //답글의 원래 이야기 작성자.
                Incognito incognito = r.getExchangedStory().getStory().getIncognito();
                replyByIncognito.get(incognito).remove(r);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
