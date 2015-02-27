package me.eting.common.domain;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 */
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

}

