package com.hoho.java_bigdata;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
	
	@RequestMapping(value = "/crawl_melon", method = RequestMethod.GET)
	public String crawl_melon(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Crawl crawling = new Crawl();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DB melonDB = new DB();
			melonDB.DBMelon(); //DB접속하기						
			result = crawling.melon_rank();			
			melonDB.insert_melon(result);	
			model.addAttribute("result", result );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main";
	}
	
	@RequestMapping(value = "/crawl_cgv", method = RequestMethod.GET)
	public String crawl_cgv(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Crawl crawling = new Crawl();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DB cgvDB = new DB();
			cgvDB.DBCgv(); //DB접속하기
			result = crawling.movie_rank();
			cgvDB.insert_cgv(result);
			System.out.println(result.get("rank_info"));
			System.out.println(result.get("rank_info").getClass().getName());
			model.addAttribute("result", result.get("rank_info") );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main";
	}
	
	@RequestMapping(value = "/select_melon", method = RequestMethod.GET)
	public String select_melon(Locale locale, Model model) {		
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			DB melonDB = new DB();
			melonDB.DBMelon(); //DB접속하기
			List<Map<String, Object>> rList = melonDB.select_melon();
			Iterator<Map<String, Object>> it = rList.iterator();
			
			while(it.hasNext()) {
				Map<String, Object> rMap = it.next();
				System.out.println("-------------------------");
				System.out.println("time: " + rMap.get("time"));
				
				List<Map<String,Object>> rankList = (List<Map<String, Object>>)rMap.get("rank_info");
				Iterator<Map<String, Object>> mlt = rankList.iterator();
				
				while(mlt.hasNext()) {
					System.out.println("-------------------------");
					Map<String, Object> mMap = mlt.next();
					System.out.println("타이틀: "+ mMap.get("title"));
					System.out.println("가사: "+ mMap.get("sing"));					
				}
			}
			
			
			model.addAttribute("result", result.get("rank_info") );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main";
	}
	
	@RequestMapping(value = "/select_cgv", method = RequestMethod.GET)
	public String select_cgv(Locale locale, Model model) {		
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			DB cgvDB = new DB();
			cgvDB.DBCgv(); //DB접속하기
			List<Map<String, Object>> rList = cgvDB.select_cgv();
			Iterator<Map<String, Object>> it = rList.iterator();
			
			while(it.hasNext()) {
				Map<String, Object> rMap = it.next();
				System.out.println("-------------------------");
				System.out.println("time: " + rMap.get("time"));
				
				List<Map<String,Object>> rankList = (List<Map<String, Object>>)rMap.get("rank_info");
				Iterator<Map<String, Object>> mlt = rankList.iterator();
				
				while(mlt.hasNext()) {
					System.out.println("-------------------------");
					Map<String, Object> mMap = mlt.next();
					System.out.println("순위: "+ mMap.get("movie_rank"));
					System.out.println("제목: "+ mMap.get("movie_name"));
					System.out.println("예매: "+ mMap.get("movie_reserve"));
				}
			}
			
			
			model.addAttribute("result", result.get("rank_info") );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "main";
	}
	
	@RequestMapping(value = "/word", method = RequestMethod.GET)
	public String word(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "word";
	}
	
}
