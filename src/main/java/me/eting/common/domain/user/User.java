package me.eting.common.domain.user;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 */
@Data
@Entity
@Table(name = "user")
public class User
{
	/**
	 */
    @Id
    private String userId;
	
	/**
	 */
    @Column
    private String userPw;
	
	/**
	 */
    @Temporal(TemporalType.TIMESTAMP)
	private Date ts = new Date();
	
	/**
	 */
	public User(){
		super();
	}

}

