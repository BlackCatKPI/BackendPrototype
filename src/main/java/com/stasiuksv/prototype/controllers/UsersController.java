package com.stasiuksv.prototype.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	

	@PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object>createObjectByPOST(@RequestBody User user) 
    {
      	HttpStatus status = userService.create(user);
      	if(status.equals(HttpStatus.BAD_REQUEST))ResponseEntity.status(status).body("Wrong roles"); 
      	if(status.equals(HttpStatus.CONFLICT))ResponseEntity.status(status).body("User with such name already exists"); 
    	return ResponseEntity.ok("Record created");
    }
    
	@PreAuthorize("hasAuthority('MODERATOR')")
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
    
	@PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByID(@PathVariable long id) 
    {
    	UserEntity user = userService.getByID(id);
    	if (user == null)
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested record is not found");
    	else
    		return ResponseEntity.ok(user);
    }
    
	@PreAuthorize("hasAuthority('MODERATOR')")
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllUsers() 
    {
    	List<UserEntity> usersList = userService.listAll();
	   	return ResponseEntity.ok(usersList);
    }
    
	@PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUserByID(@PathVariable long id) 
    {
    	HttpStatus status = userService.deleteByID(id);
    	if (status.equals(HttpStatus.OK))
    		return ResponseEntity.ok("Record delted");
    	else
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested record is not found");
    	
    }
      
 }
