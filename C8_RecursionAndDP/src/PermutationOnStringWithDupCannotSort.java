import java.util.*;
public class PermutationOnStringWithDupCannotSort {

	public static void main(String[] args) {
		String test = "122";
		List<String> res = new ArrayList<>();
		Map<Character, Integer> map = new HashMap<>();
		for(int i = 0; i < test.length(); i++) {
			map.put(test.charAt(i), map.getOrDefault(test.charAt(i), 0) + 1);
		}
		permuWithDup(test, new StringBuilder(), map, res);
		System.out.println(res);
	}
	
	// aaaaab
	// a 5 b 1
	// use the map to track the count. keys are distinct, each time choose each key and append(backtracking), decrease the cnt. until the 
	// string is completely built
	
	//
	public static void permuWithDup(String s, StringBuilder temp, Map<Character, Integer> map, List<String> res) {
		if(s.isEmpty()) return;
		if(temp.length() == s.length()) {
			res.add(temp.toString());
			return;
		}
		for(char key : map.keySet()) {
			int cnt = map.get(key);
			if(cnt > 0) {
				temp.append(key);
				map.put(key, cnt - 1);
				permuWithDup(s, temp, map, res);
				map.put(key, cnt);		 // not cnt + 1
				temp.deleteCharAt(temp.length() - 1); // dont forget to remove the appended char as well.
			}
		}
	}

}
