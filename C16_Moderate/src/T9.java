import java.util.*;
public class T9 {

	/**
	 * A collection of dictionary - Trie is a possible DS to fast search 
	 */
	public static void main(String[] args) {
		String num = "8733";
		Set<String> dict = new HashSet<String>();
		dict.add("treee");
		dict.add("used");
		dict.add("vrfd");
		dict.add("a");
		dict.add("asde");
		dict.add("vpde");
		System.out.println(getDictListPreCompute(num, dict));
	}
	
	private static T9 instance = new T9();
	
	class TrieNode {
		TrieNode[] kids;
		boolean isWord;
		public TrieNode() {
			kids = new TrieNode[26];
			isWord = false;
		}
	}
	
	private static TrieNode root;
	
	private static void buildTrie(Set<String> dict) {
		root = instance.new TrieNode();
		for(String s : dict) insert(s);
	}
	
	private static void insert(String s) {
		TrieNode node = root;
		for(int i = 0; i < s.length(); i++) {
//			System.out.println(s.charAt(i) - '0');
			if(node.kids[s.charAt(i) - 'a'] == null) node.kids[s.charAt(i) - 'a'] = instance.new TrieNode();
			node = node.kids[s.charAt(i) - 'a'];
		}
		node.isWord = true;
	}
	
	private static boolean isPrefix(String s) {
		TrieNode node = root;
		for(int i = 0; i < s.length(); i++) {
			if(node.kids[s.charAt(i) - 'a'] == null) return false;
			node = node.kids[s.charAt(i) - 'a'];
		}
		return true;
	}
	
	private static List<String> getDictList(String num, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[][] t9Letters = {
				null, 					// 0
				null, 					// 1
				{'a', 'b', 'c'}, 		// 2
				{'d', 'e', 'f'}, 		// 3
				{'g', 'h', 'i'}, 		// 4
				{'j', 'k', 'l'},		// 5
				{'m', 'n', 'o'},		// 6
				{'p', 'q', 'r', 's'}, 	// 7
				{'t', 'u', 'v'},		// 8
				{'w', 'x', 'y', 'z'} 	// 9
		};
		buildTrie(dict);
		LinkedList<String> que = new LinkedList<String>();
		que.offer("");
		int size = 0;
		for(int i = 0; i < num.length(); i++) {
			// DFS use size
			size = que.size();
			for(int j = 0; j < size; j++) {
				// 1. char - '0' or - 'a. number char or char char.
				// 2. String Builder remove after not satisfying the condition.
//				 先poll 出来，que里面的每一个元素加上这一层的数字。想清楚 -> 明白嵌套
				StringBuilder toAppend = new StringBuilder(que.poll());
				for(char c : t9Letters[num.charAt(i) - '0']) {
					toAppend.append(c);
//					System.out.println(toAppend);
					if(isPrefix(toAppend.toString())) que.offer(toAppend.toString());
					toAppend.deleteCharAt(toAppend.length() - 1);
				}
			}
		}
		// Things in que are prefix, not necessary real words
		for(String ele : que) {
			if(dict.contains(ele)) res.add(ele);
		}
		return res;
	}
	
	/**
	 * compute every word in dict, map them into words, and look up the words.
	 */
	private static List<String> getDictListPreCompute(String num, Set<String> dict) {
		Map<String, List<String>> map = new HashMap<>();
		for(String s : dict) {
			map.computeIfAbsent(computeNum(s), k -> new ArrayList<>()).add(s);
		}
		return map.get(num);
	}
	
	private static String computeNum(String s) {
		StringBuilder sb = new StringBuilder();
		char[][] t9Letters = {
				null, 					// 0
				null, 					// 1
				{'a', 'b', 'c'}, 		// 2
				{'d', 'e', 'f'}, 		// 3
				{'g', 'h', 'i'}, 		// 4
				{'j', 'k', 'l'},		// 5
				{'m', 'n', 'o'},		// 6
				{'p', 'q', 'r', 's'}, 	// 7
				{'t', 'u', 'v'},		// 8
				{'w', 'x', 'y', 'z'} 	// 9
		};
		// tree
		int[] map = new int[26];
		for(int i = 2; i < t9Letters.length; i++) {
			for(int j = 0; j < t9Letters[i].length; j++) {
				map[t9Letters[i][j] - 'a'] = i;
			}
		}
		for(int i = 0; i < s.length(); i++) {
			sb.append(map[s.charAt(i) - 'a']);
		}
		return sb.toString();
	}
	
}
