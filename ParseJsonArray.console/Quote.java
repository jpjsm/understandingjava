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

	public String getAuthor() {
		return author;
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