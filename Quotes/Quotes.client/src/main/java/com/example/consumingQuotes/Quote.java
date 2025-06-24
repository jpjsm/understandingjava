package com.example.consumingQuotes;

public class Quote {

	private String author;
	private String quote;

	public Quote(String quote, String author) {
		this.quote = quote;
		this.author = author;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String text) {
		this.quote = (text == null || text.trim().isEmpty()) 
			? "" 
			: text.trim();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String name) {
		this.quote = (name == null || name.trim().isEmpty()) 
			? "" 
			: name.trim();
	}

	@Override
	public String toString(){
		return "[Quote: "      +
		       quote           +
			   "; Author: " +
			   author          +
			   "]";
	}
}