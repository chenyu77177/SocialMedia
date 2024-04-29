package com.esun.socialMedia.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(nullable = false, unique = true, columnDefinition = "UUID")
	private UUID user_id;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false, unique = true)
	private String phone;
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	@Column(nullable = false)
	private String biography;
	@Column(nullable = false)
	@JsonIgnore
	private String salt;
	private Date createdAt;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@EqualsAndHashCode.Exclude
	private Set<Post> posts;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Comment> comments;
	
	@PrePersist
	public void setLastCreate() {
		createdAt = new Date();
	}
}
