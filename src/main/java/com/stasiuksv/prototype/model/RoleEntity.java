package com.stasiuksv.prototype.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.stasiuksv.prototype.controllers.Role;

@Entity(name = "role")
@Table(name = "role")
public class RoleEntity implements StoredObject
{
	
		@Id
		@GeneratedValue(generator = "idGenerator", strategy = GenerationType.SEQUENCE)
		@TableGenerator(name = "idGenerator", table = "table_sequence", 
        pkColumnName = "name", valueColumnName = "value",
        pkColumnValue = "role_gen", 
        initialValue = 4, allocationSize = 1)
		private long id;
		
		
		@NotNull
	  	@Size(min = 2, max = 80)
		private String roleName;
    
		public RoleEntity()
		{}
		
		public RoleEntity(Role role)
		{
			this.roleName=role.getRoleName();
		}
		
		
		public RoleEntity(String roleName)
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
