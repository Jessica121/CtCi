import java.util.*;
public class checkSubtree {
	
//	sol 1, traversal the tree put into string and compare if is the substring
//	hence traverse and append to string, use stringBuilder which also has a func indexOf to check substring
//	use preOrder traverse make sure root is the first in string, null -> 'X' to unique tree with diff structur
//	Use global string pass into func and change the same one in every recursion level
	public static boolean isSbtree(TreeNode n1, TreeNode n2){
		StringBuilder s1 = new StringBuilder();
		StringBuilder s2 = new StringBuilder();
		traverseAndAdd(n1,s1);
		traverseAndAdd(n2,s2);
		System.out.println(s1.toString());
		System.out.println(s2.toString());
//		stringBuilder is NOT ： String 
//		indexOf 的 input type 是 string
//		判断 ！= 和 == 结果相反。。。
//		s2是s1的substring，s1check indexof……看看还有多少傻逼错误要犯，amazon不要你也是应该的
		return s1.indexOf(s2.toString()) != -1;
	}
//	O(m+n) time O(m+n) space
	
	public static void traverseAndAdd(TreeNode n, StringBuilder s){
		if(n == null){
			s.append('X');
			return;
		}
		s.append(n.data);
		traverseAndAdd(n.left,s);
		traverseAndAdd(n.right,s);
//		O(n) time and O(n) space
	}
	
	
//	Sol traverse the big tree, iff the node's value equals the smalls tree's 'ROOT' value,
//	check for every node together in two trees to see if everything in small tree matches
	
	public static boolean isSbBetter(TreeNode n1, TreeNode n2){
		if(n1 == null && n2 == null) return true;
		if(n2 == null) return true;
		if(n1 == null) return false;
//		todo: see if above can be merged into other condition
		if(n1.data == n2.data && treeMatches(n1, n2)){
			return true;
		}else return isSbBetter(n1.left,n2) || isSbBetter(n1.right,n2);
//		O(mn)time: every node in big tree check match small tree
//		better bound: O(n + km) k:small tree's root occurance in big tree's
//		O(lgn+lgm)space, better than O(m+n) -> scalability
	}
//	tree matches must match every node in exact same positions
	public static boolean treeMatches(TreeNode n1, TreeNode n2){
		if(n1 == null && n2 == null)		return true;
		if(n1 == null || n2 == null)		return false;
		if(n1.data != n2.data)		return false;
		else		return treeMatches(n1.left,n2.left)&&treeMatches(n1.right,n2.right);
	}
//	O(m) time and O(lgm) space 

	public static void main(String[] args) {
		int[] array1 = {1, 2, 1, 3, 1, 1, 5};
		int[] array2 = {2, 3, 1};
		TreeNode n1 = TreeNode.insertFromArray(array1);
		TreeNode n2 = TreeNode.insertFromArray(array2);
		if (isSbBetter(n1, n2)) {
			System.out.println("t2 is a subtree of t1");
		} else {
			System.out.println("t2 is not a subtree of t1");
		}

	}

}
