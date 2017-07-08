package com.stasiuksv.prototype.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.stasiuksv.prototype.model.UserEntity;
import com.stasiuksv.prototype.service.DataService;
 
@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private DataService<User,UserEntity> userService;
	
 

	
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object>createObjectByPOST(@RequestBody User user) 
    {
      	HttpStatus status = userService.create(user);
    	return ResponseEntity.ok("Record created");
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object>changeObjectByPOST(@PathVariable long id, @RequestBody User user) 
    {
    	HttpStatus status = userService.update(id, user);
    	
    	switch (status)
    	{
    		case OK: return ResponseEntity.ok("Record updated");
    		case CREATED: return ResponseEntity.status(status).body("Record " + id +" deleted, created new record");
    		case NOT_FOUND: return ResponseEntity.status(status).body("Record " + id +" not found");
    		default: return ResponseEntity.status(status).body("Bad request");
    	}
     }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getuserByID(@PathVariable long id) 
    {
    	UserEntity user = userService.getByID(id);
    	if (user == null)
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested record is not found");
    	else
    		return ResponseEntity.ok(user);
    }
   
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllusers() 
    {
    	List<UserEntity> usersList = userService.listAll();
	   	return ResponseEntity.ok(usersList);
    }
    

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteuserByID(@PathVariable long id) 
    {
    	HttpStatus status = userService.deleteByID(id);
    	if (status.equals(HttpStatus.OK))
    		return ResponseEntity.ok("Record delted");
    	else
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested record is not found");
    	
    }
 }
