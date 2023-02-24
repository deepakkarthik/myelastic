package com.test.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class Retriveal {

	public static void main(String[] args) throws ElasticsearchException, IOException {
		// TODO Auto-generated method stub

		// Create the low-level client
		// RestClient restClient = RestClient.builder(new HttpHost("localhost",
		// 9200)).build();
		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

		// Create the transport with a Jackson mapper
		ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

		// And create the API client
		ElasticsearchClient esClient = new ElasticsearchClient(transport);

		
		GetResponse<Product> response = esClient.get(g -> g
			    .index("products") 
			    .id("bk-2"),
			    Product.class      
			);

			if (response.found()) {
			    Product product = response.source();
			    System.out.println("Product name========> " + product);
			    
			    System.out.println("Product name " + product.getName());
			} else {
				System.out.println("Product not found");
			}
		
		
	}

}
