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

import com.stasiuksv.prototype.model.RoleEntity;

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
	
	
	public void create(RoleEntity role) 
	{
		getSession().persist(role);
	}
	
	public void delete(RoleEntity role)
	{
		if (getSession().contains(role))
			getSession().remove(role);
		else
			getSession().remove(getSession().merge(role));
		return;	
	}
	
	public void update(RoleEntity role) {
		getSession().merge(role);
	    return;
	  }
	
	public RoleEntity getById(long id) 
	{
	   return getSession().find(RoleEntity.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleEntity> getAll() 
	{
	    return  getSession().createQuery("from role").getResultList();
	}
	
	
	public RoleEntity getByName(String roleName) 
	{
		 CriteriaBuilder builder = getSession().getCriteriaBuilder();
		 CriteriaQuery<RoleEntity> criteria = builder.createQuery(RoleEntity.class);
		 Root<RoleEntity> from = criteria.from(RoleEntity.class);
		 criteria.select(from);
		 criteria.where(builder.equal(from.get("roleName"), roleName));
		 TypedQuery<RoleEntity> typed = getSession().createQuery(criteria);
		 try {
		        return typed.getSingleResult();
		   } catch (final NoResultException nre) {
		        return null;
		    }
	}
}
