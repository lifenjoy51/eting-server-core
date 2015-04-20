package me.eting.server.model.models.age;

import me.eting.common.domain.user.Incognito;
import me.eting.server.model.domain.EtingModelSource;
import me.eting.server.model.service.EtingModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by lifenjoy51 on 2015-04-17.
 */
@Service
public class AgeModelService extends EtingModelService {
    
    
    @Autowired
    AgeModelRepository ageModelRepository;

    
    @Override
    public void process(EtingModelSource etingModelSource) {
        //요놈은 유저정보를 갖고있다..
        //패키지 구분 없이 그냥 다 갖다 쓰면 되나?
        //뭐가 문제지? 중요한거....
        //접근성 제어가 되지 않으면 어떤 문제가 생길까?
        
        //여튼. 나이를 갖고 구분을 해보자.
        int value = 0;
        try{
            //모델소스에 객체들을 그냥 저장했는데...문제생길지도..
            Incognito incognito = (Incognito) etingModelSource.getObjs()[0];
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int age = year - incognito.getBirthYear();
            if(age > 10 && age < 40){
                if(age < 20){
                    value = -1;
                }else{
                    value = 1;
                }
            }

            //결과는 각자 저장하는걸로.        
            ageModelRepository.save(new AgeModel(incognito.getId(), value));
        }catch (ClassCastException cce){
            //TODO 에러처리 필요함.
        }
    }

    @Override
    public int value(Incognito incognito) {
        AgeModel model = ageModelRepository.findOne(incognito.getId());
        if(model == null) return 0;
        return model.getValue();
    }
    
}
