package com.test.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class TestConnection2 {

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

		Product product = new Product("bk-6", " bike", "350");

		IndexResponse response = esClient.index(i -> i.index("products").id(product.getId()).document(product));
		System.out.println("Indexed with version " + response.version());
		
		System.out.println("result" + response.result());

		System.out.println("id" + response.id());
		
		System.out.println("index" + response.index());
		
		
		System.out.println("primaryterm" + response.primaryTerm());
//		SearchResponse<Product> search = esClient.search(s -> s.index("products")
//			    .query(q -> q
//			        .term(t -> t
//			            .field("name")
//			            .value(v -> v.stringValue("bicycle"))
//			        )),
//			    Product.class);
//
//			for (Hit<Product> hit: search.hits().hits()) {
//			    System.out.println("+++==>"+hit);
//			}

	}

}
