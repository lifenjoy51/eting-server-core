package me.eting.server.core.service.reply;


import me.eting.common.domain.reply.Reply;
import me.eting.server.core.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lifenjoy51 on 14. 12. 25.
 */
@Service
public class ReplyDuplicationService {

    @Autowired
    ReplyRepository repository;

    public boolean isDuplicated(Reply story) {

        // get last saved stories from db.?
        List<Reply> lastSavedStories = repository.findFirst10ByIncognitoOrderByIdDesc(story.getIncognito());

        //count
        int cnt = 0;

        //check
        for (Reply lastReply : lastSavedStories) {

            //compare content.
            final String lastContent = lastReply.getContent();
            final String newContent = story.getContent();

            //quartiles.
            int q1 = newContent.length() * 1 / 4;
            int q2 = newContent.length() * 2 / 4;
            //int q3 = newContent.length() * 3 / 4;

            //앞부분 비교
            String head = newContent.substring(0, q2);
            if (lastContent.contains(head)) cnt++;

            //중간부분 비교
            String center = newContent.substring(q1, q2);
            if (lastContent.contains(center)) cnt++;

            //뒷부분 비교
            String tail = newContent.substring(q2, newContent.length());
            if (lastContent.contains(tail)) cnt++;

        }

        //2번 이상 중복게시물을 올릴 경우!
        int max = 2 * 2;
        if (cnt > max) {
            return true;
        } else {
            return false;
        }

    }
}
