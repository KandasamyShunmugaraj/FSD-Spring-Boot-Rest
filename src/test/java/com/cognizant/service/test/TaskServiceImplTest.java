package com.cognizant.service.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cognizant.dao.TaskDaoImpl;
import com.cognizant.dto.TaskDto;
import com.cognizant.entity.Task;
import com.cognizant.service.TaskServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {
	
	@Mock
	private TaskDaoImpl taskDaoImpl;
	
	@InjectMocks
	private TaskServiceImpl taskServiceImpl;


	@Test
	public void saveTask() {
		TaskDto task = new TaskDto();
		taskServiceImpl.addTask(task);
		Mockito.verify(taskDaoImpl).addTask(Mockito.any(Task.class));
	}
	
	@Test
	public void deleteTask() {
		Task task = new Task();
		Mockito.when(taskDaoImpl.getTaskById(1L)).thenReturn(task);	
		taskServiceImpl.updateTask("1");
		Mockito.verify(taskDaoImpl).updateTask(task);
	}
	

	@Test
	public void getTaskById() {
		Task task = new Task();
		task.setTaskName("taskName");
		task.setParentTask("2");
		Task parentTask = new Task();
		parentTask.setTaskName("parentTask");
		Mockito.when(taskDaoImpl.getTaskById(1L)).thenReturn(task);	
		Mockito.when(taskDaoImpl.getTaskById(2L)).thenReturn(parentTask);	
		TaskDto taskreturn = taskServiceImpl.getTaskById(1L);
		assertEquals("taskName",taskreturn.getTaskName());	
		assertEquals("parentTask",parentTask.getTaskName());	
	}
	
	@Test
	public void getTaskByIdForParenttaskNull() {
		Task task = new Task();
		task.setTaskName("taskName");
		task.setParentTask(null);
		Mockito.when(taskDaoImpl.getTaskById(1L)).thenReturn(task);		
		TaskDto taskreturn = taskServiceImpl.getTaskById(1L);
		assertEquals("taskName",taskreturn.getTaskName());	
		
	}
	
	@Test
	public void listTasks() {
		List<Task> listTask = new ArrayList<>();
		Task task1= new Task();
		task1.setParentTask("2");
		listTask.add(task1);
		Task parentTask = new Task();
		parentTask.setTaskName("parentTask");
		Task task2= new Task();
		task1.setParentTask("3");
		listTask.add(task2);
		Mockito.when(taskDaoImpl.listTasks()).thenReturn(listTask);	
		Mockito.when(taskDaoImpl.getTaskById(2L)).thenReturn(parentTask);	
		Mockito.when(taskDaoImpl.getTaskById(3L)).thenReturn(parentTask);
		List<TaskDto> listTaskreturn = taskServiceImpl.listTasks();
		assertEquals(2,listTaskreturn.size());	
	}
	
	@Test
	public void getTaskByName() {
		Task task = new Task();
		task.setTaskName("taskName");
		Mockito.when(taskDaoImpl.getTaskByName("taskName")).thenReturn(task);	
		TaskDto taskreturn = taskServiceImpl.getTaskByName("taskName");
		assertEquals("taskName",taskreturn.getTaskName());	
	}
	
	
}
