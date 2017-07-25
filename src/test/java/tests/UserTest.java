package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashSet;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stasiuksv.prototype.controllers.Role;
import com.stasiuksv.prototype.controllers.User;
import com.stasiuksv.prototype.dao.UserDAO;
import com.stasiuksv.prototype.model.UserEntity;
import com.stasiuksv.prototype.service.DataService;
 

 
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
    @Autowired
    protected WebApplicationContext wac;
 
    private MockMvc mockMvc; 
    
    private static ObjectMapper mapper = new ObjectMapper();
    
    @Configuration
    @EnableWebMvc
    @ComponentScan(value={"com.stasiuksv.prototype"})
    static class WebConfig extends WebMvcConfigurerAdapter 
    {
    }
 
    @Autowired
    private UserDAO userDAO;
    
   
    @Test
    public void test1User() throws Exception 
    {
    	if(userDAO.getByName("9999BILL")!=null)
    		userDAO.delete(userDAO.getByName("9999BILL"));
    	User user = new User();
    	user.setIsActive(true);
    	user.setUserName("9999BILL");
    	user.setPassWord("WILLY");
    	HashSet<Role> roles = new HashSet<>();
    	Role roleuser = new Role();
    	roleuser.setRoleName("USER");
    	roles.add(roleuser);
    	user.setRoles(roles);
      	userDAO.create(new UserEntity(user));

      	UserEntity testUser = userDAO.getByName("9999BILL");
    	
    	
    	assertEquals("9999BILL",testUser.getUserName());
    	assertFalse("WILLY".equals(testUser.getPassWord()));
    	assertEquals(true,testUser.getActive());
    	
    	
    }
    
    @Autowired
    private DataService<User, UserEntity> userService;
   
    @Test
    public void test2Service() throws Exception 
    {
    	if(userService.getByName("VASYL333")!=null)
    		userService.deleteByID(userDAO.getByName("VASYL333").getId());
    	UserEntity userEntity = userDAO.getByName("9999BILL");
    	User user = new User();
    	user.setIsActive(true);
    	user.setUserName("VASYL333");
    	user.setPassWord("WISKEY");
    	Role roleuser = new Role();
    	roleuser.setRoleName("USER");
    	HashSet<Role> roles = new HashSet<>();
    	roles.add(roleuser);
    	user.setRoles(roles);
    	userService.update(userEntity.getId(), user);
    	    	
    	UserEntity testUser = userDAO.getById(userEntity.getId());
    	assertTrue(testUser!=null);
    	assertEquals(testUser.getActive(),true);
    	assertEquals(testUser.getUserName(),"VASYL333");
    }
    
  
    
	@Before
	public void setup() 
	{
	    this.mockMvc = webAppContextSetup(this.wac).build();
	}
    
    @Test
    public void test3findById() throws Exception 
    {
    	UserEntity testUser = userDAO.getByName("VASYL333");
    	mockMvc.perform( delete("/users/{id}", testUser.getId()))
                .andExpect(status().isOk());
        
    }
}
	

