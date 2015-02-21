package me.eting.server.core.domain.entity;
import java.util.Date;
import lombok.Data;

import javax.persistence.*;


/**
 */
@Data
@Entity
@Table(name = "story_exchange")
public class StoryExchange
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
    @ManyToOne
    @JoinColumn(name="device_id")
    private Device device;
	
	/**
	 */
    @ManyToOne
    @JoinColumn(name="story_id")
    private Story story;
	
	/**
	 */
	public StoryExchange(){
		super();
	}

}

