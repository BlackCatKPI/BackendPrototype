package com.stasiuksv.prototype.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stasiuksv.prototype.model.UserEntity;

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
		
	public void create(UserEntity user) 
	{
		getSession().persist(user);
	}
	
	public void delete(UserEntity user)
	{
		if (getSession().contains(user))
			getSession().remove(user);
		else
			getSession().remove(getSession().merge(user));	
	}
	
	public void update(UserEntity user) {
		getSession().merge(user);
	    return;
	  }
	
	public UserEntity getById(long id) {
	    return getSession().find(UserEntity.class, id);
	  }
	
	
	@SuppressWarnings("unchecked")
	public List<UserEntity> getAll() 
	{
	    return  getSession().createQuery("from user").getResultList();
	}
}