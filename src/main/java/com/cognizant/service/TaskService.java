package com.cognizant.service;

import java.util.List;

import com.cognizant.dto.TaskDto;

public interface TaskService {
	public void addTask(TaskDto task);
	public void updateTask(String taskId);
	public List<TaskDto> listTasks();
	public TaskDto getTaskById(Long taskId);
	public TaskDto getTaskByName(String taskName);
 
}