package me.eting.common.domain.story;
import lombok.Data;
import me.eting.common.domain.user.Device;
import me.eting.common.domain.user.Incognito;

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
	@Column(nullable=false, columnDefinition="boolean default false")
	private boolean isDeleted;

	/**
	 */
	@Column(nullable=false, columnDefinition="boolean default false")
	private boolean isFavorite;
	
	/**
	 */
    @ManyToOne
    @JoinColumn(name="incognito_id")
	private Incognito incognito;
	
	/**
	 */
	public Story(){
		super();
	}

}

