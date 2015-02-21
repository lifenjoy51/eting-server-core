package me.eting.server.core.domain.entity;

import lombok.Data;
import me.eting.server.core.domain.EtingLang;
import me.eting.server.core.domain.EtingType;

import javax.persistence.*;
import java.util.Date;


/**
 */
@Data
@Entity
@Table(name = "device")
public class Device {
    /**
     */
    @Id
    private int id;

    /**
     */
    @Column(nullable = false)
    private char uuid;

    /**
     */

    private String pushKey;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;

    /**
     */
    @Column(nullable = false)
    private char os;

    /**
     */
    @Column
    private EtingType etingType;

    /**
     */
    @Column
    private EtingLang etingLang;

    /**
     */
    @Column
    private int etingPoint;

    /**
     */
    @Column
    private char gender;

    /**
     */
    @Column
    private int birthYear;

    /**
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    /**
     */
    public Device() {
    }

}

