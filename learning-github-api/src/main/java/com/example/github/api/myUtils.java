package com.example.github.api;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class myUtils {
   public static final String HTTP_METHOD_PATCH = "PATCH";

   public static String jsonPrettyfy(String jsonString) {
      String prettier = null;

      try {
         // remove matching enclosing quotes
         while (jsonString.charAt(0) == '"'
               && jsonString.charAt(jsonString.length() - 1) == '"'
               && jsonString.length() > 1) {
            jsonString = jsonString.substring(1, jsonString.length()
                  - 1); /* substring function substracts 1 from endIndex */
         }

         char firstChar = jsonString.charAt(0);
         switch (firstChar) {
         case '[':
            JSONArray jsonArr = new JSONArray(jsonString);
            prettier = jsonArr.toString(4);
            break;

         case '{':
            JSONObject jsonObject = new JSONObject(jsonString);
            prettier = jsonObject.toString(4);
            break;

         default:
            throw new Exception(
                  String.format("JSON: Unexpected first character '%s'",
                        String.valueOf(firstChar)));
         }
      }
      catch (JSONException err) {
         // ToDo: define how to warn the user about jsonString not proper
         // JSON
         System.out.println(
               "-->".repeat(5) + " JSONException " + "<--".repeat(5));
         System.out.println(err.getMessage());
         System.out.println("=".repeat(45));
      }
      catch (Exception err) {
         // ToDo: define how to warn the user about jsonString not proper
         // JSON
         System.out.println(
               "-->".repeat(5) + " General Exception " + "<--".repeat(5));
         System.out.println(err.getMessage());
         System.out.println("=".repeat(53));
      }

      return prettier;
   }

   public static JSONArray toJsonArray(String jsonString) {
      JSONArray jsonArray = new JSONArray();

      try {
         jsonString = removeEnclosingCharPair(jsonString, '"');

         char firstChar = jsonString.charAt(0);
         switch (firstChar) {
         case '[':
            JSONArray jsonArr = new JSONArray(jsonString);
            jsonArray = jsonArr;
            break;

         case '{':
            JSONObject jsonObject = new JSONObject(jsonString);
            jsonArray.put(jsonObject);
            break;

         default:
            throw new Exception(
                  String.format("JSON: Unexpected first character '%s'",
                        String.valueOf(firstChar)));
         }
      }
      catch (JSONException err) {
         // ToDo: define how to warn the user about jsonString not proper
         // JSON
         System.out.println(
               "-->".repeat(5) + " JSONException " + "<--".repeat(5));
         System.out.println(err.getMessage());
         System.out.println("=".repeat(45));
      }
      catch (Exception err) {
         // ToDo: define how to warn the user about jsonString not proper
         // JSON
         System.out.println(
               "-->".repeat(5) + " General Exception " + "<--".repeat(5));
         System.out.println(err.getMessage());
         System.out.println("=".repeat(53));
      }

      return jsonArray;
   }

   public static String removeEnclosingCharPair(String string,
         char delimiter) {
      while (string.charAt(0) == delimiter
            && string.charAt(string.length() - 1) == delimiter
            && string.length() > 1) {
         if (string.equals("\"\"")) {
            return "";
         }
         string = string.substring(1, string.length() - 1); // substring
                                                            // function
                                                            // substracts 1
                                                            // from endIndex
      }

      return string;

   }

   public static String UtcNowToString() {
      Instant now = Instant.now();
      ZoneId zi = ZoneId.of("UTC");
      ZonedDateTime zdt = now.atZone(zi);
      return String.format("%d-%02d-%02d %02d%02d%02d %09d", zdt.getYear(),
            zdt.getMonthValue(), zdt.getDayOfMonth(), zdt.getHour(),
            zdt.getMinute(), zdt.getSecond(), zdt.getNano());
   }
}
