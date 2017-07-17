
public class findNextSucc {
	public static TreeNode findNxtSuc(TreeNode n){
		if(n == null){
			return null;
		}
		if(n.right != null){
			return findMinOfRight(n.right);
		}else{
			TreeNode p = n.parent;
			if(p!= null && p.left == n){
				return p;
			}else{									// if(p!= null && p.right == n)
				while(p!= null && p.right == n){
					n = p;
					p = p.parent;
				}
				return p;
			}
		}
	}
	
	public static TreeNode findMinOfRight(TreeNode n){
		if(n == null){
			return null;
		}
		while(n.left != null){
			n = n.left;
		}
		return n;
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinHeightTree(array);
		for(int a: array){
			TreeNode me = root.search(a);
			TreeNode next = findNxtSuc(me);
			if(next != null){
				System.out.println(me.data+"'s next is "+ next.data);
			}else{
				System.out.println(me.data+"has reached far right");
			}
		}

	}

}
