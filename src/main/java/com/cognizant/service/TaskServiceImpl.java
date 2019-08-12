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
	public void addTask(Task taskdto) {
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
		Task taskDto = populateFromEntity(task);
		if(task.getParentTask()!=null) {
			Task parentTask = taskDao.getTaskById(Long.parseLong(task.getParentTask()));
			taskDto.setParentTaskName(parentTask.getTaskName());
		}
		 return taskDto;
	}

	@Override
	@Transactional
	public List<Task> listTasks() {
		List<Task> taskDtoList = new ArrayList<>();
		List<Task> taskList = taskDao.listTasks();
		

		for (Task task : taskList) {
		    Task taskDto = populateFromEntity(task);
			if(task.getParentTask()!=null) {
			Task taskReturned = taskDao.getTaskById(Long.parseLong(task.getParentTask()));
			taskDto.setParentTaskName(taskReturned.getTaskName());
			}
			taskDtoList.add(taskDto);
		}
		
		return taskDtoList;
	}

	
	@Override
	@Transactional
	public Task getTaskByName(String taskName) {
		return populateFromEntity(taskDao.getTaskByName(taskName));
		
	}
	
	private static void populateFromDTO(Task taskdto,Task task) {
		
		task.setTaskId(taskdto.getTaskId());
		task.setTaskName(taskdto.getTaskName());
		task.setPriority(taskdto.getPriority());
		task.setStartDate(taskdto.getStartDate());
		task.setEndDate(taskdto.getEndDate());
		task.setEndTask(taskdto.isEndTask());
		task.setParentTask(taskdto.getParentTask());
	}
	
   private static Task populateFromEntity(Task task) {
       Task taskdto = new Task();
		
	   taskdto.setTaskId(task.getTaskId());
	   taskdto.setTaskName(task.getTaskName());
	   taskdto.setPriority(task.getPriority());
	   taskdto.setStartDate(task.getStartDate());
	   taskdto.setEndDate(task.getEndDate());
	   taskdto.setEndTask(task.isEndTask());
	   taskdto.setParentTask(task.getParentTask());
	   return taskdto;
	}

}
