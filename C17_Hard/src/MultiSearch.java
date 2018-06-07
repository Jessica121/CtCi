import java.util.*;
public class MultiSearch {
	
	/*
	 * Given a long string and list of small strings, search all indexes of occurrence of each small strings in the long string.
	 * 
	 * e.g. list: "is", "ppi", "hi", "sis", "i", "ssippi"
	 * 		long: "mississippi"
	 * 
	 * */
	
	/*
	 * Build a trie from the long string, from suffixes. store the start index in each node in which the small string belongs to.
	 * 
	 * find a node, add the index and char, go to the next char/ child, do the same thing.
	 * the duplicated ones will have more indexes in its index array.
	 * 
	 * */
	public static List<List<Integer>> searchInLongWord(String s, String[] list) {
		Trie root = buildTrie(s);
		List<List<Integer>> res = new ArrayList<>();
		for(String str : list) {
			res.add(root.search(str));
		}
		return res;
	}
	
	public static Trie buildTrie(String s) {
		TrieNode root = new TrieNode('-');
		Trie trie = new Trie(root);
		for(int i = 0; i < s.length(); i++) {
			trie.insert(s.substring(i), i);
		}
		return trie;
	}
	
	/*
	 * Another way is to build a trie from small words.
	 * search starting from each index of the long word. add to the map result when isWord == true
	 * 
	 * */
	public static Map<Integer, List<String>> searchInLongWordTwo(String s, String[] list) {
		TrieNode root = new TrieNode('-');
		Trie trie = new Trie(root);
		for(String str : list) {
			trie.insert(str);
		}
		Map<Integer, List<String>> map = new HashMap<>();
		for(int i = 0; i < s.length(); i++) {
			List<String> res = trie.searchSmall(s.substring(i));
			if(res.size() != 0) map.put(i, res);
		}
		return map;
	}
	
	public static class Trie {
		TrieNode root;
		public Trie(TrieNode root) {
			this.root = root;
		}

		public void insert(String s, int ind) {
			TrieNode node = root;
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if(!node.child.containsKey(c)) node.child.put(c, new TrieNode(c));
				node.child.get(c).index.add(ind);
				node = node.child.get(c);
			}
			node.isWord = true;
		}
		
		public void insert(String s) {
			TrieNode node = root;
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if(!node.child.containsKey(c)) node.child.put(c, new TrieNode(c));
				node = node.child.get(c);
			}
			node.isWord = true;
		}
		
		public List<Integer> search(String s) {
			TrieNode node = root;
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if(node.child.get(c) == null) return new ArrayList<>();
				node = node.child.get(c);
			}
			return node.index;
		}
		
		public List<String> searchSmall(String s) {
			List<String> res = new ArrayList<>();
			TrieNode node = root;
			int i = 0;
			while(i < s.length()) {
				char c = s.charAt(i);
				// the node refers to the current node, not the child that is the end of a string.
				if(node == null) break;
				// if it is a word, add to res, keep going. it may contain word later on.
				if(node.isWord) res.add(s.substring(0, i));
				node = node.child.get(c);
				i++;
			}
			// check at the end. when the node is not null and it is a word. add it.
			if(node != null && node.isWord) res.add(s.substring(0, i));
			return res;
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
		String big = "mississippi";
		String[] smalls = {"is", "ppi", "hi", "sis", "i", "mississippi"};
        System.out.println(searchInLongWordTwo(big, smalls));
	}

}
