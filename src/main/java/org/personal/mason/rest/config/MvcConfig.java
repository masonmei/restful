package org.personal.mason.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.personal.mason.rest"})
public class MvcConfig {
}
