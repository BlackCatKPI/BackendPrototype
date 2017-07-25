package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
 
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.stasiuksv.prototype.controllers.Role;
import com.stasiuksv.prototype.dao.RoleDAO;
import com.stasiuksv.prototype.model.RoleEntity;
import com.stasiuksv.prototype.service.DataService;
 

 
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleTest {
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
    private RoleDAO roleDAO;
    
    @Test
    public void test1DAO() throws Exception 
    {
    	if(roleDAO.getByName("Superman999")!=null)
    		roleDAO.delete(roleDAO.getByName("Superman999"));
    	assertTrue(roleDAO.getByName("Superman999")==null);
    	
    	RoleEntity role = new RoleEntity("Superman999");
    	roleDAO.create(role);
    	
    	RoleEntity roleTest =roleDAO.getByName("Superman999");
    	
    	assertTrue(!roleTest.equals(null));
    }
    
    @Autowired
    private DataService<Role,RoleEntity> roleService;
    
    @Test
    public void test2Service() throws Exception 
    {
    	if(roleDAO.getByName("EVIL777")!=null)
    		roleDAO.delete(roleDAO.getByName("EVIL777"));
    	RoleEntity roleTest =roleService.getByName("Superman999");
    	Role role= new Role();
    	role.setRoleName("EVIL777");
    	roleService.update(roleTest.getId(),role);
    	String object = mapper.writeValueAsString(roleService.getByID(roleTest.getId()));
    	assertEquals(object,"{\"id\":"+roleTest.getId()+",\"roleName\":\"EVIL777\"}");
    }
    
      
    
    @Before
	public void setup() 
	{
	    this.mockMvc = webAppContextSetup(this.wac).build();
	}
    
    @Test
    public void test3findById() throws Exception 
    {
    	RoleEntity roleTest =roleService.getByName("EVIL777");
    	MvcResult result = mockMvc.perform(get("/roles/{id}", roleTest.getId())).andExpect(status().isOk()).andReturn();
    	String content = result.getResponse().getContentAsString();
    	System.out.println(content);
        assertEquals(content, "{\"id\":"+roleTest.getId()+",\"roleName\":\"EVIL777\"}");
        mockMvc.perform( delete("/roles/{id}", roleTest.getId())).andExpect(status().isOk());
       
    }
   
}
	

