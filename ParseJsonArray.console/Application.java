public class Application {
    // Programs entry point is a call to main()
    public static void main(String args[])  throws Throwable {
        for (int index = 0; index < 10; index++) {
            System.out.println(Quotes.getQuote());
        }
    }    
}
