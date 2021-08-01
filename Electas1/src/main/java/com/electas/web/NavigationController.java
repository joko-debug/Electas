package com.electas.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.electas.domain.User;

@Controller
public class NavigationController {

	@GetMapping("/nav")
	public String homeView(User user, ModelMap model) {
		return "nav";
	}
	
	
	 @GetMapping("/nav/home")
	    public String getContent1() {
		 //ModelAndView mv= new ModelAndView("search::search_list"); 
	        return "fragments/navigation::home";
	    }

	    @RequestMapping("/content2")
	    public String getContent2() {
	        return "fragments/navigation :: content2";
	    }

}
