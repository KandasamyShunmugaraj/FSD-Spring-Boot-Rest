package com.cognizant.dao;

import java.util.List;
import com.cognizant.entity.ParentTask;

/**
 * 
 * @author Admin
 *
 */

public interface ParentTaskDao {
	
	 void addParent(ParentTask parentTask);
	 ParentTask getParent(int parentId);
	 List<ParentTask> getAllParent();
}
