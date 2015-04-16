package me.eting.server.model.domain;

import lombok.Data;
import me.eting.common.domain.user.Incognito;

/**
 * Created by lifenjoy51 on 2015-04-16.
 */
@Data
public class EtingModelSource {
    private ModelType modelType;
    private Class cls;
    private Object obj;
    private Incognito incognito;
    
    public EtingModelSource(Incognito incognito, ModelType modelType, Object obj){
        this.setIncognito(incognito);
        this.setModelType(modelType);
        this.setObj(obj);
        this.setCls(obj.getClass());
    }

}
