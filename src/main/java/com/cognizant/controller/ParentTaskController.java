package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.service.ParentTaskService;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/parent")
/**
 * 
 * @author Admin
 *
 */
public class ParentTaskController {
	
	@Autowired
	private ParentTaskService parentTaskService;


	@RequestMapping(value="/{name}", method = RequestMethod.POST)
	public Boolean addParentTask(@PathVariable final String name) {
		parentTaskService.addParent(name);
		return true;

	}
	
	@GetMapping("/book/{id}")
	   public ResponseEntity<String> get(@PathVariable("id") long id) {
		parentTaskService.addParent("first1");
	      return ResponseEntity.ok().body("success");
	   }
	
	@RequestMapping(value="/{name}", method = RequestMethod.PUT)
	public Boolean editParentTask(@PathVariable final int parentTaskId ,
			@PathVariable final String name) {
		parentTaskService.editParent(parentTaskId,name);
		return true;

	}
	
}