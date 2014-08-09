package com.ricardovz.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/{page}.{extension:html}", method = RequestMethod.GET)
	public String getHtml(@PathVariable String page, @PathVariable String extension) {
		return "resources/" + page + "." + extension;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "resources/index.html";
	}

}
