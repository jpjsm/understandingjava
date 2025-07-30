package com.example.github.api;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.Map.Entry;
import java.net.http.HttpRequest.BodyPublishers;

import org.json.JSONObject;

import java.net.URI;

public class Github {
   private static final Pattern serverPattern = Pattern.compile(
         "^https?://[a-z0-9][a-z0-9\\.-]+(:[0-9]+)?$",
         Pattern.CASE_INSENSITIVE);

   private String server = null;
   private String token = null;
   private String username = null;
   private String githubApiVersion = "2022-11-28";
   private Map<String, String> defaultHeaders = new HashMap<>();
   private HttpClient client = HttpClient.newBuilder()
         .version(Version.HTTP_1_1).followRedirects(Redirect.NORMAL)
         .build();

   public static final String PUBLIC_OCTOCAT_TEMPLATE = "%s/octocat";
   public static final String USERS_REPOS_TEMPLATE = "%s/users/%s/repos";
   public static final String USER_REPOS_TEMPLATE = "%s/user/repos";
   public static final String REPO_PATH_TEMPLATE = "%s/repos/%s/%s";

   public static final String HTTP_4XX_TEMPLATE = "HTTP_STATUS=%d, MESSAGE=%s";

   /**
    * Creates a simplified mechanism to access Github API for a given user,
    * at a certain server.
    *
    * @param server the http(s) server name and the port (if port is
    *               non-standard)
    * @param token  the unencrypted token
    */
   public Github(String server, String token, String githubApiVersion,
         String username) {
      server = server.trim();
      if (server == null || server.equals("")) {
         throw new IllegalArgumentException(
               "'server' cannot be null or empty.");
      }

      if (server.charAt(server.length() - 1) == '/') {
         server = server.substring(0, server.length());
      }

      Matcher serverMatcher = serverPattern.matcher(server);
      if (!serverMatcher.matches()) {
         throw new IllegalArgumentException(
               "'server' doesn't look like a valid URL.");
      }

      this.server = server;

      this.token = token;

      if (githubApiVersion != null && !githubApiVersion.equals("")) {
         this.githubApiVersion = githubApiVersion;
      }

      this.username = username;

      defaultHeaders.put("Accept", "application/vnd.github+json");
      defaultHeaders.put("X-GitHub-Api-Version", this.githubApiVersion);
      if (this.token != null && !this.token.equals("")) {
         defaultHeaders.put("Authorization", "Bearer " + this.token);
      }
   }

   /**
    * Test public connectivity to Github; no user or token required aka get
    * the Octo Cat !!
    */
   public String GetPublicOctocat() throws Exception {
      String url = String.format(PUBLIC_OCTOCAT_TEMPLATE, this.server);

      return getRequest(url, null);
   }

   /**
    * Get a list of all public repos under the authenticated user
    *
    * @return the body of the response as a string
    * @throws Exception on HTTP_STATUS 4xx, 5xx
    */
   public String ListUserPublicRepos() throws Exception {
      String url = String.format(USERS_REPOS_TEMPLATE, this.server,
            this.username);

      return getRequest(url, null);
   }

   /**
    * List repositories to which the authenticated user has access (private
    * or public)
    *
    * @return the body of the response as a string
    * @throws Exception on HTTP_STATUS 4xx, 5xx
    */
   public String ListAllReposForUser() throws Exception {
      String url = String.format(USER_REPOS_TEMPLATE, this.server);

      return getRequest(url, null);
   }

   /**
    * @param payload           the parameters to update a repo as a JSON
    *                          object. See: -
    *                          https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28#update-a-repository
    * @param additionalHeaders any extra headers needed on top of the
    *                          default ones
    * @return the body of the response as a string
    * @throws Exception on HTTP_STATUS 4xx, 5xx
    */
   public String CreateRepo(JSONObject payload,
         Map<String, String> additionalHeaders) throws Exception {
      String url = String.format(USER_REPOS_TEMPLATE, this.server);

      return postRequest(url, additionalHeaders, payload);
   }

