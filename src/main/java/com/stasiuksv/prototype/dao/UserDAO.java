package com.stasiuksv.prototype.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stasiuksv.prototype.model.User;

@Repository
@Transactional
public class UserDAO 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() 
	{
		return this.sessionFactory.getCurrentSession();
	}
	
	
	public void create(User user) 
	{
		getSession().persist(user);
	}
	
	public void delete(User user)
	{
		if (getSession().contains(user))
			getSession().remove(user);
		else
			getSession().remove(getSession().merge(user));	
	}
	
	public void update(User user) {
		getSession().merge(user);
	    return;
	  }
	
	public User getById(long id) {
	    return getSession().find(User.class, id);
	  }
	
	
	@SuppressWarnings("unchecked")
	public List<User> getAll() 
	{
	    return  getSession().createQuery("from user").getResultList();
	}
}