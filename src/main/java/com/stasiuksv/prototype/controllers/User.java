package com.stasiuksv.prototype.controllers;

import java.util.Set;

public class User implements ExchangeObject
{
  	private String userName;
	private String passWord;
  	private Boolean isActive;
  	private Set<Role> roles;
 
    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    
    public void setPassWord(String passWord) 
    {
        this.passWord=passWord;
    }
 
    public void  setIsActive(Boolean isAvtive) 
    {
        this.isActive=isAvtive;
    }
   
    public void setRoles(Set<Role> roles) 
    {
        this.roles = roles;
    }
  	
    public String getUserName()
    {
        return userName;
    }
    
    public String getPassWord() 
    {
        return passWord;
    }
 
    public Boolean getIsActive() 
    {
        return isActive;
    }
    
    public Set<Role> getRoles() 
    {
        return roles;
    }
}

