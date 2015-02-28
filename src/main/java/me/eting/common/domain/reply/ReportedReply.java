package me.eting.common.domain.reply;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 */
@Data
@Entity
@Table(name = "reported_reply")
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

