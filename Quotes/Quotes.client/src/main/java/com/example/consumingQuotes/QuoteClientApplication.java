package com.example.consumingQuotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.temporal.Temporal;
import java.time.temporal.ChronoUnit;
import java.util.Random;


@SpringBootApplication
public class QuoteClientApplication {

	private static final Logger log = LoggerFactory.getLogger(QuoteClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(QuoteClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Random r = new Random();
			char[] letters = null;
			String author = null;
			Quote quote = null;
			Quote[] quotes = null;
			String url = null;
			ResponseEntity<Quote[]> response = null;
			while(true) {
				url = "http://localhost:10007/quote";
				quote = restTemplate.getForObject( url, Quote.class);
				log.info("Query: " + url);
				log.info("Response:" + quote.toString());
				Thread.sleep(50);

				letters = new char[]{ (char)('a'+ r.nextInt(26)), (char)('a'+ r.nextInt(26)) };
				author = new String(letters);
				url = "http://localhost:10007/quote?author=" + author;
				quote = restTemplate.getForObject( url, Quote.class);
				log.info("Query: " + url);
				log.info("Response:" + quote.toString());
				Thread.sleep(50);

				url = "http://localhost:10007/quotes";
				response = restTemplate.getForEntity( url, Quote[].class);
				quotes = response.getBody();
				log.info("Query: " + url);
				log.info("Response: (" + Integer.toString(quotes.length) + " quotes)");
				for (int j = 0; j < quotes.length; j++){
					log.info(Integer.toString(j) + " --> " + quotes[j].toString());
				}
				Thread.sleep(50);

				letters = new char[]{ (char)('a'+ r.nextInt(26)), (char)('a'+ r.nextInt(26)) };
				author = new String(letters);
				url = "http://localhost:10007/quotes?author=" + author;
				response = restTemplate.getForEntity( url, Quote[].class);
				quotes = response.getBody();
				log.info("Query: " + url);
				log.info("Response: (" + Integer.toString(quotes.length) + " quotes)");
				for (int j = 0; j < quotes.length; j++){
					log.info(Integer.toString(j) + " --> " + quotes[j].toString());
				}
				Thread.sleep(50);
			}
		};
	}
}
