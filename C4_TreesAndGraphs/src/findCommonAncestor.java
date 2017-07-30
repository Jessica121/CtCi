
public class findCommonAncestor {
//	-----------------------------sol 1-------------------------------------
	public static TreeNode findCA1(TreeNode a, TreeNode b){
//		first trim the longer node's brance, make it align with the shorter one so they can go up together in one closure
		int ha = getDpth(a);
		int hb = getDpth(b);
		int diff = Math.abs(ha - hb);
		System.out.println("height diff is-> "+diff);
//		*錯誤：ha = hb 情況沒有handle，所以都給了b………………………………………………
		TreeNode longer = (ha >= hb)? a:b;
		TreeNode shorter = (ha < hb)? a:b;
		System.out.println("longer shorter"+longer.data +" "+ shorter.data);
//		*這裡出錯。。。
		longer = goUpBy(longer , diff);
		System.out.println("longer shorter"+longer.data +" "+ shorter.data);
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
		System.out.println("n go up to the node "+n.data);
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
	
//	---------------------------------------sol3----------------------------------------------------
//	starting from the root, the anecster wont go below one of the node a or b
//	so once a and b are on dif side , return it
//	corner case: 1. a covers b or otherwise
//				2. a covers b but a only has b as a child, when recursive to that null child
//				3. either a or b not in the tree
	public static TreeNode findCA3(TreeNode root, TreeNode a, TreeNode b){
		if(!cover(root,a)||!cover(root,b)){
			return null;
		}
//		runs the helper after making sure two nodes are on this tree in question.
//		avoid recheck if a b are in same subtree..
		return ancesterHelper(root,a,b);
	}
	public static TreeNode ancesterHelper(TreeNode root, TreeNode a , TreeNode b){
//		null means recursive to the leaves (??)
		if(root == null || root == a || root == b) //only check for immediate children
			return root;
		
//		code below only can check nodes BELOW the root. 
//		hence code ABOVE should eliminate the case for root itself first.
		boolean aOnL = cover(root.left,a);
		boolean bOnL = cover(root.left,b);
		if((aOnL && !bOnL) || (!aOnL && bOnL)){
			return root;
		}
//		the only condition left is a b both on left or right
		TreeNode goTo = aOnL? root.left: root.right;
		
		return ancesterHelper(goTo, a, b);
	}
	
//	---------------------------------------Sol4--------------------------------
//	check the tree once from the root to find the pos of p and q.
//	return p or q if find it under the branch including self(?)
//	2 conditions: res left and right are p and q;	res itself a p contains q in its either branch
//	for the both conditions mark it as Ans, else mark it false cuz on latter one, the branch may not contain the other
//	other cases be like, when tree is null, when p and q are the same node
	//	*	Common problem:
//	to find if a node is in the tree, using recursive, and to check is recursive to itself, aka node == p or q
	public static class Result{
//		public public public
		public TreeNode n;
		public boolean isAnc;
		public Result(TreeNode n, boolean isAnc){
			this.n = n;
			this.isAnc = isAnc;
		}
	}

	public static TreeNode findCA4(TreeNode r, TreeNode p , TreeNode q){
		Result res = findCA4Helper(r,p ,q);
		System.out.println("isAnc? "+res.isAnc);
		System.out.println("res's data? "+res.n.data);
		if (res.isAnc) {
			return res.n;
		}
		return null;
	}

	public static Result findCA4Helper(TreeNode r, TreeNode p , TreeNode q){
//		in the recursive bulk, every single condition should be considered and written in here using if...
		if(r == null) return new Result(null , false);	//returned stuff 
		if( r == p && r == q)	return new Result(r , true);
		Result rx = findCA4Helper(r.left, p , q);
//		System.out.println("rx is ->?" + rx.isAnc);
		if(rx.isAnc)		return rx;
		Result ry = findCA4Helper(r.right,p , q);
//		System.out.println("ry is ->?" + ry.isAnc);
		if(ry.isAnc)		return ry;
//		WOW WRAPPER CLASS COMPARE ITS PROPERTY NOT THE WHOLE THING ITSELF
//		rx「「「.n」」」
//		這裡出大錯：兩個都寫成rx……太不小心了
		if(rx.n != null && ry.n != null)		{
			return new Result(r , true);	//root is AC but not p q itself
//		root is one of the p, q and the other is underneath
		}else if(r == p || r == q){
			boolean isA = rx.n != null || ry.n != null;
			return new Result(r, isA);
//		root is one of p, q , the other is not underneath
		}else{
//			if rx == null && ry == null, -> null;
//			if rx != null , -> rx;
//			else -> ry
			return new Result( rx.n != null? rx.n : ry.n, false);
		}
		}
	

//	TESTING CASESES
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinHeightTree(array);
		System.out.println("root-> "+root.data);
		TreeNode n3 = root.search(6);
		TreeNode n4 = root.search(9);
		TreeNode ancestor1 = findCA1(n3, n4);
		TreeNode ancestor2 = findCA2(root,n3, n4);
		TreeNode ancestor3 = findCA3(root,n3, n4);
		TreeNode ancestor4 = findCA4(root,n3, n4);
//		if (ancestor3 != null) {
//			System.out.println(ancestor3.data);
//		} else {
//			System.out.println("null");
//		}
		System.out.println("Check me! "+ancestor1.data+" "+ancestor2.data+" "+ancestor3.data+" "+ancestor4.data);
//		System.out.println("Check me! "+ancestor3.data);
	}
	}
	