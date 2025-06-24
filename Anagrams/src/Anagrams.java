import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Anagrams {
	public static String GetKeyFromWord(String word) {
		char[] keyarray = word.toLowerCase().toCharArray();
		Arrays.sort(keyarray);
		return new String(keyarray);
	}
	
	public static ArrayList<ArrayList<String>> GetAnagramGroups(ArrayList<String> words){
		HashMap<String, ArrayList<String>> anagramgroups = new HashMap<String, ArrayList<String>>(words.size());
		ArrayList<String> current = new ArrayList<String>();
		
		for (String word : words) {
			String key = GetKeyFromWord(word);
			if (!anagramgroups.containsKey(key)) {
				anagramgroups.put(key, new ArrayList<String>());
			}
			
			current = anagramgroups.get(key);
			current.add(word);
			anagramgroups.put(key, current);
		}
		
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		for(ArrayList<String> anagrams: anagramgroups.values()) {
			if (anagrams.size() == 1) {
				continue;
			}
			
			results.add(anagrams);			
		}
		
		return results;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> words = new ArrayList<String>();
		words.add("Pores"); 
		words.add("Fear");
		words.add("Poser");
		words.add("one");
		words.add("Prose");
		words.add("Ropes");
		words.add("two");
		words.add("Spore");
		words.add("Fare");
		
		int group = 0;
		for(ArrayList<String> anagramgroup: GetAnagramGroups(words)) {
			System.out.print("Group");
			System.out.print(++group);
			System.out.print(": ");
			System.out.println(String.join(", ", anagramgroup));			
		}
		
	}

}
