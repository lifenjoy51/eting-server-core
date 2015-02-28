package me.eting.common.domain.story;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 */
@Data
@Entity
@Table(name = "reported_story")
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

