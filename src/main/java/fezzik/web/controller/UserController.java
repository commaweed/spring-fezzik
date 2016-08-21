package fezzik.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fezzik.domain.User;
import fezzik.service.UserService;
import fezzik.web.controller.model.FezzikResponse;
import fezzik.web.controller.model.UserCredentials;

@RestController
@ExposesResourceFor(UserController.class)
@RequestMapping("/rest")
//TODO: figure out how to give good bad request message for bad post body (USE Matt's AOP idea)
public class UserController implements ResourceProcessor<RepositoryLinksResource> {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	public FezzikResponse addUser(@RequestBody User user) {
		userService.addUser(user);
		return FezzikResponse.getSuccessResponse("Added user with ID [" + user.getUuid() + "]");
	}
	
	/**
	 * Returns all users.
	 * @return
	 */
    @RequestMapping(value = "/user/all", method = RequestMethod.GET, produces = "application/json")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * Returns single user.
	 * @return
	 */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
    public User getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
    
	/**
	 * Validates a login.
	 * @return
	 */
    @RequestMapping(value = "/user/validate-login", method = RequestMethod.POST, produces = "application/json")
    public FezzikResponse isValidLogin(@RequestBody UserCredentials userCredentials) {
    	FezzikResponse response = FezzikResponse.getSuccessResponse(null); // default to success
    	
		boolean validLogin = userService.isValidLogin(userCredentials.getUserId(), userCredentials.getPassword());
		if (!validLogin) {
			response = FezzikResponse.getFailureResponse(
				"isValidLogin returned false; THERE IS NO REASON RIGHT AT THIS MOMENT THAT THIS SHOULD OCCUR!"
			);
		}
    	
        return response;
    }

    /**
     * um...
     */
    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(UserController.class).withRel("rest"));
        return resource;
    }
}
