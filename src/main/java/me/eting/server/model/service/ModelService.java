package me.eting.server.model.service;

import me.eting.common.domain.user.EtingModelValue;
import me.eting.common.domain.user.Incognito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Vector;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Service
public class ModelService {
    
    @Autowired
    EtingModelRegistry etingModelRegistry;
    
    public EtingModelValue getValue(Incognito incognito){

        Vector<Integer> values = new Vector<Integer>();
        
        for(EtingModelService service : etingModelRegistry.getModels()){
            int value = service.value(incognito); 
            values.add(value);
        }
        
        return new EtingModelValue(values);
        
    }
    
}
