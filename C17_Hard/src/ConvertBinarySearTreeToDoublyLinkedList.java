
public class ConvertBinarySearTreeToDoublyLinkedList {
	/*
	 * Convert a BST into a doubly linked list in place
	 * 
	 * left's tail and right left
	 * return as needed left or right. (boolean returnRight)
	 * 
	 * left,true, right, false.
	 * if true, return right, true
	 * if false, return left, false
	 * */
	public static BiNode convertBSTtoDLL(BiNode root) {
		return convert(root, false);
	}
	
	public static BiNode convert(BiNode node, boolean returnRight) {
		// if the node is null, return null.
		if(node == null) return null;
		BiNode left = convert(node.left, true), right = convert(node.right, false);
		// since func return null, so left or right could be null. 
		// so check before concat.
		concat(left, node, right);
		// cannot recursively call itself for right true and left false again:
		// since the left and right pointer are already converted into linkedlist.
		// but actually really inefficient.
		if(returnRight) {
			while(node.right != null) node = node.right;
		} else {
			while(node.left != null) node = node.left;
		}
		return node;
	}
	
	/*
	 * So the better way is to save the head and tail for each node, to pass directly up the recursion stack.
	 * return left.head, right.tail.
	 * 
	 * concat: left.tail, right.head.
	 * at the leaf node, the head and tail is both itself. 
	 * 
	 * thinking is left subtree: separate DLL, the result contains head and tail. 
	 * concat 3 parts.
	 * at each node. head is left DLL's head, tail is right's DLL's tail.
	 * 
	 * if one of them is null, itself is the head or tail.
	 * 
	 * if left == recursion left
	 * the leaf / base result will get passed all the way up root
	 * 
	 * */
	
	public static class NodePair {
		BiNode tail, head;
		public NodePair(BiNode h, BiNode t) {
			this.tail = t;
			this.head = h;
		}
	}
	
	// O(n)
	public static NodePair convert(BiNode root) {
		if(root == null) return null;
		NodePair left = convert(root.left), right = convert(root.right);
		// concat
		concat(left == null ? null : left.tail, root, right == null ? null : right.head);
		// left.head and right.tail == null no need to handle, it was handled by recursion: if left / right == null. head / tail == self.
		return new NodePair(left == null ? root : left.head, right == null ? root : right.tail);
	}
	
	/*
	 * To get rid of extra data structure, return the head also, but return a circular linked list. 
	 * so the tail will be head.left: head point to the tail by its left pointer. but tail does not point back to head.
	 * 
	 * left: left head.
	 * right: right head;
	 * concate node with left.left (left tail)
	 * concate node with right (right head)
	 * if left == null || right == null, replace with itself
	 * then concat the left (left head) with right tail (right.left)
	 * return the left.
	 * */
	
	public static BiNode convertToCLLMain(BiNode root) {
		BiNode head = convertToCLL(root);
		head.left = null;
		return head;
	}
	
	private static BiNode convertToCLL(BiNode root) {
		if(root == null) return null;
		BiNode left = convertToCLL(root.left), right = convertToCLL(root.right);
		// save head and tail. as tail is right.left, which will be reset in the concat method.
		BiNode head = left == null ? root : left, tail = right == null ? root : right.left;
		// in concat, if left == null || right == null, leave it to null, not set to head.
		// or the head.left = head. head.right = head also. while should be its children.
		concat(left == null ? null : left.left, root, right == null ? null : right);
		// link head to tail.
		head.left = tail;
		return head;
	}
	
	private static void concat(BiNode left, BiNode node, BiNode right) {
		if(left != null) {
			left.right = node;
			node.left = left;
		}
		if(right != null) {
			right.left = node;
			node.right = right;
		} 
	}
	
	public static class BiNode {
		BiNode left, right;
		int val;
		public BiNode(int v) {
			this.val = v;
			this.left = null;
			this.right = null;
		}
	}
	
	public static BiNode createTree() {
		BiNode[] nodes = new BiNode[7];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new BiNode(i);
		}
		nodes[4].left = nodes[2];
		nodes[4].right = nodes[5];
		nodes[2].left = nodes[1];
		nodes[2].right = nodes[3];
		nodes[5].right = nodes[6];
		nodes[1].left = nodes[0];
		return nodes[4];
	}

	public static void main(String[] args) {
		BiNode root = createTree();
//		BiNode n = convertBSTtoDLL(root);
		BiNode n = convertToCLLMain(root);
//		NodePair n = convert(root);
		printLinkedListTree(n);
	}
	
	public static void printLinkedListTree(BiNode root) {
		for (BiNode node = root; node != null; node = node.right) {
			if (node.right != null && node.right.left != node) {
				System.out.println("inconsistent node: " + node.val);
			} else System.out.print(node.val + "<=>");
		}
		System.out.println();
	}

}
