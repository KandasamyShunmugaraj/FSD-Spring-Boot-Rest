package com.cognizant.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "Task") 
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private long taskId;
	
	@Column(name = "task")
	private String taskName;
		
	@Column(name = "start_date")
	@Type(type="date")
	private Date startDate;
	
	@Column(name = "end_date")
	@Type(type="date")
	private Date endDate;

	@Column(name="parent_id")
    private String parentTask;
	
	@Column(name = "priority")
	private String priority;
	
	@Column(name = "end_task")
	private boolean endTask;
	
	public boolean isEndTask() {
		return endTask;
	}

	public void setEndTask(boolean endTask) {
		this.endTask = endTask;
	}


	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}


}
