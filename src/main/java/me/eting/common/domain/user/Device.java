package me.eting.common.domain.user;

import lombok.Data;
import me.eting.common.domain.reply.ReplyStatus;

import javax.persistence.*;
import java.util.Date;


/**
 */
@Data
@Embeddable
public class Device {

    /**
     * 기기에서 생성하는 36자리 고유번호.
     */
    @Column(nullable = false, unique = true)
    private String uuid;

    /**
     * push할 키가 없을 수도 있다.
     * 안드로이드의 경우 play서비스 버젼이 낮아서 생성을 못한다.
     * 그러면 푸쉬없이 폴링을 통해 새로운 소식을 가져가는 로직을 사용한다.
     */
    @Column
    private String pushKey;

    /**
     * 현재시간의 타임스탬프.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts = new Date();

    /**
     * 안드로이드 A
     * 아이폰 I
     * ...
     */
    @Enumerated(EnumType.STRING)
    private UserOS os;

    /**
     */
    public Device() {
    }

}

