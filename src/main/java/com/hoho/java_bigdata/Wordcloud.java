package com.hoho.java_bigdata;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Wordcloud {
	public static Map<String, Integer> SongForWordCloud() throws Exception{
		DB mc = new DB();
		mc.DBMelon();
		
		List<Map<String,Object>> rList = mc.select_melon();
		Iterator<Map<String, Object>> it = rList.iterator();
		
		String song = "";
		
		while(it.hasNext()) {
			Map<String, Object> rMap = it.next();
			
			List<Map<String, Object>> rankList = (List<Map<String, Object>>)rMap.get("rank_info");
			
			Iterator<Map<String, Object>> mIt = rankList.iterator();
			
			while(mIt.hasNext()) {
				Map<String, Object> mMap = mIt.next();
				song += mMap.get("sing") + " ";
			}
			 
		}
		String[] SingArr = song.split(" ");		
		
		HashMap<String, Integer> word = new HashMap<String,Integer>();
		
		for (int i = 0; i < SingArr.length; i++) {
			if(word.containsKey(SingArr[i])) {
				word.put(SingArr[i], (int)word.get(SingArr[i])+1);
			} else {
				word.put(SingArr[i], 1);
			}
		}
		return word;
	}

}
