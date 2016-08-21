package fezzik.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

/**
 * The index of the web application should redirect to the swagger documentation.
 */
@Controller
@ApiIgnore // tell swagger to ignore this controller
public class HomeController {
	
	/**
	 * Send the root context to the swagger documentation by default.
	 */
    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}