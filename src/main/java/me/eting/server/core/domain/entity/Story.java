package me.eting.server.core.domain.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 */
@Data
@Entity
@Table(name = "story")
public class Story
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
    @Column(nullable = false)
    private String content;
	
	/**
	 */
    @ManyToOne
    @JoinColumn(name="device_id")
	private Device device;
	
	/**
	 */
	public Story(){
		super();
	}

}

