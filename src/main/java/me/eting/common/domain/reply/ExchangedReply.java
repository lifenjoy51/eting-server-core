package me.eting.common.domain.reply;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 */
@Data
@Entity
@Table(name = "exchanged_reply")
public class ExchangedReply
{
    /**
     */
    @Id
    private long id;


    /**
     */
    @Column(nullable=false, columnDefinition="boolean default false")
    private boolean isLiked;

    /**
     */
    @Column(nullable=false, columnDefinition="boolean default false")
    private boolean isDeleted;

    /**
     */
    @OneToOne
    @JoinColumn(name="reply_id")
    private Reply reply;
	
	/**
	 */
	public ExchangedReply(){
		super();
	}

}

