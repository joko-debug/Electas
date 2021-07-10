package com.electas.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LandingController {

	 @GetMapping("/")
	  public String rootView () {
	    return "landing_page";
	  }
}
