package me.eting.common.domain.user;

import lombok.Data;
import me.eting.common.domain.EtingKey;
import me.eting.common.domain.EtingLang;
import me.eting.common.domain.EtingType;

import javax.persistence.*;
import java.util.Date;


/**
 */
@Data
@Entity
@Table(name = "incognito")
public class Incognito {
    /**
     * 자동생성.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     *
     */
    @Column
    private int etingPoint;

    /**
     * 남 M
     * 여 F
     */
    @Column
    private String gender = "X";

    /**
     * ex) 1988
     */
    @Column
    private int birthYear = 0;

    /**
     */
    @Embedded
    private Device device;

    /**
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    /**
     */
    @Embedded
    private EtingKey etingKey;

    /**@Embedded
    private EtingModelValue etingModelValue;
     */


    /**
     */
    public Incognito() {
    }

    /**
     * 언어정보가 없을 때. 
     * @param device
     */
    public Incognito(Device device) {
        this.device = device;
        this.etingKey = new EtingKey(EtingLang.basic, EtingType.NORMAL);
    }

    /**
     * 언어정보를 갖고 등록할 때.
     * @param device
     * @param etingLang
     */
    public Incognito(Device device, EtingLang etingLang) {
        this.device = device;
        this.etingKey = new EtingKey(etingLang, EtingType.NORMAL);
    }

}

