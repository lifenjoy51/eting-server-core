package me.eting.common.domain.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lifenjoy51 on 2/27/15.
 */
@Data
@Embeddable
public class EtingModelValue {

    @Column
    @ElementCollection
    Collection<Integer> values = new ArrayList<Integer>();
}
