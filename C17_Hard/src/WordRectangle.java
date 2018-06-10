import java.util.*;
public class WordRectangle {
/*
 * Give a list of words, form the largest rectangle where each row is a word and each col is also a word.
 * 
 * if build row by row, need a mechanism to look up in columns, build a trie from the lists.
 * append one row, and check for colomns, if trie does not have it, return false.
 * 
 * row by row need to be the same length, also column by column, need different tries for different length of col (h), 
 * also group words together with same length.
 * 
 * find the largest: from the largest height and width to look. return if valid.
 * 
 * from the longest word * longest word.
 * from the biggest area decrease to 0, for each area, try a * b if dividable.
 * 
 * get words from a length for row:
 * append to list,
 * check col valid, if no such trie exist, create first, if so, recurse, (lazy create, not create everything in one run)
 * if recurse result != null return this result;
 * remove from list.
 * go to the next one.
 * return null
 * */
	
	public static List<String> largestRect(List<String> dict) {
		// group base on length of the strings
		Map<Integer, List<String>> group = new HashMap<>();
		int maxLen = groupStrings(dict, group);
		// do not create unless wants to check it.
		Map<Integer, Trie> tries = new HashMap<>();
//		System.out.println(tries);
		// start from biggest area to 1. e.g. 6: 1 * 6, 2 * 3; 5
		int area = maxLen * maxLen;
		for(; area > 0; area--) {
			for(int i = 1; i <= area / 2; i++) {
				int j = area / i;
				if(area % i == 0) {
					List<String> res = makeRec(i, j, group, tries, new ArrayList<>());
					if(res != null) return res;
				}
			}
		}
		return null;
	}
	
	private static List<String> makeRec(int w, int h, Map<Integer, List<String>> group, Map<Integer, Trie> tries, List<String> temp) {
		if(temp.size() == h) return new ArrayList<>(temp);
		
		// if no such len / width in group, return null immediately
		if(!group.containsKey(w) || !group.containsKey(h)) return null;
		for(String word : group.get(w)) {
			temp.add(word);
			
			if(isValid(temp, tries, w, h, group)) {
				List<String> next = makeRec(w, h, group, tries, temp);
				// if find a valid one, return immediately, no need for further as it is not finding all.
				// find biggest, shortest, optimal one, only need to return immediately if found.
				if(next != null) return next;
			}
			temp.remove(temp.size() - 1);
		}
		return null;
	}
	
	// check column valid or not.
	private static boolean isValid(List<String> words, Map<Integer, Trie> tries, int w, int h, Map<Integer, List<String>> group) {
		if(!tries.containsKey(h)) createTrieAtLen(tries, h, group);
		Trie root = tries.get(h);
		for(int i = 0; i < w; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < words.size(); j++) {
				sb.append(words.get(j).charAt(i));
			}
			// check is prefix not search
			if(!root.isPrefix(sb.toString())) return false;
		}
		return true;
	}
	
	private static int groupStrings(List<String> dict, Map<Integer, List<String>> map) {
		int max = 0;
		for(String s : dict) {
			if(s.length() > max) max = s.length();
			map.computeIfAbsent(s.length(), k -> new ArrayList<>()).add(s);
		}
		return max;
	}
	
	// Lazy calls for it.
	private static void createTrieAtLen(Map<Integer, Trie> tries , int len, Map<Integer, List<String>> group) {
		List<String> candidates = group.get(len);
		Trie root = new Trie(new TrieNode('-'));
		for(String candidate : candidates) root.insert(candidate);
		tries.put(len, root);
	}
	
	public static class Trie {
		TrieNode root;
		public Trie(TrieNode root) {
			this.root = root;
		}
		public void insert(String s) {
			TrieNode node = root;
			for(int i = 0; i < s.length(); i++) {
				char cha = s.charAt(i);
				if(!node.child.containsKey(cha)) node.child.put(cha, new TrieNode(cha));
				node = node.child.get(cha);
			}
			node.isWord = true;
		}
		
		public boolean isPrefix(String pre) {
			TrieNode node = root;
			for(int i = 0; i < pre.length(); i++) {
				char cha = pre.charAt(i);
				if(!node.child.containsKey(cha)) return false;
				node = node.child.get(cha);
			}
			return true;
		}
		
		public boolean search(String s) {
			TrieNode node = root;
			for(int i = 0; i < s.length(); i++) {
				char cha = s.charAt(i);
				if(!node.child.containsKey(cha)) return false;
				node = node.child.get(cha);
			}
			return node.isWord;
		}
		
	}
	
	public static class TrieNode {
		char val;
		List<Integer> index;
		Map<Character, TrieNode> child;
		boolean isWord;
		public TrieNode(char c) {
			this.val = c;
			index = new ArrayList<>();
			child = new HashMap<>();
			isWord = false;
		}
	}
	
	public static void main(String[] args) {
		List<String> dict = new ArrayList<>();
		dict.add("abc");
		dict.add("bcb");
		dict.add("caa");
		dict.add("bca");
		dict.add("cba");
		
		dict.add("abcd");
		dict.add("bcde");
		dict.add("cdef");
		dict.add("defg");

        System.out.println(largestRect(dict));
	}
}
