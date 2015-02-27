package me.eting.server.core.service.user;

import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;
import me.eting.server.core.repository.IncognitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifenjoy51 on 2/27/15.
 */
@Service
public class UserService {

    @Autowired
    IncognitoRepository incognitoRepository;

    /**
     * 새로운 익명유저를 등록한다.
     *
     * @param device
     * @return
     */
    public Incognito register(Device device){
        //새로운 익명유저 생성.
        Incognito newIncognito = new Incognito(device);
        //새로운 익명유저 저장.
        Incognito savedNewIncognito = incognitoRepository.save(newIncognito);
        //저장된 익명유저 반환.
        return savedNewIncognito;
    }

}
