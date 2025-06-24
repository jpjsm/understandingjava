package com.example.restservice;

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

	public void setQuote(String author, String text) {
		this.author = (author == null || author.trim().isEmpty()) 
			? "" 
			: text.trim();
		
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
			   "]\n\t[Author " +
			   author          +
			   "]";
	}
}