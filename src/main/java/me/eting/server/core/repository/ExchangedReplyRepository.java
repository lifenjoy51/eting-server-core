package me.eting.server.core.repository;

import me.eting.common.domain.reply.ExchangedReply;
import me.eting.common.domain.story.Story;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface ExchangedReplyRepository extends JpaRepository<ExchangedReply, Long> {

}
