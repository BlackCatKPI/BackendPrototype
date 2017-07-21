package com.stasiuksv.prototype.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
  	@Column(unique=true)
  	private String userName;
	  
  	@NotNull
  	@Size(min = 2, max = 80)
  	private String passWord;

  	@NotNull
  	private Boolean isActive;
  	
  	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
  	@JoinTable(name = "user_role", 
  				joinColumns = @JoinColumn(name = "userID"), 
  				inverseJoinColumns =  @JoinColumn(name = "roleID"))
  	Set<RoleEntity> roles;
  	
  	public UserEntity(){}
 	
 	public UserEntity(User user) 
  	{
 		userName = user.getUserName();
 		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassWord());
		passWord = encodedPassword;
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

