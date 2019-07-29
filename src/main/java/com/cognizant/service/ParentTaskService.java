package com.cognizant.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entity.ParentTask;

public interface ParentTaskService {
	
	public void addParent(String parentTaskName);
	public ParentTask getParent(int parentId);
	public void editParent(int parentId,String parentTaskName);
	public List<ParentTask> getAllParentTask();
	public ParentTask searchParent(int parentId);
}
