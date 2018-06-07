import java.util.*;

public class Re_Space {
	/*
	 * Given a string, and a dictionary, re-space a string with the least unrecognized / not in the dictionary length of characters.
	 * recursion capsulized needed results.
	 * 
	 *  e.g. jesslooks, dict: looks, ss => j + " " + e + " " + ss + " " + looks, unrecog = 2.
	 *  
	 *  if known the best unrecog and the string rep at i, calculate from j to i by checking substring j to i whether in dict and get from j.
	 *  if j is set, slide thru the i from j + 1 to end of string. update the best unrecognized word within the func. (form index j to end)
	 *  same logic must work for the recursive calls.
	 *  base when index == string.length return ("", 0)
	 *  
	 *  if substring (j, i) contained in the dict, this unrecog is 0. else is its length. 
	 *  if it's longer or equal than the shortest unrecognized, no need to proceed.
	 *  else recursion from i and take the res. if concat res has shorter unrecog, update both the string and the len of unrecog.
	 *  go thru from start to the end, will get the best result from index start. cache it.
	 *  
	 *  return 0 index for the main func.
	 * */
	public static Result bestSplit(String string, Set<String> dict) {
		return bestSplit(string, dict, 0, new HashMap<>());
	}
	
	private static Result bestSplit(String string, Set<String> dict, int index, Map<Integer, Result> map) {
		if(map.containsKey(index)) return map.get(index);
		if(index == string.length()) {
			map.put(index, new Result("", 0));
			return map.get(index);
		}
		
		int shortest = Integer.MAX_VALUE, curUnrecog = 0;
		String resStr = "";
		int i = index;
		while(i < string.length()) {
			String substring = string.substring(index, i + 1);
			curUnrecog = dict.contains(substring) ? 0 : substring.length();
			// if the current string is longer than the shortest (e.g. looks -> 0, lookse -> 6, no need to look at it. since unrecog will only be longer.
			if(curUnrecog < shortest) {
				Result next = bestSplit(string, dict, i + 1, map);
				// to update, use this + later shortest
				if(curUnrecog + next.shortestUnrecog < shortest) {
					shortest = curUnrecog + next.shortestUnrecog;
					resStr = substring + " " + next.string;
					// if shortest == 0, no need to find better ones.
					if(shortest == 0) break;
				}
			}
			i++;
		}
		map.put(index, new Result(resStr, shortest));
		return map.get(index);
	}
	
	public static class Result {
		String string;
		int shortestUnrecog;
		public Result(String str, int shortest) {
			this.string = str;
			this.shortestUnrecog = shortest;
		}
	}
	
	private static String clean(String s) {
		char[] punc = {',', '"', '!', '.', '\'', '?', ','};
		for(char c : punc) {
			s = s.replace(c, ' ');
		}
		return s.replace(" ", "").toLowerCase();
	}

	public static void main(String[] args) {
		Set<String> dictionary = new HashSet<>();
		dictionary.add("looks");
		String sentence = "jess lookse !";
		sentence = clean(sentence);
		System.out.println(sentence);
		Result res = bestSplit(sentence, dictionary);
		System.out.println(res.string + " unrecog " + res.shortestUnrecog);
	}

}
