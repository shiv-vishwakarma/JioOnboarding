package com.jio.elasticsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.flexible.core.builders.QueryBuilder;
import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.client.*;
import org.elasticsearch.*;

import joptsimple.internal.Strings;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class ElasticSnippet {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		final Logger logger = Logger.getLogger(ElasticSnippet.class);

		Settings settings = Settings.builder().put("cluster.name", "mano_cluster").build();
		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.32.140.142"), 9302));

		// Preparing the Index and Type
		IndexResponse response1 = client.prepareIndex("reliancejio", "department", "1")
				.setSource(jsonBuilder().startObject()
						.field("departmentName", "accounts")
						.field("totalEmployee", 100)
						.field("departmentLocation ", "1st floor")
						.field("departmentLogoName", "accountlogo")
						.endObject())
				.get();
		logger.info("Document 1 added successfully !!");

		IndexResponse response2 = client.prepareIndex("reliancejio", "department", "2")
				.setSource(jsonBuilder().startObject()
						.field("departmentName", "admin")
						.field("totalEmployee", 50)
						.field("departmentLocation ", "2nd floor")
						.field("departmentLogoName", "adminlogo")
						.endObject())
				.get();
		logger.info("Document 2 added successfully !!");

		IndexResponse response3 = client.prepareIndex("reliancejio", "department", "3")
				.setSource(jsonBuilder().startObject()
						.field("departmentName", "transport")
						.field("totalEmployee", 20)
						.field("departmentLocation ", "3rd floor")
						.field("departmentLogoName", "transportlogo")
						.endObject())
				.get();
		logger.info("Document 3 added successfully !!");

		// Storing a document without an ID
		IndexResponse response4 = client.prepareIndex("reliancejio", "department")
				.setSource(jsonBuilder().startObject()
						.field("departmentName", "technical")
						.field("totalEmployee", 500)
						.field("departmentLocation ", "4th floor")
						.field("departmentLogoName", "technicallogo")
						.endObject())
				.get();
		logger.info("Document without ID added successfully !!");
		
		IndexResponse response5 = client.prepareIndex("reliancejio", "department", "4")
				.setSource(jsonBuilder().startObject()
						.field("departmentName", "HR")
						.field("totalEmployee", 50)
						.field("departmentLocation ", "5th floor")
						.field("departmentLogoName", "hrlogo")
						.endObject())
				.get();
		logger.info("Document 5 added successfully !!");

		// Fetching one of the document from Index using documentID
		logger.info("Fetching document from the INDEX with ID : 3");
		GetResponse getresponse1 = client.prepareGet("reliancejio", "department", "3").get();
		String json1 = getresponse1.getSourceAsString();
		logger.info("GetResponse getresponse1 as String: " + json1);

		// Fetching one of the document from Index using Auto Generated documentID
		logger.info("Fetching document from the INDEX without ID : " + response4.getId().toString());
		GetResponse getresponse2 = client.prepareGet("reliancejio", "department", response4.getId().toString()).get();
		String json2 = getresponse2.getSourceAsString();
		logger.info("GetResponse getresponse2 as String: " + json2);

		/*if (logger.isDebugEnabled()) {
			logger.debug("GetResponse getresponse1 as Map: " + getresponse1.getSourceAsMap());
			logger.debug("GetResponse getresponse2 as Map: " + getresponse2.getSourceAsMap());
		}*/

		
		/*if (getresponse2.isExists()) {
			DeleteResponse deleteResponse = client.prepareDelete("reliancejio", "department", response4.getId().toString()).get();
			Result deleteJson = deleteResponse.getResult();
			logger.info("Result of Deletion : " + deleteJson);
			logger.info(deleteResponse.toString());
		}
		 
		SearchResponse searchResponse = client.prepareSearch("reliancejio").setTypes("department").setSize(0)
				.setExplain(true).get();
		logger.info("Search Result : " + searchResponse.toString());
		logger.info("Search Result totalHits : " + searchResponse.getHits().getTotalHits());*/

		/*	logger.info("BEFORE UPDATE" + client.prepareGet("reliancejio", "department", "1").get().getSourceAsMap());
		
			UpdateRequest updateRequest = new UpdateRequest("reliancejio", "department", "1")
					.doc(jsonBuilder().startObject().field("totalEmployee", 150).endObject());
			UpdateResponse updateResponse = client.update(updateRequest).get();
			logger.info("UpdateResponse : " + updateResponse);
		
			logger.info("AFTER UPDATE" + client.prepareGet("reliancejio", "department", "1").get().getSourceAsMap());*/
		
		
		/////////////////////////////// SCrolling /////////////////////////////////////////////////////////
		
		/*final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("departmentLogoName", "logo"));
		searchSourceBuilder.size(2); 
		searchRequest.indices("reliancejio").types("department");
		searchRequest.source(searchSourceBuilder);
		searchRequest.scroll(scroll);
		
		QueryBuilder queryBuilder = QueryBuilders.matchQuery("departmentLogoName", "logo");
		
		SearchResponse searchResponse = client.search(searchRequest).execute().actionGet();*/
		SearchResponse searchResponse = client.prepareSearch()
										.setIndices("reliancejio").setTypes("department")
										.setSize(2)
										.setScroll(TimeValue.timeValueMinutes(1L))
										.setQuery(QueryBuilders.matchAllQuery())
										//.setQuery(QueryBuilders.matchQuery("departmentLogoName", "logo"))
										.execute()
										.actionGet();
		
		logger.info("Search Response : " +searchResponse.toString());
			int count = 1;	
			
			String scrollId = searchResponse.getScrollId();
			SearchHit[] searchHits = searchResponse.getHits().getHits();
			
			while (searchHits != null && searchHits.length > 0) { 
				
				logger.info("BATCH "+count+" ==> Scroll ID : "+scrollId);
				
				for(SearchHit hit : searchHits)
				{
					logger.info("Batch "+count+ " ==> : " +hit.getSourceAsString());
				}
				
				logger.info("End of BATCH "+count+" ==> Outside FOR loop");
		    	
				
				searchResponse = client.prepareSearchScroll(scrollId).setScroll(TimeValue.timeValueMinutes(1L)).execute().actionGet();
				scrollId = searchResponse.getScrollId();
			    searchHits = searchResponse.getHits().getHits();
			    count++;
			}
		
			/*do {
			for (SearchHit hit : searchResponse.getHits().getHits()) {
				//logger.info("Inside Inner For Loop");
				logger.info("Batch "+count+ "==> : " +hit.getSourceAsString());
				logger.info("Scroll ID : "+searchResponse.getScrollId());
			}
			logger.info("Outside the Inner For Loop");
			count++;
			
			searchResponse = client.prepareSearchScroll(searchResponse.getScrollId()).setScroll(new TimeValue(100000)).execute().actionGet();
			} while(searchResponse.getHits().getHits().length != 0); // Zero hits mark the end of the scroll and the while loop.
			
			logger.info("Outside the DoWhile Loop");*/
		
		

		

		client.close();
		

	}

}
