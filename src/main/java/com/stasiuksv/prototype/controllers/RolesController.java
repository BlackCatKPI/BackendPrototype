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

import com.stasiuksv.prototype.model.RoleEntity;
import com.stasiuksv.prototype.service.DataService;
 
@RestController
@RequestMapping("/roles")
public class RolesController {
 
	@Autowired
	private DataService<Role, RoleEntity> roleService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object>createObjectByPOST(@RequestBody Role role) 
    {
    	roleService.create(role);
    	return ResponseEntity.ok("Record created");
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object>changeObjectByPOST(@PathVariable long id, Role role) 
    {
    	HttpStatus status = roleService.update(id, role);
    	
    	if (status.equals(HttpStatus.OK))
    		return ResponseEntity.ok("Record updated");
    	else
    		return ResponseEntity.status(status).body("Record " + id +" not found");
    }
    
    @PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getRoleByID(@PathVariable long id) 
    {
    	RoleEntity role = roleService.getByID(id);
    	if (role.equals("null"))
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record " + id +" not found");
    	else
    		return ResponseEntity.ok(role);
    }
    
    @PreAuthorize("hasAuthority('MODERATOR')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRoles() 
    {
    	List<RoleEntity> result = roleService.listAll();
	   	return ResponseEntity.ok(result);
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
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
