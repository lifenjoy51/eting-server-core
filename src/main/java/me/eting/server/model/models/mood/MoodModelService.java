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
    
    @Override
    public void process(EtingModelSource etingModelSource) {
        // 각자 보관중인 점수 테이블이 필요함.
        
        // 점수를 산정한 다음.
        
        // 해당 점수에 따라 결과값을 정한다.
        
        // 이건 지금 DB에서 이벤트로 처리하고있으니...
        
        // 들고 올라오는겨~
    }

    @Override
    public int value(Incognito incognito) {
        return 0;
    }
    
}
