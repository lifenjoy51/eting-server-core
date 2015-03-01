package me.eting.server.core.repository;

import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Incognito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lifenjoy51 on 12/15/14.
 */
public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findByIncognito(Incognito incognito);
}
