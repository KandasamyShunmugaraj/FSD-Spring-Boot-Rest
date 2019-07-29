package com.cognizant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.dao.TaskDao;
import com.cognizant.entity.Task;

@Service
public class TaskServiceImpl  implements TaskService{
	
	@Autowired
	private TaskDao taskDao;

	@Override
	@Transactional
	public void addTask(Task task) {
		taskDao.addTask(task);
		
	}

	@Override
	@Transactional
	public void updateTask(String taskId) {
		Task task = taskDao.getTaskById(Long.parseLong(taskId));
		task.setEndTask(true);
		taskDao.updateTask(task);
		
	}

	@Override
	@Transactional
	public Task getTaskById(Long taskId) {
		Task task = taskDao.getTaskById(taskId);
		if(task.getParentTask()!=null) {
			Task parentTask = taskDao.getTaskById(Long.parseLong(task.getParentTask()));
			task.setParentTaskName(parentTask.getTaskName());
		}
		 return task;
	}

	@Override
	@Transactional
	public List<Task> listTasks() {
		List<Task> taskList = new ArrayList<>();
		taskList= taskDao.listTasks();
		
		for (Task task : taskList) {
			if(task.getParentTask()!=null) {
			Task taskReturned = taskDao.getTaskById(Long.parseLong(task.getParentTask()));
			task.setParentTaskName(taskReturned.getTaskName());
			}
		}
		
		return taskList;
	}

	@Override
	@Transactional
	public void removeTask(Integer id) {
		taskDao.removeTask(id);
		
	}
	@Override
	@Transactional
	public Task getTaskByName(String taskName) {
		return taskDao.getTaskByName(taskName);
		
	}

}
