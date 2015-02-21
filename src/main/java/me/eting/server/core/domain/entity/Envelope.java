package me.eting.server.core.domain.entity;

import lombok.Data;
import me.eting.server.core.domain.EtingLang;
import me.eting.server.core.domain.EtingType;
import me.eting.server.core.domain.entity.Story;

import javax.persistence.*;


/**
 */
@Data
@Entity
@Table(name = "envelope")
public class Envelope
{
    /**
     */
    @Id
    private long id;

	/**
	 */
    @Enumerated(EnumType.STRING)
    private EtingLang lang;
	
	/**
	 */
    @Enumerated(EnumType.STRING)
    private EtingType eType;
	
	/**
	 */
    @OneToOne
    @JoinColumn(name="story_id")
    private Story story;
	
	/**
	 */
	public Envelope(){
		super();
	}

}

