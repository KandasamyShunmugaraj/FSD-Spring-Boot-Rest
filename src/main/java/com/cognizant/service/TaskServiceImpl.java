package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.dao.TaskDao;
import com.cognizant.dto.TaskDto;
import com.cognizant.entity.Task;

@Service
public class TaskServiceImpl  implements TaskService{
	
	@Autowired
	private TaskDao taskDao;

	@Override
	@Transactional
	public void addTask(TaskDto taskdto) {
		Task task = new Task();
		populateFromDTO(taskdto,task);
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
		List<Task> taskList = taskDao.listTasks();

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
	public Task getTaskByName(String taskName) {
		return taskDao.getTaskByName(taskName);
		
	}
	
	private void populateFromDTO(TaskDto taskdto,Task task) {
		
		task.setTaskId(taskdto.getTaskId());
		task.setTaskName(taskdto.getTaskName());
		task.setPriority(taskdto.getPriority());
		task.setStartDate(taskdto.getStartDate());
		task.setEndDate(taskdto.getEndDate());
		task.setEndTask(taskdto.isEndTask());
		task.setParentTask(taskdto.getParentTask());
		task.setParentTaskName(taskdto.getParentTaskName());
	}

}
