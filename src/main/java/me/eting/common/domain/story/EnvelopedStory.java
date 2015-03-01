package me.eting.common.domain.story;

import lombok.Data;
import me.eting.common.domain.EtingKey;
import me.eting.common.domain.EtingLang;
import me.eting.common.domain.EtingType;
import me.eting.common.domain.story.Story;

import javax.persistence.*;


/**
 */
@Data
@Entity
@Table(name = "enveloped_story")
public class EnvelopedStory
{
    /**
     */
    @Id
    @GeneratedValue
    private long id;

	/**
	 */
    @Embedded
    private EtingKey etingKey;

	/**
	 */
    @OneToOne
    @JoinColumn(name="story_id")
    private Story story;
	
	/**
	 */
	public EnvelopedStory(){
		super();
	}

    /**
     */
    public EnvelopedStory(EtingKey etingKey, Story story){
        this.etingKey = etingKey;
        this.story = story;
    }

}

