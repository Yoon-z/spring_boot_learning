package com.springboot1.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private AuthenticationService authenticationService;

	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@RequestMapping()
	// http://localhost:8080/login?name=
	public String gotoLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name);
		logger.debug("Request param is " + name);
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	// http://localhost:8080/login
	public String gotoLoginPage() {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	// http://localhost:8080/login?name=
	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
		model.put("name", name);
		// model.put("password", password);
		if (authenticationService.authenticate(name, password)) {
			return "welcome";
		}
		return "login";
	}
}
