package com.hoho.java_bigdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawl {	
	
	public Map<String, Object> melon_rank() throws Exception {
		String chart_url = "https://www.melon.com/chart/index.htm";		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");		
		
		int rank_cnt = 1;
		int top = 50; //원하는 순위까지 출력하기	
		
		Document doc = null;
		doc = Jsoup.connect(chart_url).get();		
		Elements element = doc.select("form#frm tbody");		
		
		Iterator<Element> song_rank = element.select("tr").iterator(); //이둘이 세
		
		Map<String, Object> gMap = new HashMap<String, Object>();
		Map<String, String> rMap = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		
		while(song_rank.hasNext()) {
			rMap = new HashMap<String, String>();
			String song_url = "https://www.melon.com/song/detail.htm?songId=";
			doc = null;
			
			if (rank_cnt==top+1) {
				break;
			}
			
//			System.out.println(song_rank.next().select("div.wrap a"));
//			System.out.println(song_rank.next().attr("abs:data-song-no")); // href의 링를 출력할때 용https://www.melon.com/chart/33223396
			String song_no = song_rank.next().attr("data-song-no");
			System.out.println(song_no); //속성의 값만 출력
			
			song_url = song_url + song_no;
			
			doc = Jsoup.connect(song_url).get();			
			Elements titleArea = doc.select("div.song_name"); //노래 제목 태그지정
			String titleInit = titleArea.text().trim(); //노래제목의 앞뒤공백 제거
			String title = titleInit.substring(3, titleInit.length()); // 노래 제목 앞에 붙는 '곡명' 단어 제거			
			Elements singArea = doc.select("div.lyric"); // 노래 가사 태그 지정
			String lyric = singArea.text();						
			
			rMap.put("title", title);
			rMap.put("sing", lyric);
			list.add(rMap);			
			
			rank_cnt ++;
		}
		
		gMap.put("time", dateFormat.format(date));
		gMap.put("rank_info", list);
		System.out.println(gMap);
		
		
						
		return gMap;
	}
	
	public Map<String, Object> movie_rank() throws Exception{
		String url = "http://www.cgv.co.kr/movies/";
		Document doc = null;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");		
		
		
		doc = Jsoup.connect(url).get();
		Elements element = doc.select("div.sect-movie-chart");
		Iterator<Element> movie_rank = element.select("strong.rank").iterator();
		Iterator<Element> movie_name = element.select("strong.title").iterator();
		Iterator<Element> movie_reserve = element.select("strong.percent span").iterator();
		
		Map<String, Object> gMap = new HashMap<String, Object>();
		Map<String, String> rMap = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
				
		while(movie_rank.hasNext()) {
			rMap = new HashMap<String, String>();
			
			rMap.put("movie_rank", movie_rank.next().text());
			rMap.put("movie_name", movie_name.next().text());
			rMap.put("movie_reserve", movie_reserve.next().text());			
			
			list.add(rMap);
		}
		
		gMap.put("time", dateFormat.format(date));
		gMap.put("rank_info", list);
		System.out.println(gMap);		
		
		return gMap;
	}

}
