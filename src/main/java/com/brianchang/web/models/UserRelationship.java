package com.brianchang.web.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="user_relationships",
uniqueConstraints={@UniqueConstraint(columnNames = {"user1_id" , "user2_id"})})
public class UserRelationship {
	
	//Member Variables
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Byte status;
	
	@Column
	private Long action_user_id;
	
	////////////////////////////////////////////////////
	// RELATIONSHIPS                                  //
	////////////////////////////////////////////////////
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user1_id")
	private User user1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user2_id")
	private User user2;
	////////////////////////////////////////////////////
	// END RELATIONSHIPS                              //
	////////////////////////////////////////////////////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Long getAction_user_id() {
		return action_user_id;
	}

	public void setAction_user_id(Long action_user_id) {
		this.action_user_id = action_user_id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	
}
