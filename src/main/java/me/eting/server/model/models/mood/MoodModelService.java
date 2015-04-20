package me.eting.server.model.models.mood;

import me.eting.common.domain.user.Incognito;
import me.eting.server.model.domain.EtingModelSource;
import me.eting.server.model.domain.ModelType;
import me.eting.server.model.service.EtingModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Component
public class MoodModelService extends EtingModelService {

    @Autowired
    MoodModelRepository moodModelRepository;
    
    @Override
    public void process(EtingModelSource etingModelSource) {
        // 각자 보관중인 점수 테이블이 필요함.
        MoodModel model = moodModelRepository.findOne(etingModelSource.getIncognito().getId());
        if(model==null){
            model = new MoodModel(etingModelSource.getIncognito());
        }
        int positive = model.getPositive();
        int negative = model.getNegative();

        // 점수를 산정한 다음.
        // 여기가 많이 복잡해지겠네....
        if(etingModelSource.getModelType().equals(ModelType.AbnormalContentStory)){
            // 비정상 메세지일 때 점수처리.
            negative += 1;
        }
        
        // 해당 점수에 따라 결과값을 정한다.
        model.setPositive(positive);
        model.setNegative(negative);
        
        // 이건 지금 DB에서 이벤트로 처리하고있으니...
        moodModelRepository.save(model);
        
        // 들고 올라오는겨~
    }

    @Override
    public int value(Incognito incognito) {
        //어디까지가 이상한 놈이고 어디부터가 좋은놈인지 구분하기.
        MoodModel model = moodModelRepository.findOne(incognito.getId());
        if(model==null){
            return 0;
        }
        int positive = model.getPositive();
        int negative = model.getNegative();
        int score = positive - negative;

        if(score >= 15 ){
            return 1;
        }else if (score < -10) {
            return -1;
        }else{
            return 0;
        }

    }
    
}
