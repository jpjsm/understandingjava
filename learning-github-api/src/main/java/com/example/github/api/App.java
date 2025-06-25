package com.example.github.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

public class App {
   private static final String encryptedToken = "vdr4xpEvyD7aVj5y3QCPEq2TRFVc0B5CUsamrPmW8idb1zopPU850HNIj/oJSkp+4W9nCgwXWI0vfsuUnWCqJekxEZy8hhS24i7rr92O26jkouIi";
   private static final String githubUsername = "jpjsm";

   public static void main(String[] args) throws Exception {

      String PASSWORD = new String(
            System.console().readPassword("Password to decrypt Token:"));
      String decryptedToken = EncryptorAesGcmPassword
            .decrypt(encryptedToken, PASSWORD);

      JSONArray jsonArray;
      JSONObject payload = new JSONObject();
      String repoJsonString = "";
      JSONObject repoJson = new JSONObject();
      String newRepoId = "";
      String newRepoName = "";

      String githubServer = "https://api.github.com";
      String username = "jpjsm";
      String localPathToRepos = "D:\\tests";
      String openapiOriginalLocation = "tmp/openapi.json";

      /*
       * Start with an anonymous instance of Github class
       */
      Github github = new Github(githubServer, null, null, username);

      /*
       * Get the Octo cat and validate token!!
       */
      System.out.println(github.GetPublicOctocat());

      /*
       * Create a new instance og Github with credentials
       */
      github = new Github(githubServer, decryptedToken, null, username);

      /*
       * Get the Octo cat and validate token!!
       */
      System.out.println(github.GetPublicOctocat());

      /*
       * Get a list of my public repos under the authenticated user
       */
      jsonArray = myUtils.toJsonArray(github.ListUserPublicRepos());

      System.out
            .println(String.format("List of %s's public repos", username));
      for (int i = 0; i < jsonArray.length(); i++) {
         JSONObject jobj = jsonArray.getJSONObject(i);
         System.out.println(
               String.format("-->    %s", jobj.getString("svn_url")));
      }

      /*
       * List repositories to which the authenticated user has access
       * (private or public)
       */
      jsonArray = myUtils.toJsonArray(github.ListAllReposForUser());

      System.out.println(
            "List repositories for the authenticated user (private or public)");
      for (int i = 0; i < jsonArray.length(); i++) {
         JSONObject jobj = jsonArray.getJSONObject(i);
         String svn_url = jobj.has("svn_url") ? jobj.getString("svn_url")
               : "# n/a svn_url #";
         String _private = jobj.has("private")
               ? jobj.get("private").toString()
               : "# n/a private #";
         String visibility = jobj.has("visibility")
               ? jobj.getString("visibility")
               : "# n/a visibility #";
         System.out.println(String.format(
               "-->    %s : Is private == %s : Visibility == %s", svn_url,
               _private, visibility));
      }

      /*
       * Create folder where repo files will exist and add files to the
       * folder; at least one OpenApi spec file. Folder name looks like
       * `YYYY-MM-DD hhmmss nanosecnd`
       */
      String newRepoFolderName = myUtils.UtcNowToString().replace(' ', '_');

      Path openapiPath = Paths.get(openapiOriginalLocation);

      Path reposPath = Paths.get(localPathToRepos);
      Path newRepo = reposPath.resolve(newRepoFolderName);
      if (!Files.exists(newRepo)) {
         Files.createDirectories(newRepo);
      }

      Path deploymentFolderpath = newRepo.resolve("deployment");
      Path devApiFolderPath = deploymentFolderpath.resolve("dev");
      Path stagingApiFolderPath = deploymentFolderpath.resolve("staging");
      Path prodApiFolderPath = deploymentFolderpath.resolve("prod");
      if (!Files.exists(deploymentFolderpath)) {
         Files.createDirectories(devApiFolderPath);
         Files.createDirectories(stagingApiFolderPath.resolve("staging"));
         Files.createDirectories(prodApiFolderPath);
      }

      Path newFile = newRepo.resolve("green-shoot.txt");

      try (FileOutputStream fos = new FileOutputStream(
            newFile.toAbsolutePath().toString());
            OutputStreamWriter osw = new OutputStreamWriter(fos,
                  StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(osw)) {
         writer.append("綠芽已經長到天空 她的祖先在天堂的拱頂上慶祝她 女孩當了兵 士兵成為領導者 領導者成為傳奇\n");
      }

      Path[] specPaths = { devApiFolderPath.resolve("dev.openapi.json"),
            stagingApiFolderPath.resolve("staging.openapi.json"),
            prodApiFolderPath.resolve("prod.openapi.json")
      };
      for (Path specPath : specPaths) {
         Files.copy(openapiPath, specPath,
               StandardCopyOption.COPY_ATTRIBUTES,
               StandardCopyOption.REPLACE_EXISTING);
      }

      /*
       * Create repo and upload files in folder
       */
      System.out.println("Create repo and upload files in folder");
      String repoName = newRepoFolderName.replaceAll("\\s+", "_");

      payload = new JSONObject();
      payload.put("name", repoName);
      payload.put("description", "This is your first repository");
      payload.put("homepage", "https://super.duper.example.com");
      payload.put("auto_init", "true");
      payload.put("private", false);
      payload.put("has_issues", false);
      payload.put("has_projects", false);
      payload.put("has_wiki", false);
      payload.put("visibility", "public");

      repoJsonString = github.CreateRepo(payload, null);

      System.out.println(
            "-->".repeat(5) + " Newly Created Repo " + "<--".repeat(5));
      System.out.println(myUtils.jsonPrettyfy(repoJsonString));
      System.out.println(
            "-/-".repeat(5) + " .................. " + "-\\-".repeat(5));

      repoJson = new JSONObject(repoJsonString);

      newRepoId = repoJson.get("id").toString();
      newRepoName = repoJson.getString("name");

      /*
       * Update new repo to set default values: branch = main
       */
      payload = new JSONObject();
      payload.put("name", newRepoName);
      payload.put("default_branch", "main");

      repoJsonString = github.UpdateRepoProperties(githubUsername,
            newRepoName, payload, null);

      System.out
            .println("-->".repeat(5) + " Updated Repo " + "<--".repeat(5));
      System.out.println(myUtils.jsonPrettyfy(repoJsonString));
      System.out.println(
            "-/-".repeat(5) + " .................. " + "-\\-".repeat(5));

      repoJson = new JSONObject(repoJsonString);

      if (!newRepoId.equals(repoJson.get("id").toString())) {
         throw new Exception(String.format(
               "Updated repo (id: '%s') is different from requested (id: '%s')",
               newRepoId, repoJson.get("id").toString()));
      }

      System.out.println(String.format("\tdefault_branch: %s",
            repoJson.get("default_branch").toString()));
      newRepoId = repoJson.get("id").toString();
      newRepoName = repoJson.getString("name");

      /*
       * Create branch to upload files to
       */

      /*
       * Iterate over the repo folder and update the files
       */

      try (Stream<Path> walk = Files.walk(newRepo)) {
         List<String> result = walk.filter(Files::isRegularFile)
               .map(x -> x.toString()).collect(Collectors.toList());

         result.forEach(System.out::println);
      }
      catch (IOException e) {
         e.printStackTrace();
      }

      /*
       * Update stoplight project
       */

      Path executionDirectory = Paths.get(System.getProperty("user.dir"));

      StringBuilder sb = new StringBuilder("npx @stoplight/cli@6 push ");
      sb.append("--ci-token 2f1312a6-5e4c-4aa8-832d-866528eb1d98 ");
      sb.append("--branch main ");
      sb.append("--directory "
            + executionDirectory.relativize(newRepo).toString());
      ProcessBuilder processBuilder = new ProcessBuilder();

      System.out.println(String.format(
            "About to execute the following command: %s", sb.toString()));
      processBuilder.command("cmd.exe", "/c", sb.toString());

      try {

         Process process = processBuilder.start();

         BufferedReader reader = new BufferedReader(
               new InputStreamReader(process.getInputStream()));

         String line;
         while ((line = reader.readLine()) != null) {
            System.out.println(line);
         }

         int exitCode = process.waitFor();
         System.out.println("\nExited with error code : " + exitCode);

      }
      catch (IOException e) {
         e.printStackTrace();
      }
      catch (InterruptedException e) {
         e.printStackTrace();
      }

      /*
       * Done end of POC
       */
      System.out.println();
      System.out.println("Done end of POC");
   }
}
