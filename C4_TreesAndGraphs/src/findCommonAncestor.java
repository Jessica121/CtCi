
public class findCommonAncestor {
//	-----------------------------sol 1-------------------------------------
	public static TreeNode findCA1(TreeNode a, TreeNode b){
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
	
//	-------------------------------sol 2-----------------------------------
//	func a cover b, when a is b's parent or at least == b, so exclude not in the same branch
//	sblg, if a & b is not each other's parent, get sbling, in diff branch , layer by layer's sibling , if other branch covers, 
//	return the parent.
//	has to have a non-null result for a and b in the same tree:)
	public static boolean cover(TreeNode a, TreeNode b){
//		cover b under a's leading branch
		if(a == null || b == null){
			return false;
		}
		if(a == b){
			return true;
		}
		return cover(a.left , b) || cover(a.right , b);
//		check all the nodes under a basically, multiple check on one child underneath it
	}
	
	public static TreeNode getSblg(TreeNode a){
		if(a == null || a.parent == null) return null;
		TreeNode p = a.parent;
		return a == p.left? p.right:p.left;
	}
	
//	
	public static TreeNode findCA2(TreeNode root, TreeNode a, TreeNode b){
		if(!cover(root, a ) || !cover(root,b)) return null;
//		a and b will in the graph.
		if(cover(a,b)) return a;
		if(cover(b,a)) return b;
//		a and b will not in same branch
		TreeNode sblg = getSblg(a);// doesnt matter get whose sblg, if b is higher, a's parent will go up too
		TreeNode p = a.parent;
		while(!cover(sblg, b)){
//			get current sblg first, then 
			sblg = getSblg(p);
			p = p.parent;
		}
		return p;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinHeightTree(array);
		TreeNode n3 = root.search(9);
		TreeNode n7 = root.search(7);
		TreeNode ancestor1 = findCA1(n3, n7);
		TreeNode ancestor2 = findCA2(root,n3, n7);
		System.out.println("Check me! "+ancestor1.data+" "+ancestor2.data);
	}

}
