import java.util.HashMap;
import java.util.Map;

public class wordFreq {
	public static void main(String[] args) {
		String book = "the  day  is sunNy the   the the sunny is is";
		System.out.println(freq(book, "suny"));
	}
	
	private static int freq(String book, String word) {
		if(book.isEmpty() || word.isEmpty()) return 0;
		Map<String, Integer> map = new HashMap<>();
		String[] words = book.split("\\s+");
		for(String wrd : words) {
			String w = wrd.toLowerCase();
			map.put(w, map.getOrDefault(w, 0) + 1);
		}
		return map.get(word) == null ? 0 : map.get(word);
	}
}
