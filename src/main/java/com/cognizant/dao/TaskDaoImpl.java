package com.cognizant.dao;


import java.util.List;


import com.cognizant.entity.Task;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;



@Repository
public class TaskDaoImpl implements TaskDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addTask(Task task) {
		try {
		sessionFactory.getCurrentSession().save(task);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateTask(Task task) {
		sessionFactory.getCurrentSession().update(task);
	}

	public List<Task> listTasks() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Task").list();
	}

	public Task getTaskById(Long taskId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Task t where t.taskId = :taskId");
		query.setParameter("taskId", taskId);
		List<Task> list = query.list();
		return list.size() > 0 ?(Task)list.get(0): null;
	}

	public void removeTask(Integer id) {
		Task task = (Task)sessionFactory.getCurrentSession()
				.load(Task.class, id);
				if(null != task) {
				sessionFactory.getCurrentSession().delete(task);
				}
		
	}

	public Task getTaskByName(String taskName) {
		Session session = sessionFactory.getCurrentSession();
		List<Task> list = session.createQuery("from Task t where t.task = :taskName")
		.setParameter("taskName", taskName)
		.list();
		return list.size() > 0 ?(Task)list.get(0): null;
	}
	

}
