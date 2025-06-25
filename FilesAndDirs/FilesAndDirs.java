import java.util.Scanner;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FilesAndDirs{
    public static List<String> listAllFiles(String startingPath, int maxDepth = -1){
        List<String> results = new ArrayList<>()
    
        File top = new File(startingPath)
        File[] files = top.listFiles()
    
        if(files != null){
            for(File f:files){
                if(f.isDirectory()){
                    results.add("${f.getAbsoluteFile()}/")
                    if(maxDepth !=  0){
                        results.addAll(listAllFiles("${f.getAbsoluteFile()}", maxDepth == -1 ? -1 : maxDepth - 1))
                    }
                }
                else{
                    results.add("${f.getAbsoluteFile()}")
                }
            }
        }
    
        return results
    }
    
    List<String> findFiles(
        String startingPath,
        String include_regex, 
        String exclude_regex, 
        int maxDepth = -1){
        List<String> results = new ArrayList<>()
    
        String include_regex_final = (include_regex == null || include_regex.trim().isEmpty()) ? ".+" : include_regex.trim()
        Pattern include_pattern = Pattern.compile(include_regex_final)
    
        String exclude_regex_final = (exclude_regex == null || exclude_regex.trim().isEmpty()) ? "" : exclude_regex.trim()
        Pattern exclude_pattern = Pattern.compile(exclude_regex_final)
        Boolean test_exclude = exclude_regex_final != ""
    
        def found
        File top = new File(startingPath)
        File[] files = top.listFiles()
        
        if(files != null){
            for(File f:files){
                if(test_exclude){
                    found = exclude_pattern.matcher("${f.getAbsoluteFile()}").find() 
    
                    if(found){
                        continue
                    }
                }
    
                if(f.isDirectory()){
                    found = include_pattern.matcher("${f.getAbsoluteFile()}").find() 
    
                    if(found){
                        results.add("${f.getAbsoluteFile()}/")
                    }
    
                    if(maxDepth !=  0){
                        results.addAll(
                            findFiles("${f.getAbsoluteFile()}", 
                            include_regex,
                            exclude_regex,
                            maxDepth == -1 ? -1 : maxDepth - 1))
                    }
                }
                else{
                    found = include_pattern.matcher("${f.getAbsoluteFile()}").find() 
    
                    if(found){
                        results.add("${f.getAbsoluteFile()}")
                    }
                }
            }
        }
    
        return results
    }
    
    def printFolderStructure(
        String startingPath,
        String exclude_regex, 
        int maxDepth = -1){
        List<String> results = new ArrayList<>()
    
        String exclude_regex_final = (exclude_regex == null || exclude_regex.trim().isEmpty()) ? "" : exclude_regex.trim()
        Pattern exclude_pattern = Pattern.compile(exclude_regex_final)
        Boolean test_exclude = exclude_regex_final != ""
    
        def found
        File top = new File(startingPath)
        File[] files = top.listFiles()
        
        if(files != null){
    
            for(File f:files){
                if(test_exclude){
                    String path = "${f.getAbsoluteFile()}"
                    found = exclude_pattern.matcher(path).find() 
                    if(found){
                        continue
                    }
                }
    
                if(f.isDirectory()){
                    String dir = "${f.getAbsoluteFile()}/"
                    print(dir)
                    results.add(dir)
                }
            }
    
            for(String dir: results){
                if(maxDepth !=  0){
                    printFolderStructure(dir, exclude_regex, maxDepth == -1 ? -1 : maxDepth - 1)
                }
            }
        }
    }
    
    
}