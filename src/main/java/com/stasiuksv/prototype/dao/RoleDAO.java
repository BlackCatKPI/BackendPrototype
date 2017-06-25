package com.stasiuksv.prototype.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stasiuksv.prototype.model.Role;

@Repository
@Transactional
public class RoleDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() 
	{
		return this.sessionFactory.getCurrentSession();
	}
	
	
	public void create(Role role) 
	{
		getSession().persist(role);
	}
	
	public void delete(Role role)
	{
		if (getSession().contains(role))
			getSession().remove(role);
		else
			getSession().remove(getSession().merge(role));
		return;	
	}
	
	public void update(Role role) {
		getSession().merge(role);
	    return;
	  }
	
	public Role getById(long id) 
	{
	   return getSession().find(Role.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getAll() 
	{
	    return  getSession().createQuery("from role").getResultList();
	}
}
