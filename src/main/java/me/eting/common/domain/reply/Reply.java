package me.eting.common.domain.reply;

import lombok.Data;
import me.eting.common.domain.story.ExchangedStory;
import me.eting.common.domain.user.Incognito;
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
	private String content;
	
	/**
	 */
	@ElementCollection
	@Enumerated(EnumType.STRING)
    private Collection<Emoticon> emoticon;

	/**
     * 답글의 상태는 여러가지..*
	 */
    @Enumerated(EnumType.STRING)
    private ReplyStatus replyStatus;

	/**
	 */
	@Column(nullable=false, columnDefinition="boolean default false")
	private boolean isFavorite;
	
	/**
	 */
    @OneToOne
    @JoinColumn(name="exchanged_story_id")
    private ExchangedStory exchangedStory;

    /**
     */
    @ManyToOne
    @JoinColumn(name="incognito_id")
    private Incognito incognito;
	
	/**
	 */
	public Reply(){
        
        emoticon = new ArrayList<Emoticon>();
        
	}
    
    public Reply(ExchangedStory exchangedStory){
        this();
        this.id = exchangedStory.getId();
        this.exchangedStory = exchangedStory;
        this.incognito = exchangedStory.getIncognito();
    }

}