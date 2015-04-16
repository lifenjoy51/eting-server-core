package me.eting.server.model.service;

import me.eting.common.domain.user.Incognito;
import me.eting.server.model.domain.EtingModelSource;

import java.util.Comparator;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
public abstract class EtingModelService implements Comparable {

    protected int version;
    
    public EtingModelService(){
        version = 1;
    }

    /**
     * 소스를 갖고 처리한다. 결과값은 -1,0,1중 하나.* 
     * @param etingModelSource
     */
    public abstract void process(EtingModelSource etingModelSource);

    /**
     * 해당 유저의 결과값을 리턴한다.
     * @param incognito
     * @return
     */
    public abstract int value(Incognito incognito);

    @Override
    public int compareTo(Object o) {
        return this.getClass().getCanonicalName().compareTo(o.getClass().getCanonicalName());
    }
}
