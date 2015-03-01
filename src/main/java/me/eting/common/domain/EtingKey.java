package me.eting.common.domain;


import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 */
@Data
@Embeddable
public class EtingKey
{
	/**
     * 언어
	 */
	@Enumerated(EnumType.STRING)
	private EtingLang etingLang;
	
	/**
     * 유형
	 */
	@Enumerated(EnumType.STRING)
    private EtingType etingType;
	
	/**
	 */
	public EtingKey(){
		super();
	}

    /**
     */
    public EtingKey(EtingLang etingLang, EtingType etingType){
        super();
    }

}

