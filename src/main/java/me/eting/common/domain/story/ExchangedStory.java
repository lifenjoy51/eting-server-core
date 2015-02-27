package me.eting.common.domain.story;
import java.util.Date;
import lombok.Data;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;

import javax.persistence.*;


/**
 */
@Data
@Entity
@Table(name = "exchanged_story")
public class ExchangedStory
{
	/**
	 */
	@Id
	private long id;

	/**
	 */
	@Column(nullable=false, columnDefinition="boolean default false")
	private boolean isPassed;
	
	/**
	 */
    @ManyToOne
    @JoinColumn(name="incognito_id")
    private Incognito incognito;
	
	/**
	 */
    @ManyToOne
    @JoinColumn(name="story_id")
    private Story story;
	
	/**
	 */
	public ExchangedStory(){
		super();
	}

}

