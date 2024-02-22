package com.ifour.controller;

import com.ifour.stereotypes.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	
	// Logger
	private static final Logger LOG = LoggerFactory
			.getLogger(IndexController.class);

	@Autowired
	private MetadataManager metadata;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		return "index";
	}

	@RequestMapping("/new")
	public String landing(@CurrentUser User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null)
			LOG.debug("Current authentication instance from security context is null");
		else
			LOG.debug("Current authentication instance from security context: "
					+ this.getClass().getSimpleName());
		model.addAttribute("username", 	user.getUsername());
		return "new";
	}

	/*@GetMapping("/saml/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		new SecurityContextLogoutHandler().logout(request, null, SecurityContextHolder.getContext().getAuthentication());
		SecurityContextHolder.getContext().setAuthentication(null);
		request.logout();
		//return "redirect:/"; // Redirect to the home page or any other page after logout
	}*/

}
