package me.eting.server.core.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 */
@Data
@Entity
@Table(name = "reply")
public class Reply
{
    /**
     */
    @Id
    private long id;

	/**
	 */
    @Column(nullable = false)
	public String content;
	
	/**
	 */
    @Column
	public String emoticon;
	
	/**
	 */
    @OneToOne
    @JoinColumn(name="exchange_id")
	public StoryExchange exchange;
	
	/**
	 */
	public Reply(){
		super();
	}

}

