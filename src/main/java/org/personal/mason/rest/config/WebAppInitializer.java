package org.personal.mason.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 4/24/14
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebAppInitializer implements WebApplicationInitializer {
    private static Logger LOG = LoggerFactory.getLogger(WebAppInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = createRootContext(servletContext);
        configureSpringMvc(servletContext, context);
        configureSpringSecurity(servletContext, context);
    }

    private void configureSpringSecurity(ServletContext servletContext, WebApplicationContext context) {
        FilterRegistration.Dynamic springSecurity = servletContext.addFilter("springSecurityFilterChain",
                new DelegatingFilterProxy("springSecurityFilterChain", context));
        springSecurity.addMappingForUrlPatterns(null, true, "/*");
    }

    private void configureSpringMvc(ServletContext servletContext, WebApplicationContext context) {
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(MvcConfig.class);

        mvcContext.setParent(context);

        ServletRegistration.Dynamic appServlet = servletContext.addServlet("webservice", new DispatcherServlet(mvcContext));
        appServlet.setLoadOnStartup(1);
        Set<String> mappingConflicts = appServlet.addMapping("/");

        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                LOG.error("Mapping conflict: " + s);
            }
            throw new IllegalStateException("'webservice' cannot be mapped to '/'");
        }
    }

    private WebApplicationContext createRootContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(CoreConfig.class, SecurityConfig.class);
        context.refresh();

        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter("defaultHtmlEscape", "true");
        return context;
    }
}
