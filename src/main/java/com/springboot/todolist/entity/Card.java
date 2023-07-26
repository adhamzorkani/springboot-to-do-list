package com.springboot.todolist.entity;

import org.hibernate.validator.constraints.Range;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "cards")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "category")
	private String category;

	@Range(min = 1, max = 5) // 1 for to do. 2 for postponed. 3 for canceled. 4 for waiting for approval.
	@Column(name = "status") // 5 for completed.
	private int status;

	@Range(min = 1, max = 5) // priority 1-5 for 1 being the least and 5 the most
	@Column(name = "priority")
	private int priority;

	@Column(name = "due_date")
	private Date date;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Card() {

	}

	public Card(@NotEmpty String title, String description, String category,
			@NotEmpty @Range(min = 1, max = 5) int status, @NotEmpty @Range(min = 1, max = 5) int priority, Date date,
			User user) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.status = status;
		this.priority = priority;
		this.date = date;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
