package me.eting.common.domain.story;
import java.util.Date;
import lombok.Data;
import me.eting.common.domain.story.Story;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;
import me.eting.common.util.EtingUtil;

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
    @JoinColumn(name="story_id")
    private Story story;

    /**
     */
    @ManyToOne
    @JoinColumn(name="incognito_id")
    private Incognito incognito;
	
	/**
	 */
	public ExchangedStory(){
		super();
	}

    /**
     * 어떤 이야기를 누가 받아갔는가. 
     */
    public ExchangedStory(Story story, Incognito receiver) {
        this.id = EtingUtil.generatedId(receiver.getId());    //타임스탬프 + 아이디.
        this.story = story;
        this.incognito = incognito;
    }

}

