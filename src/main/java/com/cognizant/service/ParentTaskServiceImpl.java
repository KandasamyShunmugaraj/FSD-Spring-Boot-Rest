package com.cognizant.service;

import java.util.List;

import com.cognizant.dao.ParentTaskDao;
import com.cognizant.entity.ParentTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParentTaskServiceImpl  implements ParentTaskService{
	
	@Autowired
	private ParentTaskDao parentTaskDao;

	@Override
	@Transactional
	public void addParent(String parentTaskName) {
		
		ParentTask parentTask = new ParentTask();
		parentTask.setParentTaskName(parentTaskName);
		parentTaskDao.addParent(parentTask);
		
	}
	
	

	@Override
	@Transactional
	public void editParent(int parentId,
			String parentTaskName) {	
		ParentTask parentTask =
				parentTaskDao.getParent(parentId);
		if(parentTask !=  null) {
		
		parentTask.setParentTaskName(parentTaskName);
		parentTaskDao.addParent(parentTask);
		}
		
	}
	
	@Override
	@Transactional
	public ParentTask getParent(int parentId) {
		return parentTaskDao.getParent(parentId);
		
	}



	@Override
	public List<ParentTask> getAllParentTask() {
		return parentTaskDao.getAllParent();
	}



	@Override
	public ParentTask searchParent(int parentId) {
		return parentTaskDao.getParent(parentId);
	}

}
