package com.stasiuksv.prototype.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Set;

@Entity(name = "user")
@Table(name = "user",  uniqueConstraints = {@UniqueConstraint(columnNames={"userName"})})
public class User implements StoredObject
{

	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)
  	private long id;
	  
  	@NotNull
  	@Size(min = 2, max = 80)
  	private String userName;
	  
  	@NotNull
  	@Size(min = 2, max = 80)
  	private String passWord;

  	@NotNull
  	private Boolean isActive;
  	 	
 	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL) 
 	@JoinColumn(name="id")
  	private Set<Role> roles;
  
 	public User(){}
 	
 	public User(String userName, String passWord) 
  	{
 		this.userName=userName;
 		this.passWord=passWord;
    }
  

    public long getId() 
    {
        return id;
    }

    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
 	{ 
 		this.userName=userName;
 	}
    
    public String getPassWord() 
    {
        return passWord;
    }

    public void setPassWord(String passWord) 
    {
        this.passWord=passWord;
    }
    
    public Boolean getActive() 
    {
        return isActive;
    }

    public void setActive(Boolean isActive) 
    {
        this.isActive=isActive;
    }
    
    public void addRole(Role role)
    {
       	roles.add(role);
    }
    
    public Set<Role> getRoles()
    {
    	return roles;
    }
     
}

