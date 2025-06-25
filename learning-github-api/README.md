# Learning Github API

This adventure is about learning the Github API using Java.

I want to learn how to:

- Create Repos.
- Configure access, visibility, _Webhooks_, and CI/CD integration.
- Configure/Manage branch publication and merging rules.
- Create branches.
- Add/Update/Delete files.
- Commit changes.
- Create PRs
- Handle PR workflows

Out of all possible languages to use for this adventure, Java is the least
appealing to me. However, is the one I need to practice more at this stage in
my career.

## Github path

To learn the basics, I'll follow the advice given at: [Getting Started with
the Rest API](https://docs.github.com/en/rest/guides/getting-started-with-the-rest-api)
 by Github Docs team. 

> I'll be foillowing the `CURL` guide, and implementing those calls in Java,
see the next topic [Java path](#markdown-header-java-path). 
 
 Once I'm done with the introductory guide... I'll see what's still
 left from the goals above, and search for another guide.

To understand how to update files and create a new 'single' commit the
`getting-started-with-the-rest-api` guide wasn't enough; so, searched the
internet and found some guidance: [Create a folder and push multiple files
under a single commit through GitHub API](https://dev.to/bro3886/create-a-folder-and-push-multiple-files-under-a-single-commit-through-github-api-23kc)
The following image summarizes the entire flow of operations required to
upload files and make the commit.

![Multiple files with single commit workflow](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/1paoytdkvtczvkzhcg85.jpg)

## Java path

Instead of using `cUrl` for Github API calls, I'm going to use Java
`HttpClient` for that purpose. To understand `HttpClient` I'll be following:

- [Introduction to the Java HTTP Client @ OpenJDK](
    https://openjdk.org/groups/net/httpclient/intro.html)
- [Exploring the New HTTP Client in Java @ Baeldung](
    https://www.baeldung.com/java-9-http-client)

## Attributions and references used

In no particular order

- [Getting Started with the Rest API © by Github](
    https://docs.github.com/en/rest/guides/getting-started-with-the-rest-api)
- [Introduction to the Java HTTP Client © by OpenJDK](
    https://openjdk.org/groups/net/httpclient/intro.html)
- [Exploring the New HTTP Client in Java © by Baeldung](
    https://www.baeldung.com/java-9-http-client)
- [Create a folder and push multiple files under a single commit through GitHub API © Siddhartha Varma](
    https://dev.to/bro3886/create-a-folder-and-push-multiple-files-under-a-single-commit-through-github-api-23kc)
- [How to create directory in Java © MKYong](https://mkyong.com/java/how-to-create-directory-in-java/)
- [How to write a UTF-8 file in Java © MKYong](https://mkyong.com/java/how-to-write-utf-8-encoded-data-into-a-file-java/)
- [Java I/O Tutorial © MKYong](
    https://mkyong.com/tutorials/java-io-tutorials/)
- [How to get list of PRIVATE repositories via api call #24382 © Github](
    https://github.com/orgs/community/discussions/24382)
- [Repositories © Github](
    https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28)
- [Creating a personal access token © Github](
    https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token#creating-a-personal-access-token-classic)
- [How to execute shell command from Java © MKYong](https://mkyong.com/java/how-to-execute-shell-command-from-java/)
- [Working with NPM from Java, practival guide © Ilya Naryzhnyy](
    https://medium.com/orienteer/working-with-npm-from-java-6acddd97f7a4)
- [Java AES encryption and decryption © MKYong](
    https://mkyong.com/java/java-aes-encryption-and-decryption/)
- [Double colon (::) operator in Java © geeksforgeeks](
    https://www.geeksforgeeks.org/double-colon-operator-in-java/)
- [Java – How to list all files in a directory? © MKYong](
    https://mkyong.com/java/java-how-to-list-all-files-in-a-directory/)
- [Github API - create branch? © StackOverflow](
    https://stackoverflow.com/questions/9506181/github-api-create-branch)
- [Javadoc Tool © Oracle](
    https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)
- [Create a Custom Exception in Java © Baeldung](
    https://www.baeldung.com/java-new-custom-exception)
- [How to Concatenate Two Arrays in Java © HowToDoInJava](
    https://howtodoinjava.com/java/array/concatenate-arrays/)
- [How to get the current working directory in Java © MK Yong](
    https://mkyong.com/java/how-to-get-the-current-working-directory-in-java/)
- [Set The Current Working Directory © JavaCodex](
    https://www.javacodex.com/Files/Set-The-Current-Working-Directory)    