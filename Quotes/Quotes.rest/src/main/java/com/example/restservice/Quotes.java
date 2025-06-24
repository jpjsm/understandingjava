package com.example.restservice;

import java.util.Random;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import com.example.FileSearch.FileSearch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import java.io.File;



public class Quotes {
    private static ArrayList<Quote> quotes = null;
    private static Map<String, ArrayList<Integer>> authorQuotes = null;
    private static Random rnd = new Random();
    private static int defaultQuotesListSize = 5;

    public static int getDefaultQuotesListSize(){
        return defaultQuotesListSize;
    }

    public static void setDefaultQuotesListSize(int size){
        if (size < 1) {
            throw new IllegalArgumentException("'size' must be greater than zero.");
        }

        defaultQuotesListSize = size;
    }

    public static void InitQuotes() throws Throwable {
		if (quotes == null) {
            System.out.println("[Information @ Quotes.InitQuotes] initiated");

            String standardQuotes = "[{\"quote\":\"The secret of getting ahead is getting started\",\"author\":\"Mark Twain\"},{\"quote\":\"The only impossible journey is the one you never begin\",\"author\":\"Tony Robbins\"},{\"quote\":\"The best way to get started is to quit talking and begin doing\",\"author\":\"Walt Disney\"},{\"quote\":\"When you know what you want, and want it bad enough, you'll find a way to get it\",\"author\":\"Jim Rohn\"},{\"quote\":\"Go confidently in the direction of your dreams. Live the life you have imagined\",\"author\":\"Henry David Thoreau\"},{\"quote\":\"Take action. An inch of movement will bring you closer to your goals than a mile of intention\",\"author\":\"Steve Maraboli\"},{\"quote\":\"Never let the fear of striking out keep you from playing the game\",\"author\":\"Babe Ruth\"},{\"quote\":\"Knowing is not enough; we must apply. Wishing is not enough; we must do\",\"author\":\"Johann Wolfgang von Goethe\"},{\"quote\":\"Don't be afraid to give up the good for the great\",\"author\":\"John D. Rockefeller\"},{\"quote\":\"If you genuinely want something, don't wait for it – teach yourself to be impatient\",\"author\":\"Gurbaksh Chahal\"},{\"quote\":\"The most difficult thing is the decision to act; the rest is merely tenacity\",\"author\":\"Amelia Earhart\"},{\"quote\":\"You don't have to be great to start, but you have to start to be great\",\"author\":\"Zig Ziglar\"},{\"quote\":\"You miss 100% of the shots you don't take\",\"author\":\"Wayne Gretzky\"},{\"quote\":\"Twenty years from now, you will be more disappointed by the things that you didn't do than by the ones you did do. So, throw off the bowlines, sail away from the safe harbor, catch the trade winds in your sails. Explore, Dream, Discover.\",\"author\":\"Mark Twain\"},{\"quote\":\"Everything you've ever wanted is on the other side of fear\",\"author\":\"George Addair\"},{\"quote\":\"Decide what you want. Believe you can have it. Believe you deserve it and believe it's possible for you\",\"author\":\"Jack Canfield\"},{\"quote\":\"I believe that the only courage anybody ever needs is the courage to follow your dreams\",\"author\":\"Oprah Winfrey\"},{\"quote\":\"Whatever the mind of man can conceive and believe, it can achieve\",\"author\":\"Napoleon Hill\"},{\"quote\":\"The only limit to our realization of tomorrow will be our doubts of today\",\"author\":\"Franklin D. Roosevelt\"},{\"quote\":\"If you can dream it, you can achieve it. You will get all you want in life if you help enough other people get what they want.\",\"author\":\"Zig Ziglar\"},{\"quote\":\"Whether you think you can or think you can't, you're right\",\"author\":\"Henry Ford\"},{\"quote\":\"Magic is believing in yourself; if you can do that, you can make anything happen\",\"author\":\"Johann Wolfgang von Goethe\"},{\"quote\":\"Believe in yourself. You are braver than you think, more talented than you know, and capable of more than you imagine\",\"author\":\"Roy T. Bennett\"},{\"quote\":\"Believe you can, and you're halfway there\",\"author\":\"Theodore Roosevelt\"},{\"quote\":\"There are no limits to what you can accomplish, except the limits you place on your own thinking\",\"author\":\"Brian Tracy\"},{\"quote\":\"All our dreams will come true if we have the courage to pursue them\",\"author\":\"Walt Disney\"},{\"quote\":\"We generate fears while we sit. We overcome them by action\",\"author\":\"Dr. Henry Link\"},{\"quote\":\"Too many of us are not living our dreams because we are living our fears\",\"author\":\"Les Brown\"},{\"quote\":\"I have learned over the years that when one's mind is made up, this diminishes fear\",\"author\":\"Rosa Parks\"},{\"quote\":\"Believe you can, and you're halfway there\",\"author\":\"Theodore Roosevelt\"},{\"quote\":\"All our dreams will come true, if we have the courage to pursue them\",\"author\":\"Walt Disney\"},{\"quote\":\"The secret of change is to focus all of your energy, not on fighting the old, but on building the new\",\"author\":\"Socrates\"},{\"quote\":\"If you don't like something, change it. If you can't change it, change your attitude.\",\"author\":\"Mary Angelou\"},{\"quote\":\"Your mind is a powerful thing. When you fill it with positive thoughts, your life will start to change\",\"author\":\"Gautama Buddha\"},{\"quote\":\"A bad attitude is like a flat tire; if you don't change it, you won't go anywhere\",\"author\":\"Joyce Meyer\"},{\"quote\":\"We are all here for some special reason. Stop being a prisoner of your past. Become the architect of your future\",\"author\":\"Robin Sharma\"},{\"quote\":\"You'll never change your life until you change something you do daily. The secret of your success is found in your daily routine\",\"author\":\"John C. Maxwell\"},{\"quote\":\"The future belongs to those who believe in the beauty of their dreams\",\"author\":\"Eleanor Roosevelt\"},{\"quote\":\"Dream big and dare to fail\",\"author\":\"Norman Vaughan\"},{\"quote\":\"Setting goals is the first step in turning the invisible into the visible\",\"author\":\"Tony Robbins\"},{\"quote\":\"Imagine your life is perfect in every respect; what would it look like?\",\"author\":\"Brian Tracy\"},{\"quote\":\"I think goals should never be easy; they should force you to work, even if they are uncomfortable at the time\",\"author\":\"Michael Phelps\"},{\"quote\":\"People who are crazy enough to think they can change the world are the ones who do\",\"author\":\"Rob Siltanen\"},{\"quote\":\"You have brains in your head. You have feet in your shoes. You can steer yourself in any direction you choose\",\"author\":\"Dr. Seuss\"},{\"quote\":\"Whatever the mind can conceive and believe, it can achieve\",\"author\":\"Napoleon Hill\"},{\"quote\":\"First, have a definite, clear, practical ideal; a goal, an objective. Second, have the necessary means to achieve your ends; wisdom, money, materials, and methods. Third, adjust all your means to that end\",\"author\":\"Aristotle\"},{\"quote\":\"If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success\",\"author\":\"James Cameron\"},{\"quote\":\"You are never too old to set another goal or to dream a new dream\",\"author\":\"C.S. Lewis\"},{\"quote\":\"Dreaming, after all, is a form of planning\",\"author\":\"Gloria Steinem\"},{\"quote\":\"Don't wish it were easier. Wish you were better.\",\"author\":\"Jim Rohn\"},{\"quote\":\"Always bear in mind that your own resolution to success is more important than any other one thing\",\"author\":\"Abraham Lincoln\"},{\"quote\":\"I find that the harder I work, the more luck I seem to have\",\"author\":\"Thomas Jefferson\"},{\"quote\":\"I never dreamed about success; I worked for it\",\"author\":\"Estee Lauder\"},{\"quote\":\"For every reason it's not possible, there are hundreds of people who have faced the same circumstances and succeeded\",\"author\":\"Jack Canfield\"},{\"quote\":\"Success is walking from failure to failure with no loss of enthusiasm\",\"author\":\"Winston Churchill\"},{\"quote\":\"I attribute my success to this: I never gave or took any excuse\",\"author\":\"Florence Nightingale\"},{\"quote\":\"Success usually comes to those who are too busy to be looking for it\",\"author\":\"Henry David Thoreau\"},{\"quote\":\"Today's accomplishments were yesterday's impossibilities\",\"author\":\"Robert H. Schuller\"},{\"quote\":\"If you look really closely, most overnight successes took a long time\",\"author\":\"Steve Jobs\"},{\"quote\":\"When you want to succeed as bad as you want to breathe, then you'll be successful\",\"author\":\"Eric Thomas\"},{\"quote\":\"Things work out the best for those who make the best of how things work out\",\"author\":\"John Wooden\"},{\"quote\":\"People who succeed have momentum. The more they succeed, the more they want to succeed, and the more they find a way to succeed. Similarly, when someone is failing, the tendency is to get on a downward spiral that can even become a self-fulfilling prophecy\",\"author\":\"Tony Robbins\"},{\"quote\":\"There are no secrets to success. It is the result of preparation, hard work, and learning from failure\",\"author\":\"Colin Powell\"},{\"quote\":\"Success is a personal standard, reaching for the highest that is in us, becoming all we can be\",\"author\":\"Zig Ziglar\"},{\"quote\":\"The road to success and the road to failure are almost exactly the same\",\"author\":\"Colin R. Davis\"},{\"quote\":\"Don't be distracted by criticism. Remember – the only taste of success some people get is to take a bite out of you\",\"author\":\"Zig Ziglar\"},{\"quote\":\"Success is not final; failure is not fatal: it is the courage to continue that counts\",\"author\":\"Winston S. Churchill\"},{\"quote\":\"Our greatest glory is not in never falling, but in rising every time we fall\",\"author\":\"Confucius\"},{\"quote\":\"You will face many defeats in life, but never let yourself be defeated\",\"author\":\"Maya Angelou\"},{\"quote\":\"You may be disappointed if you fail, but you are doomed if you don't try\",\"author\":\"Beverly Sills\"},{\"quote\":\"Failure will never overtake me if my determination to succeed is strong enough\",\"author\":\"Og Mandino\"},{\"quote\":\"You learn more from failure than from success. Don't let it stop you. Failure builds character\",\"author\":\"Unknown\"},{\"quote\":\"It's not whether you get knocked down, it's whether you get up\",\"author\":\"Vince Lombardi\"},{\"quote\":\"It's during our darkest moments that we must focus to see the light\",\"author\":\"Aristotle\"},{\"quote\":\"Many of life's failures are people who did not realize how close they were to success when they gave up\",\"author\":\"Thomas A. Edison\"},{\"quote\":\"What you lack in talent can be made up with desire, hustle, and giving 110% all the time\",\"author\":\"Don Zimmer\"},{\"quote\":\"Don't let what you cannot do interfere with what you can do\",\"author\":\"John Wooden\"},{\"quote\":\"Nothing is impossible, the world itself says, ‘I'm possible!'\",\"author\":\"Audrey Hepburn\"},{\"quote\":\"The question isn't who is going to let me; it's who is going to stop me?\",\"author\":\"Ayn Rand\"},{\"quote\":\"Winning isn't everything, but wanting to win is\",\"author\":\"Vince Lombardi\"},{\"quote\":\"It does not matter how slowly you go as long as you do not stop\",\"author\":\"Confucius\"},{\"quote\":\"When everything seems to be going against you, remember that the airplane takes off against the wind, not with it\",\"author\":\"Henry Ford\"},{\"quote\":\"I alone cannot change the world, but I can cast a stone across the water to create many ripples\",\"author\":\"Mother Teresa\"},{\"quote\":\"The two most important days in your life are the day you are born and the day you find out why\",\"author\":\"Mark Twain\"},{\"quote\":\"When you stop chasing the wrong things, you give the right things a chance to catch you\",\"author\":\"Lolly Daskal\"},{\"quote\":\"Decide upon your major definite purpose in life and then organize all your activities around it.\",\"author\":\"Brain Tracy\"},{\"quote\":\"The only way to do great work is to love what you do. If you haven't found it yet, keep looking. Don't settle\",\"author\":\"Steve Jobs.\"},{\"quote\":\"It is never too late to be what you might have been\",\"author\":\"George Eliot\"},{\"quote\":\"The only person you are destined to become is the person you decide to be\",\"author\":\"Ralph Waldo Emerson\"},{\"quote\":\"Always stay true to yourself and never let what somebody else says distract you from your goals\",\"author\":\"Michelle Obama\"},{\"quote\":\"Always remember that you are absolutely unique. Just like everyone else\",\"author\":\"Margaret Mead\"},{\"quote\":\"Your time is limited, so don't waste it living someone else's life\",\"author\":\"Steve Jobs\"},{\"quote\":\"Be yourself, everyone else is already taken\",\"author\":\"Oscar Wilde\"},{\"quote\":\"You have to be odd to be number one\",\"author\":\"Dr. Seuss\"},{\"quote\":\"The man who has confidence in himself gains the confidence of others\",\"author\":\"Hasidic Proverb\"},{\"quote\":\"Try not to become a person of success, but rather, try to become a person of value\",\"author\":\"Albert Einstein\"},{\"quote\":\"If you cannot do great things, do small things in a great way\",\"author\":\"Napoleon Hill\"},{\"quote\":\"We are what we repeatedly do. Excellence, then, is not an act, but a habit\",\"author\":\"Aristotle\"},{\"quote\":\"Go the extra mile, there's no-one on it\",\"author\":\"Grant Cardone\"},{\"quote\":\"Don't judge each day by the harvest you reap but by the seeds that you plant\",\"author\":\"Robert Louis Stevenson\"},{\"quote\":\"Keep your face always toward the sunshine and shadows will fall behind you\",\"author\":\"Walt Whitman\"},{\"quote\":\"The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty\",\"author\":\"Winston Churchill\"},{\"quote\":\"No-one can make you feel inferior without your consent\",\"author\":\"Eleanor Roosevelt\"},{\"quote\":\"I cam not a product of my circumstances. I am a product of my decisions\",\"author\":\"Stephen Covey\"},{\"quote\":\"Always do your best. What you plant now, you will harvest later\",\"author\":\"Og Mandino\"},{\"quote\":\"When you reach the end of your rope, tie a knot in it and hang on\",\"author\":\"Franklin D. Roosevelt\"},{\"quote\":\"Gratitude is not only the greatest of virtues, but the parent of all others\",\"author\":\"Cicero\"},{\"quote\":\"Develop an ‘Attitude of gratitude.' Say thank you to everyone you meet for everything they do for you\",\"author\":\"Brain Tracy\"},{\"quote\":\"If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough\",\"author\":\"Oprah Winfrey\"},{\"quote\":\"Creativity is intelligence having fun\",\"author\":\"Albert Einstein\"},{\"quote\":\"The best and most beautiful things in the world cannot be seen or even touched – they must be felt with the heart\",\"author\":\"Helen Keller\"},{\"quote\":\"It is better to fail in originality than to succeed in imitation\",\"author\":\"Herman Melville\"},{\"quote\":\"Don't let yesterday take up too much of today\",\"author\":\"Will Rogers\"},{\"quote\":\"If life were predictable, it would cease to be life, and be without flavor\",\"author\":\"Eleanor Roosevelt\"},{\"quote\":\"Life is never fair. And perhaps it is a good thing for most of us that it is not\",\"author\":\"Oscar Wilde\"},{\"quote\":\"Life is what happens when you're busy making other plans\",\"author\":\"John Lennon\"},{\"quote\":\"In the end, it's not the years in your life that count. It's the life in your years\",\"author\":\"Abraham Lincoln\"},{\"quote\":\"You only live once. But if you do it right, once is enough\",\"author\":\"Mae West\"},{\"quote\":\"Life is either a daring adventure or nothing at all\",\"author\":\"Helen Keller\"},{\"quote\":\"Life is 10% what happens to me and 90% of how I react to it\",\"author\":\"Charles Swindoll\"},{\"quote\":\"Live in the sunshine, swim the sea, drink the wild air\",\"author\":\"Ralph Waldo Emerson\"},{\"quote\":\"Do not let making a living prevent you from making a life\",\"author\":\"John Wooden\"},{\"quote\":\"Life is ours to be spent, not to be saved\",\"author\":\"D. H. Lawrence\"},{\"quote\":\"If you're offered a seat on a rocket ship, don't ask what seat! Just get on\",\"author\":\"Sheryl Sandberg\"},{\"quote\":\"In three words, I can sum up everything I've learned about life: it goes on\",\"author\":\"Robert Frost\"},{\"quote\":\"Whoever is happy will make others happy too\",\"author\":\"Anne Frank\"},{\"quote\":\"Spread love everywhere you go. Let no one ever come to you without leaving happier\",\"author\":\"Mother Teresa\"},{\"quote\":\"The purpose of our lives is to be happy\",\"author\":\"Dalai Lama\"},{\"quote\":\"I would rather die of passion than of boredom\",\"author\":\"Vincent van Gogh\"},{\"quote\":\"Keep smiling, because life is a beautiful thing and there's so much to smile about\",\"author\":\"Marilyn Monroe\"},{\"quote\":\"Go confidently in the direction of your dreams! Live the life you've imagined\",\"author\":\"Henry David Thoreau\"},{\"quote\":\"Love the life you live. Live the life you love\",\"author\":\"Bob Marley\"},{\"quote\":\"The real test is not whether you avoid this failure, because you won't. It's whether you let it harden or shame you into inaction, or whether you learn from it; whether you choose to persevere\",\"author\":\"Barack Obama\"},{\"quote\":\"Life itself is the most wonderful fairy tale\",\"author\":\"Hans Christian Anderson\"}]";
            StringBuilder contentBuilder = new StringBuilder();
            String filename = "init/Quotes.json";

            //File file = ResourceUtils.getFile("classpat:" + filename);
            try {
                File file = ResourceUtils.getFile("classpath:" + filename);
                List<String> lines = Files.readAllLines(file.toPath());
                for(String line : lines){
                    contentBuilder.append(line.trim()).append(" ");
                }
            }
            catch (IOException e){
                if (!e.getMessage().contains("class path resource [init/Quotes.json] cannot be resolved to absolute file path because it does not reside in the file system")) {
                    System.out.println("[Exception @ Quotes.InitQuotes] error message:" + e.getMessage());
                    throw e;
                }
            }
            
            quotes = new ArrayList<Quote>();
            authorQuotes = new HashMap<String, ArrayList<Integer>>();

            String jsonString = contentBuilder.length() != 0 
                ? contentBuilder.toString()
                : standardQuotes;

            // System.out.println("[Information @ Quotes.InitQuotes] Quotes as JSON: " + jsonString);

            JSONArray jarr = new JSONArray(jsonString);

            for (int index = 0; index < jarr.length(); index++) {
                JSONObject jo = jarr.getJSONObject(index);
                Quote q = new Quote(
                    jo.getString("quote").trim(), jo.getString("author").trim());
                quotes.add(q);

                String normalizedAuthor = q.getAuthor().toLowerCase();

                if (! authorQuotes.containsKey(normalizedAuthor)) {
                    authorQuotes.put(normalizedAuthor, new ArrayList<Integer>());
                }

                ArrayList<Integer> quoteIds = authorQuotes.get(normalizedAuthor);
                quoteIds.add(index);
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

    public static Quote getQuoteFrom(String author) throws Throwable {
        if (quotes == null) {
            Quotes.InitQuotes();
        } 

        // System.out.println("[Information @ Quotes.getQuoteFrom] -- started --");

        if (author == null || author.trim().length() == 0){
            // System.out.println("[Warning @ Quotes.getQuoteFrom] #No author provided#");
            return new Quote("", "#No author provided#");
        }

        author = author.trim();

        String authorToLowercase = author.toLowerCase();

        ArrayList<String> authors = new ArrayList<String>(authorQuotes.keySet());

        ArrayList<Quote> localquotes = new ArrayList<Quote>();

        for (int index = 0; index < authors.size(); index++) {
            String currentAuthor = authors.get(index);
            if (currentAuthor.toLowerCase().indexOf(authorToLowercase) != -1) {

                ArrayList<Integer> quotesIndexes = authorQuotes.get(currentAuthor);
                for (int j = 0; j < quotesIndexes.size(); j++) {
                    localquotes.add(quotes.get(quotesIndexes.get(j)));                    
                }
            }
        }

        return localquotes.size() > 0 
            ? localquotes.get(rnd.nextInt(localquotes.size())) 
            : new Quote("", "#Not Found: " + author);
    }

    public static Quote[] getQuotes() throws Throwable{
        if (quotes == null) {
            Quotes.InitQuotes();
        } 

        System.out.println("[Information @ Quotes.getQuotes] begin");
        ArrayList<Quote> resultquotes = new ArrayList<Quote>();
        ArrayList<Quote> localquotes = new ArrayList<Quote>();
        localquotes.addAll(quotes);

        int i = 0;
        while (localquotes.size() > 0 && i < getDefaultQuotesListSize()) {
            Quote removedQuote = localquotes.remove(rnd.nextInt(localquotes.size()));
            resultquotes.add(removedQuote);
            i++;
        }

        System.out.println("[Information @ Quotes.getQuotes] number of quotes returned: " + Integer.toString(resultquotes.size()));
        Quote[] results = new Quote[resultquotes.size()];
        return resultquotes.toArray(results);
    }

    public static Quote[] getQuotesFrom(String author) throws Throwable{
        if (quotes == null) {
            Quotes.InitQuotes();
        } 

        System.out.println("[Information @ Quotes.getQuotesFrom] begin");
        if (author == null || author.trim().length() == 0){
            System.out.println("[Warning @ Quotes.getQuotesFrom] #No author provided#");
            return new Quote[]{ new Quote("", "#No author provided#")};
        }

        author = author.trim();

        // standardize parameter 
        String authorToLowercase = author.toLowerCase();

        // get the list of all the authors of the quotes
        ArrayList<String> authors = new ArrayList<String>(authorQuotes.keySet());

        // A place where to collect all quotes from all authors matching the given author
        ArrayList<Quote> localquotes = new ArrayList<Quote>();

        for (String currentAuthor : authors) {
            if (currentAuthor.toLowerCase().indexOf(authorToLowercase) != -1) {
                // Get the indexes of the quotes of the current author
                ArrayList<Integer> quotesIndexes = authorQuotes.get(currentAuthor);
                for (int j = 0; j < quotesIndexes.size(); j++) {
                    localquotes.add(quotes.get(quotesIndexes.get(j)));                    
                }

                System.out.println("[Information @ Quotes.getQuotesFrom] matched " + Integer.toString(quotesIndexes.size()) + " quotes from '" + currentAuthor + "' to '" + author + "'");
            }
        }

        if (localquotes.size() == 0) {
            return new Quote[]{ new Quote("", "#Not Found: " + author) };
        }

        int resultSize = localquotes.size() > getDefaultQuotesListSize() ? getDefaultQuotesListSize() : localquotes.size();

        return localquotes.toArray(new Quote[resultSize]);
    }
}
