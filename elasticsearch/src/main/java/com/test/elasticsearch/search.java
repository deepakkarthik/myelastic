package com.test.elasticsearch;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class search {

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

		String searchText = "bike";

		SearchResponse<Product> response = esClient.search(
				s -> s.index("products").query(q -> q.match(t -> t.field("name").query(searchText))), Product.class);

		TotalHits total = response.hits().total();
		boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

		if (isExactResult) {
			System.out.println("There are " + total.value() + " results");
		} else {
			System.out.println("There are more than " + total.value() + " results");
		}

		List<Hit<Product>> hits = response.hits().hits();
		for (Hit<Product> hit : hits) {
			Product product = hit.source();
			System.out.println("the value of the product==============>"+product);
			System.out.println("Found product " + product.getId() + ", score " + hit.score());
		}

	}
}
