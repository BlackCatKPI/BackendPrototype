package com.stasiuksv.prototype.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	
	public UserEntity getByName(String userName) 
	{
		 CriteriaBuilder builder = getSession().getCriteriaBuilder();
		 CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
		 Root<UserEntity> from = criteria.from(UserEntity.class);
		 criteria.select(from);
		 criteria.where(builder.equal(from.get("userName"), userName));
		 TypedQuery<UserEntity> typed = getSession().createQuery(criteria);
		 try {
		        return typed.getSingleResult();
		   } catch (final NoResultException nre) {
		        return null;
		    }
	}
}