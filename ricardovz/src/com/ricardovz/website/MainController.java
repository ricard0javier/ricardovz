package com.ricardovz.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String any(@PathVariable String page) {
		System.out.println(page);
		return "redirect:/";
	}

}
