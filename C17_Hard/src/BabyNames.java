import java.util.*;
public class BabyNames {
	/*
	 * Given a list of names and synonyms, return the true freq.
	 * 
	 * Undirected graph with weight.
	 * Build an island that maps children to parent. Find if the parent is the same one, merge into res list. add the children weight.
	 * Start from the input list.
	 * 		If it is a parent itself and did not exist in the map or from the child the parent did not exist in the res, 
	 * add parent and its weight, then the child's weight.
	 * 		If already exist, only add the child's weight.
	 * 
	 * */

	// union find approach.
	public static Map<String, Integer> mergeFreq(Map<String, Integer> names, String[][] synonyms) {
		Map<String, String> island = new HashMap<>();
		buildIsland(island, synonyms);
		Map<String, Integer> res = new HashMap<>();
		for(String name : names.keySet()) {
			String parent = find(name, island);
			if(!res.containsKey(parent)) {
				if(parent.equals(name)) res.put(parent, names.getOrDefault(parent, 0));
				else res.put(parent, names.getOrDefault(parent, 0) + names.getOrDefault(name, 0));
			} else {
				// don't add when itself is the parent, if the parent already exist, else will re-add by the child.
				if(!parent.equals(name)) res.put(parent, res.get(parent) + names.getOrDefault(name, 0));
			}
		}
		return res;
	}

	private static void buildIsland(Map<String, String> island, String[][] synonyms) {
		for(String[] pair : synonyms) {
			String p1 = find(pair[0], island), p2 = find(pair[1], island);
			if(p1.equals(p2)) continue;
			island.put(p1, p2);
		}
	}
	
	private static String find(String child, Map<String, String> island) {
		while(island.containsKey(child)) child = island.get(child);
		return child;
	}
	
	// Build a graph, pick up the nodes and do dfs, sum up the edges. and mark visited.
	
	public static class Node {
		private boolean isVisited;
		private String name;
		private int freq;
		private List<Node> nei = new ArrayList<>();
		public Node(String name, int freq) {
			this.name = name;
			this.freq = freq;
			this.isVisited = false;
		}
		
		public void setVisited() {
			this.isVisited = true;
		}
		
		public boolean hasVisited() {
			return isVisited;
		}
		
		public int getFreq() {
			return this.freq;
		}
		
		public void addEdge(Node n) {
			this.nei.add(n);
		}
		
		public List<Node> getNei() {
			return this.nei;
		}
	}
	
	public static class Graph {
		private Map<String, Node> graph = new HashMap<>();
		public void addNode(String name, int freq) {
			graph.put(name, new Node(name, freq));
		}
		
		public void addEdge(String n1, String n2) {
			// deal with nodes not appear in the freq list.
			if(!this.graph.containsKey(n1)) addNode(n1, 0);
			if(!this.graph.containsKey(n2)) addNode(n2, 0);
			// undirected graph, add with each other is the point.
			this.graph.get(n1).addEdge(graph.get(n2));
			this.graph.get(n2).addEdge(graph.get(n1));
		}
		
		public Node getNode(String name) {
			return this.graph.get(name);
		}
		
		public Set<String> getAllNodes() {
			return this.graph.keySet();
		}
	}
	
	public static Map<String, Integer> mergeFreqGraph(Map<String, Integer> names, String[][] synonyms) {
		Graph graph = new Graph();
		buildGraph(graph, names);
		for(String[] pair : synonyms) graph.addEdge(pair[0], pair[1]);
		int sum = 0;
		Map<String, Integer> res = new HashMap<>();
		for(String nodeName : graph.getAllNodes()) {
			//if node is not visited, calculate.
			Node node = graph.getNode(nodeName);
			if(!node.isVisited) {
				sum = calculateSum(node);
				res.put(nodeName, sum);
			}
		}
		return res;
	}
	
	private static void buildGraph(Graph g, Map<String, Integer> names) {
		for(String key : names.keySet()) {
			g.addNode(key, names.get(key));
		}
	}
	
	private static int calculateSum(Node parent) {
		if(parent.isVisited) return 0;
		parent.setVisited();
		int res = parent.getFreq();
		for(Node child : parent.getNei()) {
			res += calculateSum(child);
		}
		return res;
	}
	
	public static void main(String[] args) {

		Map<String, Integer> names = new HashMap<String, Integer>();
		
		names.put("John", 15);
		names.put("Jon", 12);
//		names.put("Johnny", 5);
		names.put("Chris", 13);
		names.put("Kris", 4);
		names.put("Christopher", 19);
//		names.put("Bryan", 4);
//		names.put("Carleton", 4);
		
		String[][] synonyms = 
			{{"John", "Johnny"}, 
			 {"Jon", "John"}, 
//			 {"Jonathan", "Johnny"}, 
			 {"Chris", "Kris"}, 
			 {"Chris", "Christopher"}};
		
		System.out.println(mergeFreqGraph(names, synonyms));

	}

}
