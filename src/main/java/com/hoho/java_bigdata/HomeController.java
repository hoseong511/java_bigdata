package com.hoho.java_bigdata;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/ctest", method = RequestMethod.GET)
	public String crawling(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Crawl crawling = new Crawl();
		try {
//			String result = crawling.melon();
//			model.addAttribute("result", result );
			crawling.melon_rank();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main";
	}
	
	@RequestMapping(value = "/movie_crawl", method = RequestMethod.GET)
	public String movie_crawl(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Crawl crawling = new Crawl();
		try {
			crawling.movie_rank();
//			model.addAttribute("result", result );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main";
	}
	
}
