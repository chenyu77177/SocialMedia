package com.esun.socialMedia.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "COMMENTS")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long comment_id;
	@Column(nullable = false)
	private String content;
	//@JsonBackReference
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "post_id", referencedColumnName = "post_id")
	private Post post;
	//@JsonBackReference
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	public void createdAt() {
		createdAt = new Date();
	}
	
	@PreUpdate
	public void setLastUpdate() {
		updatedAt = new Date();
	}

}
