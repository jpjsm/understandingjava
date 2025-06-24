package com.example.restservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {


	@GetMapping(value = "/quote")
	public Quote quote(@RequestParam(value = "author", defaultValue = "") String author) {
		//System.out.println("[Information @ QuoteController.quote] Author: " + author);
		try {
			if (author == null || author.trim().length() == 0 ) {
				return Quotes.getQuote();
			}

			author = author.trim();	
			return Quotes.getQuoteFrom(author);
		} 
		catch (Throwable e) {
			System.out.println("[Throwable exception @ QuoteController.quote] Message: " + e.getMessage());
			System.out.println("[Throwable exception @ QuoteController.quote] Cause  : ");
			StackTraceElement[] stackTraceElements =  e.getStackTrace();

			StringBuilder elements = new StringBuilder();

			elements.append("===================================================================================================================================\n");
			for (int index = 0; index < stackTraceElements.length; index++) {
				elements.append(
					"[" + 
					stackTraceElements[index].getFileName() +
					"] " +
					stackTraceElements[index].getClassName() +
					"." +
					stackTraceElements[index].getMethodName() +
					":" +
					Integer.toString(stackTraceElements[index].getLineNumber()) +
					"\n"
				);
			}

			elements.append("===================================================================================================================================\n");

			System.out.println(elements.toString());

			return new Quote(elements.toString(), e.getMessage());
		}

	}

	@GetMapping(value = "/quotes")
	public Quote[] quotes(@RequestParam(value = "author", defaultValue = "") String author){
		//System.out.println("[Information @ QuoteController.quote] Author: " + author);
		try {
			if (author == null || author.trim().length() == 0 ) {
				return Quotes.getQuotes();
			}

			author = author.trim();	
			return Quotes.getQuotesFrom(author);
		} 
		catch (Throwable e) {
			System.out.println("[Throwable exception @ QuoteController.quote] Message: " + e.getMessage());
			System.out.println("[Throwable exception @ QuoteController.quote] Cause  : ");
			StackTraceElement[] stackTraceElements =  e.getStackTrace();

			StringBuilder elements = new StringBuilder();

			elements.append("===================================================================================================================================\n");
			for (int index = 0; index < stackTraceElements.length; index++) {
				elements.append(
					"[" + 
					stackTraceElements[index].getFileName() +
					"] " +
					stackTraceElements[index].getClassName() +
					"." +
					stackTraceElements[index].getMethodName() +
					":" +
					Integer.toString(stackTraceElements[index].getLineNumber()) +
					"\n"
				);
			}

			elements.append("===================================================================================================================================\n");

			System.out.println(elements.toString());

			//return new Quote[]{ new Quote("", "#No author provided#")};
			String stackTraces = elements.toString();
			String errorMessage = e.getMessage();
			return new Quote[]{ new Quote(stackTraces, errorMessage)};
		}

	}

	@GetMapping(value = "/author")
	public Quote[] authorQuotes(@RequestParam(value = "author", defaultValue = "") String author){
		Quote[] results = new Quote[0];

		return results;
	}

}