package com.cognizant.dao;

import com.cognizant.entity.ParentTask;
import com.cognizant.entity.Task;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;;

@Repository
public class ParentTaskDaoImpl implements ParentTaskDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addParent(ParentTask parentTask) {
		sessionFactory.getCurrentSession().save(parentTask);
		
	}

	public ParentTask getParent(int parentId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ParentTask p where p.parentId = :parentId");
		query.setParameter("parentId", parentId);
		List<ParentTask> list = query.list();
		return list.size() > 0 ?(ParentTask)list.get(0): null;
		
		
	}

	@Override
	public List<ParentTask> getAllParent() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ParentTask");
		return query.list();
	}

	
}
