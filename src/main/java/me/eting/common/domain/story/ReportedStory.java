package me.eting.common.domain.story;


import javax.persistence.*;
import java.util.Date;

/**
 */

public class ReportedStory
{
    /**
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     */
    @OneToOne
    @JoinColumn(name="exchanged_story_id")
    private ExchangedStory exchangedStory;

	/**
	 */
    @Column
    private String reason;
	
	/**
	 */
	public ReportedStory(){
		super();
	}

}

