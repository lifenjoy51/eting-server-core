package me.eting.server.core.repository;

import me.eting.common.domain.reply.Reply;
import me.eting.common.domain.story.Story;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
