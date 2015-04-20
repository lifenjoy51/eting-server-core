package me.eting.server.model.domain;

import lombok.Data;
import me.eting.common.domain.user.Incognito;

/**
 * Created by lifenjoy51 on 2015-04-16.
 */
@Data
public class EtingModelSource {
    private ModelType modelType;
    private Object[] objs;
    private Incognito incognito;
    
    public EtingModelSource(Incognito incognito, ModelType modelType, Object... objs){
        this.setIncognito(incognito);
        this.setModelType(modelType);
        this.setObjs(objs);
    }

}
