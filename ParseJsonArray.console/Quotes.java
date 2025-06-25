import java.util.Random;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;


public class Quotes {
    private static ArrayList<Quote> quotes = null;
    private static Random rnd = new Random();

    public static void InitQuotes() throws Throwable {
		if (quotes == null) {
            Path filePath = Paths.get("./Quotes.json");
            StringBuilder contentBuilder = new StringBuilder();
            
            try (Stream<String> stream  = Files.lines(filePath.toAbsolutePath(), StandardCharsets.UTF_8))
            {
              //Read the content with Stream
              stream.forEach(s -> contentBuilder.append(s));
            }
            catch (IOException e)
            {
              e.printStackTrace();
              throw e;
            }
            
            quotes = new ArrayList<Quote>();
            String jsonString = contentBuilder.toString();

            JSONArray jarr = new JSONArray(jsonString);

            for (int index = 0; index < jarr.length(); index++) {
                JSONObject jo = jarr.getJSONObject(index);
                Quote q = new Quote(jo.getString("quote"), jo.getString("author"));
                quotes.add(q);
            }
        }        
    }

    public Quotes() throws Throwable {
        Quotes.InitQuotes();
    }

    public static Quote getQuote() throws Throwable {
        if (quotes == null) {
            Quotes.InitQuotes();
        } 

        return quotes.get(rnd.nextInt(quotes.size()));
    }

}
