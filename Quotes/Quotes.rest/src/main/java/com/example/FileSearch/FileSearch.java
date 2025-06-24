package com.example.FileSearch;

import static java.util.stream.Collectors.toList;

import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearch {
    public static ArrayList<String> FindMatches(String directory, String regex, boolean caseSensitive) throws Throwable {
        System.out.println("[Information @ FileSearch.FindMatches('" + directory + "', " + regex+ "', CaseSensitive: " + caseSensitive + ")]:");

        ArrayList<String> matchesFound = new ArrayList<String>();

        Pattern pattern = caseSensitive 
            ?  Pattern.compile(regex)
            :  Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Path dirPath = Paths.get(directory);
        
        try (Stream<Path> walk = Files.walk(dirPath)) {
            String[] pathsList = (String[]) walk.map(w -> w.toAbsolutePath().toString()).toArray();
            for(String p : pathsList){
                System.out.println("[Information @ FileSearch.FindMatches]    : " + p + ", matches regex '" + regex + "': " + pattern.matcher(p).find());
            }

            matchesFound.addAll(walk.map(w -> w.toAbsolutePath().toString()).filter(p -> pattern.matcher(p).find()).collect(toList()));
        } catch (Exception e) {
            System.out.println("[Exception @ FileSearch.FindMatches('" + directory + "', " + regex+ "', CaseSensitive: " + caseSensitive + ")]:" + e.getMessage());
            throw e;
        }

        return matchesFound;
    }
    
}
