package me.eting.common.domain.reply;

import lombok.Data;
import me.eting.common.domain.story.ExchangedStory;
import me.eting.common.util.EtingUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date ts = new Date();

	/**
	 */
    @Column(nullable = false)
	public String content;
	
	/**
	 */
	@ElementCollection
	@Enumerated(EnumType.STRING)
	public Collection<Emoticon> emoticon;

	/**
	 */
	@Column(nullable=false, columnDefinition="boolean default false")
	private boolean isDeleted;

	/**
	 */
	@Column(nullable=false, columnDefinition="boolean default false")
	private boolean isFavorite;
	
	/**
	 */
    @OneToOne
    @JoinColumn(name="exchanged_story_id")
	public ExchangedStory exchangedStory;
	
	/**
	 */
	public Reply(){
        emoticon = new ArrayList<Emoticon>();
	}
    
    public Reply(ExchangedStory exchangedStory){
        this();
        this.id = exchangedStory.getId();
        this.exchangedStory = exchangedStory;
    }

}

