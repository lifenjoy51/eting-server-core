package me.eting.common.domain.reply;


import javax.persistence.*;
import java.util.Date;

/**
 */

public class ReportedReply
{
    /**
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     */
    @Column
    private String reason;

    /**
     */
    @OneToOne
    @JoinColumn(name="exchanged_reply_id")
    private ExchangedReply exchangedReply;
	
	/**
	 */
	public ReportedReply(){
		super();
	}

}

