package me.eting.server.model.models.age;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Repository
public interface AgeModelRepository extends JpaRepository<AgeModel, Long> {

}
