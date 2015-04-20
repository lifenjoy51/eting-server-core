package me.eting.server.model.models.mood;

import me.eting.server.model.models.age.AgeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Repository
public interface MoodModelRepository extends JpaRepository<MoodModel, Integer> {

}
