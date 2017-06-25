package com.stasiuksv.prototype.service;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stasiuksv.prototype.LoggingUnit;
import com.stasiuksv.prototype.dao.RoleDAO;
import com.stasiuksv.prototype.model.Role;

@Service
@Transactional
public class RoleService implements DataService<Role> 
{
		
	
		@Autowired
		private RoleDAO roleDAO;
		
		private static ObjectMapper mapper = new ObjectMapper();
		

		@Override
		public Role convertFromJSON(String jsonString) throws JsonParseException, JsonMappingException, IOException
		{
			Role role = mapper.readValue(jsonString, Role.class);
			return role;
		}
		
		@Override
		public String convertToJSON(Object objectToJSON) throws JsonProcessingException 
		{
			String jsonString = mapper.writeValueAsString(objectToJSON);
			return jsonString;
		}
	 
		
		
		@Override
		public HttpStatus create(String jsonString) 
		{
			Role role;
			try
			{
				role = convertFromJSON(jsonString);
			}
			catch (Exception e)
			{
				LoggingUnit.log.error(e.getMessage());
				return HttpStatus.BAD_REQUEST;
			}
			
			if(roleDAO.getById(role.getId())==null)
			{
				LoggingUnit.log.info("Record created");
				roleDAO.create(role);
				return HttpStatus.OK;
			}
			else
			{
				LoggingUnit.log.info("Record exists");
				return HttpStatus.NOT_ACCEPTABLE;
			}
		}

		@Override
		public HttpStatus update(Long id, String jsonString)
		{	
			Role role;
			try
			{
				role = convertFromJSON(jsonString);
			}
			catch (Exception e)
			{
				LoggingUnit.log.error(e.getMessage());
				return HttpStatus.BAD_REQUEST;
			}
			
			if (roleDAO.getById(id)==null)
				return HttpStatus.NOT_FOUND;
			
			if (role.getId()==id)
			{
				roleDAO.update(role);
				LoggingUnit.log.info("Record "  + role.getId() +  "updated");
				return HttpStatus.OK;
			}
			else
			{
				roleDAO.delete(roleDAO.getById(id));
				roleDAO.create(role);
				LoggingUnit.log.info("Record "  + id +  "deleted");
				LoggingUnit.log.info("Record " + role.getId() +" created");
				return HttpStatus.CREATED;
			}			
		}

		@Override
		public HttpStatus deleteByID(Long id) 
		{
			Role role = roleDAO.getById(id);
			if(role!=null)
			{
				roleDAO.delete(role);
				LoggingUnit.log.info("Record deleted");
				return HttpStatus.OK;
			}
			else 
				return HttpStatus.NOT_FOUND;
		}

		@Override
		public String getByID(Long id) 
		{
			try
			{
				String jsonString = convertToJSON(roleDAO.getById(id));
				LoggingUnit.log.info("Record got by id");
				return jsonString;
			}
			catch (Exception e)
			{
				LoggingUnit.log.error(e.getMessage());
				return "";
			}
		}

		@Override
		public String listAll() 
		{
			try
			{
				String jsonString = convertToJSON(roleDAO.getAll());
				LoggingUnit.log.info("Record got by id");
				return jsonString;
			}
			catch (Exception e)
			{
				LoggingUnit.log.error(e.getMessage());
				return "";
			}
		}

		
}
