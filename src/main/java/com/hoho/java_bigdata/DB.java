package com.hoho.java_bigdata;

import java.util.Map;
import org.bson.Document;

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

}
