package com.esun.socialMedia.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "POSTS")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long post_id;
	//@JsonBackReference
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	//@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	@EqualsAndHashCode.Exclude
	private Set<Comment> comments;
	@Column(nullable = false)
	private String content;
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
