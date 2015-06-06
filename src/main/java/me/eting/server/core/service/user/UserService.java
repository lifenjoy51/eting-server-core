package me.eting.server.core.service.user;

import me.eting.common.domain.EtingKey;
import me.eting.common.domain.EtingLang;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;
import me.eting.server.core.repository.IncognitoRepository;
import me.eting.server.model.domain.EtingModelSource;
import me.eting.server.model.domain.ModelType;
import me.eting.server.model.service.ModelQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 2/27/15.
 */
@Service
public class UserService {

    @Autowired
    IncognitoRepository incognitoRepository;
    
    @Autowired
    ModelQueue modelQueue;

    /**
     * 새로운 익명유저를 등록한다.
     *
     * @param device
     * @return
     */
    public Incognito register(Device device, EtingLang etingLang){
        //새로운 익명유저 생성.
        Incognito newIncognito = new Incognito(device, etingLang);
        //새로운 익명유저 저장.
        Incognito savedNewIncognito = incognitoRepository.save(newIncognito);
        //저장된 익명유저 반환.
        return savedNewIncognito;
    }
    
    public Incognito updateInfo(Incognito incognito){
        //변경된 정보 저장.
        incognitoRepository.save(incognito);
        //모델 호출!        
        EtingModelSource source = new EtingModelSource(incognito, ModelType.Incognito, incognito);
        modelQueue.offer(source);
        //반환.
        return incognito;
    }
    
    public Incognito get(int id){
        return incognitoRepository.findOne(id);
    }

}
