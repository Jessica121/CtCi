
public class findCommonAncestor {
	
	public static TreeNode findCA(TreeNode a, TreeNode b){
//		first trim the longer node's brance, make it align with the shorter one so they can go up together in one closure
		int ha = getDpth(a);
		int hb = getDpth(b);
		int diff = Math.abs(ha - hb);
		System.out.println("diff "+diff);
		TreeNode longer = (ha > hb)? a:b;
		TreeNode shorter = (ha < hb)? a:b;
//		*這裡出錯。。。
		longer = goUpBy(longer , diff);
		System.out.println("after goupby "+longer.data);
		while(longer != shorter && longer != null && shorter != null){
			longer = longer.parent;
			shorter = shorter.parent;
		}
		return longer == null || shorter == null ? null: longer;
	}
	
	public static int getDpth(TreeNode n){
		int h = 0;
		while(n != null){
			n = n.parent;
			h++;
		}
		return h;			// h = 1 when only one node.
	}
	
	public static TreeNode goUpBy(TreeNode n , int h){
		while(h != 0 && n!= null){
			n = n.parent;
			h--;
		}
		System.out.println("n in go upby "+n.data);
		return n;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinHeightTree(array);
		TreeNode n3 = root.search(1);
		TreeNode n7 = root.search(7);
		TreeNode ancestor = findCA(n3, n7);
		//System.out.println(ancestor.data);

	}

}