   /**
    * @param payload
    * @param additionalHeaders
    * @return
    * @throws Exception
    */
   public String UpdateRepoProperties(String owner, String repoName,
         JSONObject payload, Map<String, String> additionalHeaders)
         throws Exception {
      String url = String.format(REPO_PATH_TEMPLATE, this.server, owner,
            repoName);

      return patchRequest(url, additionalHeaders, payload);
   }

   /**
    * @param url               the github endpoint
    * @param additionalHeaders any extra headers needed on top of the
    *                          default ones
    * @return the body of the response as a string
    * @throws Exception on HTTP_STATUS 4xx, 5xx
    */

   private String getRequest(String url,
         Map<String, String> additionalHeaders) throws Exception {
      String[] h = ConcatenateAdditionalHeaders(additionalHeaders);

      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
            .headers(h).build();

      HttpResponse<String> response = this.client.send(request,
            BodyHandlers.ofString());

      if (response.statusCode() >= 400) {
         String errBody = response.body();
         if (errBody == null || errBody.equals("")) {
            errBody = "(no error message in response)";
         }
         throw new Exception(String.format(HTTP_4XX_TEMPLATE,
               response.statusCode(), errBody));
      }

      return response.body();
   }

   /**
    * @param url               the github endpoint
    * @param additionalHeaders any extra headers needed on top of the
    *                          default ones
    * @param payload           the arguments to the POST as a JSON object
    * @return the body of the response as a string
    * @throws Exception on HTTP_STATUS 4xx, 5xx
    */
   private String postRequest(String url,
         Map<String, String> additionalHeaders, JSONObject payload)
         throws Exception {
      String[] h = ConcatenateAdditionalHeaders(additionalHeaders);

      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
            .POST(BodyPublishers.ofString(payload.toString())).headers(h)
            .build();

      HttpResponse<String> response = this.client.send(request,
            BodyHandlers.ofString());

      if (response.statusCode() >= 400) {
         String errBody = response.body();
         if (errBody == null || errBody.equals("")) {
            errBody = "(no error message in response)";
         }
         throw new Exception(String.format(HTTP_4XX_TEMPLATE,
               response.statusCode(), errBody));
      }

      return response.body();
   }

   /**
    * @param url               the github endpoint
    * @param additionalHeaders any extra headers needed on top of the
    *                          default ones
    * @param payload           the arguments to the POST as a JSON object
    * @return the body of the response as a string
    * @throws Exception on HTTP_STATUS 4xx, 5xx
    */
   private String patchRequest(String url,
         Map<String, String> additionalHeaders, JSONObject payload)
         throws Exception {
      String[] h = ConcatenateAdditionalHeaders(additionalHeaders);
      HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
            .POST(BodyPublishers.ofString(payload.toString())).headers(h)
            .build();

      HttpResponse<String> response = this.client.send(request,
            BodyHandlers.ofString());

      if (response.statusCode() >= 400) {
         String errBody = response.body();
         if (errBody == null || errBody.equals("")) {
            errBody = "(no error message in response)";
         }
         throw new Exception(String.format(HTTP_4XX_TEMPLATE,
               response.statusCode(), errBody));
      }

      return response.body();
   }

   private String[] MapStringStringToArray(Map<String, String> a) {
      List<String> pairs = new ArrayList<>();
      for (Entry<String, String> kvp : a.entrySet()) {
         pairs.add(kvp.getKey());
         pairs.add(kvp.getValue());
      }

      return pairs.toArray(new String[0]);
   }

   private String[] MapStringListStringtoArray(
         Map<String, List<String>> a) {
      List<String> pairs = new ArrayList<>();
      for (Entry<String, List<String>> kvp : a.entrySet()) {
         for (String value : kvp.getValue()) {
            pairs.add(kvp.getKey());
            pairs.add(value);
         }
      }

      return pairs.toArray(new String[0]);
   }

   private String[] ConcatenateAdditionalHeaders(
         Map<String, String> additionalHeaders) {
      String[] dh = MapStringStringToArray(this.defaultHeaders);
      String[] ah = new String[0];

      if (additionalHeaders != null) {
         ah = MapStringStringToArray(additionalHeaders);
      }

      return Stream.concat(Arrays.stream(dh), Arrays.stream(ah))
            .toArray(String[]::new);
   }
}
