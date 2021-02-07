package com.hoho.java_bigdata;

import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl {
	
	public String melon() throws Exception {
		String url = "https://www.melon.com/song/detail.htm?songId=33239419";
		
		// 멜론 사이트의 HTML 태그들을 담을 doc
		Document doc = null;
		
		// 멜론 사이트 접속
		doc = Jsoup.connect(url).get();
		Elements titleArea = doc.select("div.song_name"); //노래 제목 태그지정
		String titleInit = titleArea.text().trim(); //노래제목의 앞뒤공백 제거
		String title = titleInit.substring(3, titleInit.length()); // 노래 제목 앞에 붙는 '곡명' 단어 제거
		Elements singArea = doc.select("div.lyric"); // 노래 가사 태그 지정
		String lyric = singArea.text();
		
		System.out.println("title: "+title);
		System.out.println("lyric: "+lyric);					
		return lyric;
	}
	
	public String melon_rank() throws Exception {
		String chart_url = "https://www.melon.com/chart/index.htm";
		
		int rank_cnt = 1;
		int top = 5; //원하는 순위까지 출력하기
		Document doc = null;
		doc = Jsoup.connect(chart_url).get();		
		Elements element = doc.select("form#frm tbody");		
		
		Iterator<Element> song_rank = element.select("tr").iterator(); //이둘이 세
		
		while(song_rank.hasNext()) {
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
			System.out.println("rank: "+ rank_cnt);
			System.out.println("title: "+title);
			System.out.println("lyric: "+lyric);	
			System.out.println("------------------------------------");
			
			rank_cnt ++;
		}
			
//		for (int i = 1; i <= 5; i++) {
//			Document songdoc = null;
//			
//			// 멜론 사이트 접속
//			songdoc = Jsoup.connect(song_url).get();
////			Elements element = doc.select("div.service_list_song type02 no_rank d_song_list"); //노래 제목 태그지정
//			String titleInit = titleArea.text().trim(); //노래제목의 앞뒤공백 제거
//			String title = titleInit.substring(3, titleInit.length()); // 노래 제목 앞에 붙는 '곡명' 단어 제거
//			Elements singArea = doc.select("div.lyric"); // 노래 가사 태그 지정
//			String lyric = singArea.text();
//			
//			System.out.println("title: "+title);
//			System.out.println("lyric: "+lyric);	
//		}
		// 멜론 사이트의 HTML 태그들을 담을 doc
//		while()
						
		return "hi";
	}
	
	public void movie_rank() throws Exception{
		String url = "http://www.cgv.co.kr/movies/";
		Document doc = null;
		doc = Jsoup.connect(url).get();
		Elements element = doc.select("div.sect-movie-chart");
		Iterator<Element> movie_rank = element.select("strong.rank").iterator();
		Iterator<Element> movie_name = element.select("strong.title").iterator();
		Iterator<Element> movie_reserve = element.select("strong.percent span").iterator();
		
		while(movie_rank.hasNext()) {
			System.out.println("영화 순위: " + movie_rank.next().text());
			System.out.println("영화 제목: " + movie_name.next().text());
			System.out.println("영화 예매: " + movie_reserve.next().text());
			System.out.println("------------------------------------------------");
		}
		
	}

}
