package me.eting.server.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Data
@MappedSuperclass
public abstract class BaseModel {

    @Id
    protected Integer incognitoId;

    @Column
    protected Integer value;

}
