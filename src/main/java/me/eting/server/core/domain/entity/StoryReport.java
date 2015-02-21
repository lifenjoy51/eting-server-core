package me.eting.server.core.domain.entity;


import me.eting.server.core.domain.entity.StoryExchange;

import javax.persistence.*;
import java.util.Date;

/**
 */

public class StoryReport
{
    /**
     */
    @Id
    private long id;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;

    /**
     */
    @OneToOne
    @JoinColumn(name="exchange_id")
    private StoryExchange exchange;
	/**
	 */
    @Column
    private String reason;
	
	/**
	 */
	public StoryReport(){
		super();
	}

}

