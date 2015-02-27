package me.eting.server.core.repository;

import me.eting.common.domain.user.Incognito;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lifenjoy51 on 12/14/14.
 */
public interface IncognitoRepository extends JpaRepository<Incognito, Integer> {

}
