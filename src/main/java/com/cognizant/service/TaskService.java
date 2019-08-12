package com.cognizant.service;

import java.util.List;

import com.cognizant.entity.Task;

public interface TaskService {
	public void addTask(Task task);
	public void updateTask(String taskId);
	public List<Task> listTasks();
	public Task getTaskById(Long taskId);
	public Task getTaskByName(String taskName);
 
}