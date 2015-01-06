package com.ricardovz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinaAPP {
	static final Logger logger = LoggerFactory.getLogger(LinaAPP.class);
	
	@RequestMapping(value = "/greetings", method = RequestMethod.GET)
	public Text greetings() {
		return new Text("Te quiero Liniukas");
	}
}
