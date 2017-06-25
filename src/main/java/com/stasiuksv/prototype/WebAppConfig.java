package com.stasiuksv.prototype;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
@Configuration
@EnableWebMvc
@ComponentScan(value={"com.stasiuksv.prototype"})

public class WebAppConfig {

}