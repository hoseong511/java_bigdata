package com.hoho.java_bigdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class DB {
	MongoDatabase database;
	MongoClient mongoClient;
	
	public void DBMelon() {
		mongoClient = MongoClients.create("mongodb://0.0.0.0:27017");
		database = mongoClient.getDatabase("bigdata_melon");
	}
	
	public void DBCgv() {
		mongoClient = MongoClients.create("mongodb://0.0.0.0:27017");
		database = mongoClient.getDatabase("bigdata_CGV");
	}
	
	public void insert_melon(Map<String, Object>data) throws Exception {
		MongoCollection<Document>collection = database.getCollection("Sing");
		Document document = new Document();				
		document.put("time", data.get("time"));
		document.put("rank_info", data.get("rank_info"));
		collection.insertOne(document);
	}
	
	public void insert_cgv(Map<String, Object>data) throws Exception {
		MongoCollection<Document>collection = database.getCollection("MovieChart");
		Document document = new Document();
		document.put("time", data.get("time"));
		document.put("rank_info", data.get("rank_info"));
		collection.insertOne(document);
	}
	
	public List<Map<String, Object>> select_melon() throws Exception{
		
		List<Map<String, Object>> selectResult = new ArrayList<Map<String, Object>>();
		MongoCollection<Document> collection = database.getCollection("Sing");
		FindIterable<Document> result = collection.find(new Document());
		
		for(Document doc: result) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("time", (String)doc.get("time"));
			map.put("rank_info", (Object)doc.get("rank_info"));
			selectResult.add(map);
		}
		
		
		return selectResult;
	}
	
	public List<Map<String, Object>> select_cgv() throws Exception{
		List<Map<String, Object>> selectResult = new ArrayList<Map<String, Object>>();
		MongoCollection<Document> collection = database.getCollection("MovieChart");
		FindIterable<Document> result = collection.find(new Document());
		
		for(Document doc: result) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("time", (String)doc.get("time"));
			map.put("rank_info", (Object)doc.get("rank_info"));
			selectResult.add(map);
		}
		
		return selectResult;
	}

}
