package me.eting.server.core.domain.entity;


import me.eting.server.core.domain.entity.StoryExchange;

import javax.persistence.*;
import java.util.Date;

/**
 */

public class StoryPass
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
	public StoryPass(){
		super();
	}

}

