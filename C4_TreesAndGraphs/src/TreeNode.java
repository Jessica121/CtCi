import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

public class TreeNode {
		public int size = 0;
		public TreeNode left;
		public TreeNode right;
		public TreeNode parent; 
		public int data;
		
		public TreeNode(int d){
			this.data = d;
			size = 1;
		}
		
		public void insertInOrder(int a){
			if(a <= this.data){
				if(this.left == null){
					this.setLeft(new TreeNode(a));
				}else{
					left.insertInOrder(a);
				}
			}else{
				if(this.right == null){
					this.setRight(new TreeNode(a));
				}else{
					right.insertInOrder(a);
				}
			}
			size++;			//size!!
		}
		
		private void setLeft(TreeNode left){
			this.left = left;
			if(left != null){
				left.parent = this;			//parent!
			}
		}
		
		private void setRight(TreeNode right){
			this.right = right;
			if(right != null){
				right.parent = this;
			}
		}
		
		public boolean isBST(){
			if(left != null){
			if(left.data > this.data || !left.isBST()){		//!left.isBST() return 分支層作為判斷，整個函數不中斷（return分支層）
				return false;
			}}
			if(right != null){
			if(right.data <= this.data || !right.isBST()){
				return false;
			}}
			return true;
		}
		
		public TreeNode search(int t){
			if (this.data == t){
				return this;
			}else if(t <= this.data){
				return left != null ? left.search(t) : null;
//				if(left != null){
//					return left.search(t);			//分支return只是return到分支層，不是最上面層
//				}else{
//					return null;
//				}
			}else if(t > this.data){
				return right != null ? right.search(t) : null;
//				if(right != null){
//					return right.search(t);
//				}else{
//					return null;
//				}
			}
			return null;
		}
		
		public int size(){
			return size;
		}
		
		public int height(){
			int lh = left == null ? 0 : left.height();
			int rh = right == null ? 0 : right.height();
			return Math.max(lh,rh) + 1;
//			int lh = 1;			//everytime call the height(), gonna refresh lh back to 1 again
//			int rh = 1;
//	
//			if(left != null){
//				 lh = left.height()+1;
//			}else if(right != null){
//				 rh = right.height()+1;}
//			return Math.max(lh,rh);		
			
		}

		
		private static TreeNode createMinHeightTree(int[] arr, int s, int e){
			if(s > e){
				return null;
			}else{
			int mid = (s+e)/2;
			TreeNode midN = new TreeNode(arr[mid]);
			midN.setLeft(createMinHeightTree(arr,s,mid-1));
			midN.setRight(createMinHeightTree(arr,mid+1,e));
			return midN;
			}
		}
		
		public static TreeNode createMinHeightTree(int[] arr){
			return createMinHeightTree(arr, 0, arr.length - 1);//搞不清輸入是什麼
		}
		
		public static TreeNode insertFromArray(int[] arr){
			int c = 0;
			TreeNode root = new TreeNode(arr[0]);
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.add(root);
			c++;
			boolean done = false;
			while(!done){
				TreeNode n = q.element();
				if(n.left == null){
					n.left = new TreeNode(arr[c]);
					q.add(n.left);
					c++;
				}else if(n.right == null){
					n.right = new TreeNode(arr[c]);
					q.add(n.right);
					c++;
				}else{
					q.remove();
				}
				if(c == arr.length){
					done = true;					//may out of bounds by 2	
				}
			}
			return root;
		}
		
		public static void printArrLists(ArrayList<LinkedList<TreeNode>> lists){
			int depth = 0;
			for(LinkedList<TreeNode> l : lists){
				Iterator<TreeNode> i = l.listIterator();
				System.out.print("LinkedList at depth"+depth+": ");
				while(i.hasNext()){
					System.out.print(i.next().data+" ");
				}
				System.out.println();
				depth++;
			}
				
		}
		

//		4.3 Fibo -> each level create a list if not exist, add the node
		public static void createLLAtLevels(TreeNode n, ArrayList<LinkedList<TreeNode>> allL, int index){	//node's level = index
			if(n == null) return;
			LinkedList<TreeNode> l = null;
			if(allL.size() == index){	//array 's corresponding size and index is diff by 1;
				l = new LinkedList<TreeNode>();
				allL.add(l);
			}else{
				l = allL.get(index);
			}
			l.add(n);
			createLLAtLevels(n.left,allL,index+1);
			createLLAtLevels(n.right,allL,index+1);
		}
		
		public static ArrayList<LinkedList<TreeNode>> createLLAtLevels(TreeNode n){
			ArrayList<LinkedList<TreeNode>> res = new ArrayList<LinkedList<TreeNode>>();
			createLLAtLevels(n,res,0);
			return res;
		}
			
		public static void main (String[] args){

//			TreeNode root = new TreeNode(4);
//			root.insertInOrder(2);
//			root.insertInOrder(6);
//			root.insertInOrder(1);
//			root.insertInOrder(3);
//			root.insertInOrder(5);
//			root.insertInOrder(7);

//			TreeNode root = new TreeNode(1);
//			root.insertInOrder(2);
//			root.insertInOrder(3);
//			root.insertInOrder(4);
//			root.insertInOrder(5);
//			root.insertInOrder(6);
//			root.insertInOrder(7);
//			System.out.println("size is -> "+root.size());
//			System.out.println("height is -> "+root.height());
//			if(root.isBST()){
//				System.out.println("Is a BST");
//			}else{
//				System.out.println("Not a BST");
//			}
//			TreeNode f = root.search(5);
//			System.out.println(f.data);
			
//			4.2 min height tree test
			int[] arr = {1,2,3,4,5,6,7,8};
//			TreeNode rt = TreeNode.createMinHeightTree(arr);
//			System.out.println("min height tree root data -> "+rt.data);
//			System.out.println("4.2 height is -> "+rt.height());
//			if(rt.isBST()){
//				System.out.println("4.2 Is a BST");
//			}else{
//				System.out.println("4.2 Not a BST");
//			}
			
			TreeNode root = insertFromArray(arr);
			System.out.println("4.3 height is -> "+root.height());
			if(root.isBST()){
				System.out.println("4.3 Is a BST");
			}else{
				System.out.println("4.3 Not a BST");
			}
			
			printArrLists(createLLAtLevels(root));
		}
		
}
