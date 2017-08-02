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

	public static void main(String[] args) {
		int[] array1 = {1, 2, 1, 3, 1, 1, 5};
		int[] array2 = {1, 2, 3};
		TreeNode n1 = TreeNode.insertFromArray(array1);
		TreeNode n2 = TreeNode.insertFromArray(array2);
		if (isSbtree(n1, n2)) {
			System.out.println("t2 is a subtree of t1");
		} else {
			System.out.println("t2 is not a subtree of t1");
		}

	}

}
