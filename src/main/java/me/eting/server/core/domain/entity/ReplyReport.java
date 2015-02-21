package me.eting.server.core.domain.entity;


import me.eting.server.core.domain.entity.Reply;

import javax.persistence.*;
import java.util.Date;

/**
 */

public class ReplyReport
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
    @JoinColumn(name="reply_id")
    private Reply reply;
	
	/**
	 */
	public ReplyReport(){
		super();
	}

}

