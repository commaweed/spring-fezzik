package fezzik.component.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fezzik.component.repository.FezzikUserRepository;

/**
 * Handles requests for the sample application home page (perhaps to be the real home page - not sure what front-end
 * to use yet - also not sure if we should include tiles)
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private FezzikUserRepository fezzikUserRepo;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
//		fezzikUserRepo.insert(new FezzikUser("bob jarvis")); //obviously this test example will keep inserting	
		logger.info("fezzik users: " + fezzikUserRepo.findAll());
		
		String formattedDate = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss").format(LocalDateTime.now());
		model.addAttribute("serverTime", formattedDate );	
		return "home";
	}
	
	/**
	 * logout - refactor into a separate class?? well it will be a rest service so this won't be necessary anyways
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    if (auth != null) {    
	    	// even logging out doesn't appear to work :(
	    	logger.info("loging out user: " + auth.getName());
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    
	    return "redirect:/";
		//return "welcome";
	}
}
