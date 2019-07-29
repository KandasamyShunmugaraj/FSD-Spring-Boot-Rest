package com.cognizant.dao;

import java.util.List;

import com.cognizant.entity.Task;

public interface TaskDao {
public void addTask(Task task);
public void updateTask(Task task);
public List<Task> listTasks();
public Task getTaskById(Long bookId);
public void removeTask(Integer id);
public Task getTaskByName(String taskName);
}

