package com.stasiuksv.prototype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stasiuksv.prototype.LoggingUnit;
import com.stasiuksv.prototype.controllers.Role;
import com.stasiuksv.prototype.dao.RoleDAO;
import com.stasiuksv.prototype.model.RoleEntity;

@Service
@Transactional
public class RoleService implements DataService<Role, RoleEntity> 
{
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public HttpStatus create(Role role) 
	{
		LoggingUnit.log.info("Record created");
		roleDAO.create(new RoleEntity(role));
		return HttpStatus.OK;
	}

	@Override
	public HttpStatus update(Long id, Role role)
	{	
		
		if (roleDAO.getById(id)==null)
			return HttpStatus.NOT_FOUND;
		RoleEntity roleEntity = roleDAO.getById(id);
		roleEntity.setRoleName(role.getRoleName());
		roleDAO.update(roleEntity);
		LoggingUnit.log.info("Record "  + id +  " updated");
		return HttpStatus.OK;
	}

	@Override
	public HttpStatus deleteByID(Long id) 
	{
		RoleEntity role = roleDAO.getById(id);
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
	public RoleEntity getByID(Long id) 
	{
		RoleEntity role = roleDAO.getById(id);
		return role;
	}

	@Override
	public List<RoleEntity> listAll() 
	{
		return roleDAO.getAll();
	}
	
	@Override
	public RoleEntity getByName(String name)
	{
		return roleDAO.getByName(name);
	}
		
}
