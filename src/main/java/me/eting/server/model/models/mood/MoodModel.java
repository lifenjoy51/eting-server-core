package me.eting.server.model.models.mood;

import lombok.Data;
import me.eting.common.domain.user.Incognito;
import me.eting.server.model.domain.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Data
@Entity
@Table(name = "mood_model")
public class MoodModel extends BaseModel {
    
    private int positive;
    private int negative;

    public MoodModel(){}

    public MoodModel(Incognito incognito){
        this.setIncognitoId(incognito.getId());
    }
}
