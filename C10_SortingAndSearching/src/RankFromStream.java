import java.util.Map;
import java.util.TreeMap;

public class RankFromStream {

	/*
	 * get rank of a num in real time manner
	 * 
	 * 5, 1, 4, 4, 5, 9, 7, 13, 3
	 * using a treemap, insert the element and change the values after,the element inserted is the one right before + 1;
	 * 
	 * */
	
	public static TreeMap<Integer, Integer> map = new TreeMap<>();
	public static void track(int n) {
		if(!map.containsKey(n)) {
			Integer lowerKey = map.lowerKey(n);
			if(lowerKey == null) map.put(n, 0);
			else map.put(n, map.get(lowerKey) + 1);
		} else map.put(n, map.get(n) + 1);
		for(Integer key : map.tailMap(n + 1).keySet()) {
			map.put(key, map.get(key) + 1);
		} 
	}
	
	public static Integer getRankTreeMap(int n) {
		return map.get(n);
	}
	
	/*
	 * Self created data structure.
	 * Binary tree nodes with size of its immediate left subtree.
	 * Get(n) if null, return -1
	 * 		  if smaller than node, return left, if left -1 return -1;
	 * 		  if larger than node, return left【Size】(not recursive left) + 1 (left node itself) + right, if left | right == -1, return -1
	 * 		  if equal, return left size.
	 * 
	 * Track(n) if larger than root, insert right if right not null, create this node to right if right is null (cannot create using recursion, will create a node isolated.
	 * 			if smaller than root, insert left if left not null, create left if left == null
	 * 			if equal, increase the size of the left
	 * */
	public static RankNode root = null;
	public static class RankNode {
		int leftSize, val;
		RankNode left, right;
		public RankNode(int val) {
			leftSize = 0;
			this.val = val;
			this.left = null;
			this.right = null;
		}
		
		public void insert(int n) {
			
			if(n < this.val) {
				leftSize++;
				if(left == null) left = new RankNode(n);
				else left.insert(n);
			} else if(n == this.val) leftSize++;
			else {
				if(right == null) right = new RankNode(n);
				else right.insert(n);
			}
		}
	}
	
	public static int getRank(int n) {
		return getRank(root, n);
	}
	
	private static int getRank(RankNode node, int n) {
		if(node == null) return -1;
		if(node.val == n) return node.leftSize;
		else if(node.val > n) {
			return getRank(node.left, n);
		} else {
			int leftRank = node.leftSize, rightRank = getRank(node.right, n); // if larger than current node, use this.leftSize + 1 + recursion(right), not recursion(left)
			if(rightRank == -1) return -1;
			return leftRank + 1 + rightRank; 
		}
	}
	
	private static void inorderTraverse(RankNode node) {
		if(node == null) return;
		inorderTraverse(node.left);
		System.out.println("node " + node.val + " 's left size is: " + node.leftSize);
		inorderTraverse(node.right);
	}
	
	public static void main(String[] args) {
		track(5);
		track(1);
		track(4);
		track(4);
		track(5);
		track(9);
		track(7);
		track(13);
		track(3);
		System.out.println("TreeMap " + getRankTreeMap(1) + "\n" + getRankTreeMap(3) + "\n" + getRankTreeMap(4));
		
		root = new RankNode(5);
		root.insert(1);
		root.insert(4);
		root.insert(4);
		root.insert(5);
		root.insert(9);
		root.insert(7);
		root.insert(13);
		root.insert(3);
		inorderTraverse(root);
		
		System.out.println(getRank(root, 1) + "\n" + getRank(root, 3) + "\n" + getRank(root, 4));
	}

}
