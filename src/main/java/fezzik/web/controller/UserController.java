package fezzik.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fezzik.domain.User;
import fezzik.exception.InvalidPasswordException;
import fezzik.exception.UserNotFoundException;
import fezzik.service.UserService;
import fezzik.web.controller.model.FezzikResponse;
import fezzik.web.controller.model.UserCredentials;

@RestController
@ExposesResourceFor(UserController.class)
@RequestMapping("/users2")
public class UserController implements ResourceProcessor<RepositoryLinksResource> {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * Returns all users.
	 * @return
	 */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * Returns single user.
	 * @return
	 */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    public User getUser(@PathVariable String userId) {
    	// TODO: figure out what to do about the usernotfoundexception, @exceptionhandler, we want 404 not found
	
        return userService.getUser(userId);
    }
    
    /**
     * For any controller method that throws UserNotFoundException, cause it to return a 200 status code with the error message.
     * @param response
     * @param e
     * @return
     * @throws IOException
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    private FezzikResponse handleUserNotFoundException(HttpServletResponse response, UserNotFoundException e) throws IOException {
    	return new FezzikResponse(e.getMessage());
    }
    
	/**
	 * Validates a login.
	 * @return
	 */
    @RequestMapping(value = "/validate-login", method = RequestMethod.POST, produces = "application/json")
    public FezzikResponse isValidLogin(@RequestBody UserCredentials userCredentials) {
    	// TODO: figure out how to give good bad request message
    	FezzikResponse response = new FezzikResponse(); // default to success
    	
    	try {
    		boolean validLogin = userService.isValidLogin(userCredentials.getUserId(), userCredentials.getPassword());
    		if (!validLogin) {
    			response = new FezzikResponse("isValidLogin returned false; THERE IS NO REASON RIGHT AT THIS MOMENT THAT THIS SHOULD OCCUR!");
    		}
    	} catch (UserNotFoundException | InvalidPasswordException | IllegalArgumentException e) {
    		response = new FezzikResponse(e.getMessage());
    	} 
    	
        return response;
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(UserController.class).withRel("users2"));

        return resource;
    }
}
