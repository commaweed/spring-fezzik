package fezzik.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import fezzik.web.controller.model.WebAttributeError;

/**
 * The framework was forwarding error responses as HTML.  This controller overrides the "/error" mapping that the framework
 * is using that by default returned HTML, to JSON.
 */
@RestController
public class FezzikRestErrorController implements ErrorController {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(FezzikRestErrorController.class);

	// Provides access to error attributes which can be logged or presented to the user.
    @Autowired
    private ErrorAttributes errorAttributes;

    private static final String ERROR_PATH = "/error";
    
    /**
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    public WebAttributeError error(HttpServletRequest request, HttpServletResponse response) {
    	// TODO: use a property in the property file to control whether or not we want the stack trace 
    	boolean includeStackTrace = false;
    	
        return new WebAttributeError(
        	response.getStatus(), 
        	getErrorAttributes(request, includeStackTrace),
        	includeStackTrace
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * Returns the error.
     * @param request
     * @param includeStackTrace
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}

/*
@RequestMapping
  public Map<String, Object> error(HttpServletRequest aRequest){
     Map<String, Object> body = getErrorAttributes(aRequest,getTraceParameter(aRequest));
     String trace = (String) body.get("trace");
     if(trace != null){
       String[] lines = trace.split("\n\t");
       body.put("trace", lines);
     }
     return body;
  }

  private boolean getTraceParameter(HttpServletRequest request) {
    String parameter = request.getParameter("trace");
    if (parameter == null) {
        return false;
    }
    return !"false".equals(parameter.toLowerCase());
  }
  
  also see:
  
 
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
To switch it off you can set server.error.whitelabel.enabled=false

Spring Boot installs a ‘whitelabel’ error page that you will see in browser client if you encounter a server error 
(machine clients consuming JSON and other media types should see a sensible response with the right error code).
[Note]

Set server.error.whitelabel.enabled=false to switch the default error page off which will restore the default of the 
servlet container that you are using. Note that Spring Boot will still attempt to resolve the error view so you’d 
probably add you own error page rather than disabling it completely.

Overriding the error page with your own depends on the templating technology that you are using. For example, if you
 are using Thymeleaf you would add an error.html template and if you are using FreeMarker you would add an error.ftl 
 template. In general what you need is a View that resolves with a name of error, and/or a @Controller that handles 
 the /error path. Unless you replaced some of the default configuration you should find a BeanNameViewResolver in 
 your ApplicationContext so a @Bean with id error would be a simple way of doing that. Look at ErrorMvcAutoConfiguration 
 for more options.

See also the section on Error Handling for details of how to register handlers in the servlet container

*/