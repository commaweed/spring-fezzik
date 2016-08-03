package fezzik.component.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the sample application home page (perhaps to be the real home page - not sure what front-end
 * to use yet - also not sure if we should include tiles)
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		String formattedDate = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss").format(LocalDateTime.now());
		model.addAttribute("serverTime", formattedDate );	
		return "home";
	}
	
}
