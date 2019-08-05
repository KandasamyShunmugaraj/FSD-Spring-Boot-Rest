package com.cognizant.dao.test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.cognizant.dao.TaskDaoImpl;
import com.cognizant.entity.Task;

@RunWith(MockitoJUnitRunner.class)
public class TaskDaoImplTest {
	SessionFactory sessionFactory = null;
	TaskDaoImpl taskDao;
	Session session = null;
	Query query = null;

	@Before
	public void setup() {
		sessionFactory = Mockito.mock(SessionFactory.class);
		session = Mockito.mock(Session.class);
		query = Mockito.mock(Query.class);
		taskDao = new TaskDaoImpl(sessionFactory);
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		
	}

	@Test
	public void saveTask() {
		Task task = new Task();
		taskDao.addTask(task);
		Mockito.verify(sessionFactory.getCurrentSession()).save(task);
	}
	
	@Test
	public void updateTask() {
		Task task = new Task();
		taskDao.updateTask(task);
		Mockito.verify(sessionFactory.getCurrentSession()).update(task);
	}
	
    @Test
	public void listTask() {
		List<Task> listTask = new ArrayList<>();
		listTask.add(new Task());
		listTask.add(new Task());
		Mockito.when(session.createQuery("from Task")).thenReturn(query);
		Mockito.when(query.list()).thenReturn(listTask);
		List<Task> listTaskReturned  = taskDao.listTasks();
		assertEquals(2,listTaskReturned.size());	
	}	
    
    @Test
  	public void getTaskById() {
  		List<Task> listTask = new ArrayList<>();
  		Task task = new Task();
  		task.setTaskName("firstTask");
  		listTask.add(task);
  		Mockito.when(session.createQuery("from Task t where t.taskId = :taskId")).thenReturn(query);
  		Mockito.when(query.list()).thenReturn(listTask);
  		Task taskReturned  = taskDao.getTaskById(1L);
  		assertEquals("firstTask",taskReturned.getTaskName());	
  	}
    
    @Test
  	public void getTaskByIdForNull() {
  		List<Task> listTask = new ArrayList<>();
  		Mockito.when(session.createQuery("from Task t where t.taskId = :taskId")).thenReturn(query);
  		Mockito.when(query.list()).thenReturn(listTask);
  		Task taskReturned  = taskDao.getTaskById(1L);
  		assertEquals(null,taskReturned);	
  	}
    

	@Test
	public void removeTask() {
		Task task = new Task();
		Mockito.when(session.load(Task.class, 1)).thenReturn(task);
		taskDao.removeTask(1);
		Mockito.verify(sessionFactory.getCurrentSession()).delete(task);
	}
	

	@Test(expected=RuntimeException.class)
	public void removeTaskForNull() {
		Mockito.when(session.load(Task.class, 1)).thenReturn(null);
		taskDao.removeTask(1);
		
	}	
	  @Test
	  	public void getTaskByName() {
	  		List<Task> listTask = new ArrayList<>();
	  		Task task = new Task();
	  		task.setTaskName("firstTask");
	  		listTask.add(task);
	  		Mockito.when(session.createQuery("from Task t where t.task = :taskName")).thenReturn(query);
	  		Mockito.when(query.list()).thenReturn(listTask);
	  		Task taskReturned  = taskDao.getTaskByName("firstTask");
	  		assertEquals("firstTask",taskReturned.getTaskName());	
	  	}
	  
	  @Test
	  	public void getTaskByNameForNull() {
	  		List<Task> listTask = new ArrayList<>();
	  		Mockito.when(session.createQuery("from Task t where t.task = :taskName")).thenReturn(query);
	  		Mockito.when(query.list()).thenReturn(listTask);
	  		Task taskReturned  = taskDao.getTaskByName("firstTask");
	  		assertEquals(null,taskReturned);	
	  	}

}
