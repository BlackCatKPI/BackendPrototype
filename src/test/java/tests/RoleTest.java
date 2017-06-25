package tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
 
import java.net.URI;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stasiuksv.prototype.dao.RoleDAO;
 

 
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class RoleTest {
    @Autowired
    protected WebApplicationContext wac;
 
    @Autowired
    protected MockServletContext mockServletContext;
 
    private MockMvc mockMvc;
    private static ObjectMapper mapper = new ObjectMapper();
    
    @Configuration
    @EnableWebMvc
    @ComponentScan(value={"com.stasiuksv.prototype"})
    static class WebConfig extends WebMvcConfigurerAdapter {
 
        @Bean
        public GreetController greetController() {
            return new GreetController();
        }
    }
 
    //!!TODO
    @Controller
    private static class GreetController {
 
        @RequestMapping(value = "/greet/{id}", method = RequestMethod.GET)
        @ResponseBody
        public String getCircuit(@PathVariable String id) {
            return id + " world";
        }
    }
    
    
    
   
 
    
    
    @Before
    public void setup() 
    {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }
 
    @Ignore
    @Test
    public void verifyWac() 
    {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
    }
 
   @Ignore
   @Test
    public void testGetRequest() throws Exception {
        String id = "hello";
        URI url = UriComponentsBuilder.fromUriString("/greet").pathSegment(id)
                .build().encode().toUri();
        System.out.println("Call " + url + ", result: " + 
                mockMvc.perform(get(url)).andReturn().getResponse().getContentAsString());
        mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string("hello world"));
    }
 
   
    
    @Test
    public void testDataBase() throws Exception 
    {
    	String jsonString = mapper.writeValueAsString(roleDAO.getAll());
    	System.out.println(jsonString);
    }
    
    @Autowired
    private RoleDAO roleDAO;
}
	

