package com.stasiuksv.prototype.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.stasiuksv.prototype.controllers.Role;
import com.stasiuksv.prototype.controllers.User;
import com.stasiuksv.prototype.dao.RoleDAO;
import com.stasiuksv.prototype.service.RoleService;


@Entity(name = "user")
@Table(name = "user")
public class UserEntity implements StoredObject
{

	@Id
	@GeneratedValue(generator = "idGenerator", strategy = GenerationType.SEQUENCE)
	@TableGenerator(name = "idGenerator", table = "table_sequence", 
    pkColumnName = "name", valueColumnName = "value",
    pkColumnValue = "user_gen", 
    initialValue = 2, allocationSize = 1)
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
  	
  	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
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

