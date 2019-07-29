package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.Task;
import com.cognizant.service.TaskService;

@CrossOrigin(origins = "*" , allowedHeaders="*")
@RestController
@RequestMapping("/task")
/**
 * 
 * @author Admin
 *
 */
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/getTask/{taskId}")
	public ResponseEntity<?>  getTaskById(@PathVariable final String taskId) {
		Task task = taskService.getTaskById(Long.parseLong(taskId));
		 return ResponseEntity.ok().body(task);
	}


    @CrossOrigin(origins = "*" , allowedHeaders="*")
	@PostMapping("/add")
	public ResponseEntity<?> save(@RequestBody Task task) {
		  System.out.println("the json value of book is :::::: "+task.getTaskName());
	      taskService.addTask(task);
	      return ResponseEntity.ok().body("New Book has been saved with ID:");
	   }
    
    @GetMapping("/getAllTask")
	public ResponseEntity<?> getAllTask() {
		  List<Task> lisTasks=  taskService.listTasks();
	      return ResponseEntity.ok().body(lisTasks);
	   }
    
    
    @CrossOrigin(origins = "*" , allowedHeaders="*")
   	@DeleteMapping("/delete/{taskId}")
   	public ResponseEntity<?> delete(@PathVariable final String taskId) {
    	  System.out.println("the json value of taskId is :::::: "+taskId);
   	      taskService.updateTask(taskId);
   	      return ResponseEntity.ok().body("New Book has been saved with ID:");
   	   }

	
}