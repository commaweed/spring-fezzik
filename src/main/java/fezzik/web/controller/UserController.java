package fezzik.web.controller;

import fezzik.domain.User;
import fezzik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ExposesResourceFor(UserController.class)
@RequestMapping("/users2")
public class UserController implements ResourceProcessor<RepositoryLinksResource> {

	@Autowired
	private UserService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<User> getAll() {
		return service.get();
	}

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = "application/json")
    public List<User> get() {
        return service.get();
    }

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
        resource.add(ControllerLinkBuilder.linkTo(UserController.class).withRel("users2"));

        return resource;
    }
}
