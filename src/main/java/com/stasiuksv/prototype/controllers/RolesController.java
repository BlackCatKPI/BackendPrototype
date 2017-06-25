package com.stasiuksv.prototype.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stasiuksv.prototype.model.Role;
import com.stasiuksv.prototype.service.DataService;
 
@RestController
@RequestMapping("/roles")
public class RolesController {
 
	@Autowired
	private DataService<Role> roleService;
	
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object>createObjectByPOST(@RequestBody String role) 
    {
    	HttpStatus status = roleService.create(role);
    	
    	switch (status)
    	{
    		case OK: return ResponseEntity.ok("Record created");
    		case NOT_ACCEPTABLE: ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Record with such ID already exists");
    		default: return ResponseEntity.status(status).body("Bad request");
    	}
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object>changeObjectByPOST(@PathVariable long id, @RequestBody String role) 
    {
    	HttpStatus status = roleService.update(id, role);
    	
    	switch (status)
    	{
    		case OK: return ResponseEntity.ok("Record updated");
    		case CREATED: return ResponseEntity.status(status).body("Record " + id +" deleted, created new record");
    		case NOT_FOUND: return ResponseEntity.status(status).body("Record " + id +" not found");
    		default: return ResponseEntity.status(status).body("Bad request");
    	}
     }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getRoleByID(@PathVariable long id) 
    {
    	String role = roleService.getByID(id);
    	if (role.equals("null"))
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested record is not found");
    	else
    		return ResponseEntity.ok(role);
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRoles() 
    {
    	String result = roleService.listAll();
	   	return ResponseEntity.ok(result);
    }
    

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteRoleByID(@PathVariable long id) 
    {
    	HttpStatus status = roleService.deleteByID(id);
    	if (status.equals(HttpStatus.OK))
    		return ResponseEntity.ok("Record delted");
    	else
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested record is not found");
    	
    }
 }
