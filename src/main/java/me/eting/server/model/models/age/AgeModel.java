package me.eting.server.model.models.age;

import lombok.Data;
import me.eting.server.model.domain.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Data
@Entity
@Table(name = "age_model")
public class AgeModel extends BaseModel {

    public AgeModel() {
    }

    public AgeModel(int incognitoId, int value) {
        this.setIncognitoId(incognitoId);
        this.setValue(value);
    }
}
