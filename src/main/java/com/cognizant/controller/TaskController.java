package com.cognizant.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	 private static final Logger LOGGER = Logger.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;

	@GetMapping("/getTask/{taskId}")
	public ResponseEntity<Task>  getTaskById(@PathVariable final String taskId) {
	    Task task = taskService.getTaskById(Long.parseLong(taskId));
		 return ResponseEntity.ok().body(task);
	}


    @CrossOrigin(origins = "*" , allowedHeaders="*")
	@PostMapping("/add")
	public ResponseEntity<String> save(@RequestBody Task taskDto) {
        LOGGER.info("the json value of book is :::::: "+taskDto.getTaskName());
	      taskService.addTask(taskDto);
	      return ResponseEntity.ok().body("New Task has been saved");
	   }
    
    @GetMapping("/getAllTask")
	public ResponseEntity<List<Task>> getAllTask() {
		  List<Task> lisTasks=  taskService.listTasks();
	      return ResponseEntity.ok().body(lisTasks);
	   }
    
    
    @CrossOrigin(origins = "*" , allowedHeaders="*")
   	@PutMapping("/update/{taskId}")
   	public ResponseEntity<String> update(@PathVariable final String taskId) {
        LOGGER.info("the json value of taskId is :::::: "+taskId);
   	      taskService.updateTask(taskId);
   	      return ResponseEntity.ok().body("New Task has been update");
   	   }
}