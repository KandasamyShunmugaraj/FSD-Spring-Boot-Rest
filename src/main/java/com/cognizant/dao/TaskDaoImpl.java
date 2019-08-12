package com.cognizant.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.Task;
import com.cognizant.exception.TaskNotFoundException;

@Repository
public class TaskDaoImpl implements TaskDao {

	private SessionFactory taskSessionFactory;

	@Autowired
	private SessionFactory sessionFactory;

	public TaskDaoImpl(SessionFactory sessionFactory) {
		this.taskSessionFactory = sessionFactory;
	}

	@Override
	public void addTask(Task task) {
		taskSessionFactory.getCurrentSession().save(task);
	}
	@Override
	public void updateTask(Task task) {
		taskSessionFactory.getCurrentSession().update(task);
	}
	@Override
	public List<Task> listTasks() {
		Session session = taskSessionFactory.getCurrentSession();
		return session.createQuery("from Task").list();
	}
	@Override
	public Task getTaskById(Long taskId) {
		Session session = taskSessionFactory.getCurrentSession();
		Query query = session.createQuery("from Task t where t.taskId = :taskId");
		query.setParameter("taskId", taskId);
		List<Task> list = query.list();
		return list.isEmpty() ? null :list.get(0);
	}
	@Override
	public void removeTask(Integer id) {
		Task task = taskSessionFactory.getCurrentSession().load(Task.class, id);
		if (null != task) {
			taskSessionFactory.getCurrentSession().delete(task);
		}else {
			throw new TaskNotFoundException("Task does not exist");
		}

	}
	@Override
	public Task getTaskByName(String taskName) {
		Session session = taskSessionFactory.getCurrentSession();
		Query query = session.createQuery("from Task t where t.task = :taskName");
		query.setParameter("taskName", taskName);
		List<Task> list = query.list();
		return list.isEmpty() ?  null:  list.get(0);
	}

}
