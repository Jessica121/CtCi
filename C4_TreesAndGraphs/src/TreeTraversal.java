
public class TreeTraversal {
	
	public static void visit(TreeNode n){
		if(n != null){
			System.out.println(n.data);
		}
	}
	
	public static void inOrderT(TreeNode n){
		if(n != null){
			inOrderT(n.left);
			visit(n);
			inOrderT(n.right);
		}
	}
	
	public static void preOrderT(TreeNode n){
		if(n != null){
			visit(n);
			preOrderT(n.left);
			preOrderT(n.right);
		}
	}
	
	public static void postOrderT(TreeNode n){
		if(n != null){
			postOrderT(n.left);
			postOrderT(n.right);
			visit(n);
		}
	}

	public static void main(String[] args) {
			int[] arr = {1,2,3,4,5,6,7};
			TreeNode root = TreeNode.createMinHeightTree(arr);
			inOrderT(root);
			preOrderT(root);
			postOrderT(root);
	}

}
