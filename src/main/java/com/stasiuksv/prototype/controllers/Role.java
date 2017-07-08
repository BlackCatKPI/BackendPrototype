package com.stasiuksv.prototype.controllers;

import java.io.Serializable;

public class Role implements ExchangeObject, Serializable 
{
	private static final long serialVersionUID = -7999341308171445107L;
	
	private String roleName;
	
	public void setRoleName(String roleName) 
	{
		 this.roleName=roleName;
	}
	 
    public String getRoleName() 
    {
        return roleName;
    }
}
