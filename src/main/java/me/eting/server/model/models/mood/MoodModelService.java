package me.eting.server.model.models.mood;

import me.eting.common.domain.user.Incognito;
import me.eting.server.model.domain.EtingModelSource;
import me.eting.server.model.service.EtingModelService;
import org.springframework.stereotype.Component;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Component
public class MoodModelService extends EtingModelService {

    private final int SEQ = 1;
    
    @Override
    public void process(EtingModelSource etingModelSource) {
        
    }

    @Override
    public int value(Incognito incognito) {
        return 0;
    }
    
}
