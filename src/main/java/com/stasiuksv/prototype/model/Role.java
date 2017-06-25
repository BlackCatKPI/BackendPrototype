package com.stasiuksv.prototype.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "role")
@Table(name = "role")
public class Role implements StoredObject
{
		@Id
	  	private long id;
		
		@NotNull
	  	@Size(min = 2, max = 80)
		private String roleName;
    
		public Role()
		{}
		
		public Role(String roleName)
		{
			this.roleName=roleName;
		}
		
	    public String getRoleName() 
	    {
	        return roleName;
	    }
	    	    
	    public void setRoleName(String roleName) 
	    {
	    	this.roleName=roleName;
	    }
	    
	    public long getId() 
	    {
	        return id;
	    }
}
