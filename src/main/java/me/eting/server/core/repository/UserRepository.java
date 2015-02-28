package me.eting.server.core.repository;

import me.eting.common.domain.user.Incognito;
import me.eting.common.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/14/14.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
