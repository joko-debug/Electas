package com.electas.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.electas.domain.User;


@Controller
public class NavigationController {

	@GetMapping("/nav")
	public String homeView(@AuthenticationPrincipal User user, ModelMap model) {
		return "nav";
	}
	
	
	 @GetMapping("/nav/home")
	    public String navhome() {
		 //ModelAndView mv= new ModelAndView("search::search_list"); 
	        return "fragments/navigation::home";
	    }
	 @GetMapping("/nav/election")
	    public String navelection() {
		 //ModelAndView mv= new ModelAndView("search::search_list"); 
	        return "fragments/navigation::election";
	    }
	 @GetMapping("/nav/profile")
	    public String navprofile() {
		 //ModelAndView mv= new ModelAndView("search::search_list"); 
	        return "fragments/navigation::profile";
	    }
	 @GetMapping("/nav/help")
	    public String navhelp() {
		 //ModelAndView mv= new ModelAndView("search::search_list"); 
	        return "fragments/navigation::help";
	    }

	    @RequestMapping("/content2")
	    public String getContent2() {
	        return "fragments/navigation :: content2";
	    }

}
