package com.stasiuksv.prototype.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.stasiuksv.prototype.controllers.Role;
import com.stasiuksv.prototype.controllers.User;


@Entity(name = "user")
@Table(name = "user")
public class UserEntity implements StoredObject
{

	@Id
	@GeneratedValue
  	private long id;
	  
  	@NotNull
  	@Size(min = 2, max = 80)
  	private String userName;
	  
  	@NotNull
  	@Size(min = 2, max = 80)
  	private String passWord;

  	@NotNull
  	private Boolean isActive;
  	
  	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
  	//@JoinColumn(name="id")
  	Set<RoleEntity> roles;
  	
  	public UserEntity(){}
 	
 	public UserEntity(User user) 
  	{
 		userName = user.getUserName();
 		passWord = user.getPassWord();
 		isActive = user.getIsActive();
 		roles=new HashSet<>();
 		for (Role role:user.getRoles())
 			roles.add(new RoleEntity(role));
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
    
    public void setRoles(Set<RoleEntity> roles) 
    {
        this.roles=roles;
    }
    
    public Set<RoleEntity> getRoles()
    {
    	return roles;
    }
    
}

