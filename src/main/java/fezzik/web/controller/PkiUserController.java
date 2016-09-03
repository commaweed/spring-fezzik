package fezzik.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fezzik.domain.PkiUser;
import fezzik.service.PkiUserService;
import fezzik.web.controller.model.FezzikResponse;

@RestController
@ExposesResourceFor(PkiUserController.class)
@RequestMapping("/rest")
@CrossOrigin
@Profile("pki")
public class PkiUserController implements ResourceProcessor<RepositoryLinksResource> {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(PkiUserController.class);

	@Autowired
	private PkiUserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	public FezzikResponse addUser(@RequestBody PkiUser user) {
		userService.addUser(user);
		return FezzikResponse.getSuccessResponse("Added user with ID [" + user.getUuid() + "]");
	}
	
	/**
	 * Returns all users.
	 * @return
	 */
    @RequestMapping(value = "/user/all", method = RequestMethod.GET, produces = "application/json")
	public List<PkiUser> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * Returns single user.
	 * @return
	 */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
    public PkiUser getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }
    
	/**
	 * Returns information about the user that has currently logged into the application.
	 * @return
	 */
    @RequestMapping(value = "/user/current", method = RequestMethod.GET, produces = "application/json")
    public PkiUser getUser() {
        return userService.getRequestUser();
    }

    /**
     * um...
     */
    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(PkiUserController.class).withRel("rest"));
        return resource;
    }
}
