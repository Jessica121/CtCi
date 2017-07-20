import java.util.Stack;

import ReverseSecondHalfOfLL.ListNode;
public class FourQuestionCollection {
//	1. rotate Matrix
	public class rotateMatrix {
	    public int[][] rotate(int[][] matrix, int flag)
	    {
	        if(matrix == null || matrix.length == 0) return matrix;
	        int m = matrix.length, n = matrix[0].length;
	        int[][] res = convertM(n,m,matrix);
	        res = flag == 1 ? rightRotate(res,n,m):leftRotate(res,n,m);
	        return res;
	    }
	    
	    public int[][] convertM(int m,int n,int[][] matrix){
	    		int[][] res = new int[n][m];
	        for(int i = 0; i < m; i++)
	        {
	            for(int j = 0; j < n; j++) res[j][i] = matrix[i][j];
	        }
	        return res;
	    }
	    
	    public int[][] rightRotate(int[][]res, int n, int m){
		    	for(int i = 0; i < n; i++)
		        {
		            for(int j = 0; j < m / 2; j++)
		            {
		                int tmp = res[i][j];
		                res[i][j] = res[i][m - 1 - j];
		                res[i][m - 1 - j] = tmp;
		            }
		        }
		    	return res;
	    }
	    
	    public int[][] leftRotate(int[][] res, int n, int m){
		    	for(int j = 0; j < m ;j++)
		        {
		            for(int i = 0; i < n / 2; i++)
		            {
		                int tmp = res[i][j];
		                res[i][j] = res[n - 1 - i][j];
		                res[n - 1 - i][j] = tmp;
		            }
		        }
		    	return res;
	    }
	}
	
//2.Valid Parethethes
	public class ValidPare {
	    public boolean isValid(String s) {
	    		if (s == null || s.length() == 0)   return true;
	        Stack<Character> stack = new Stack<Character>();
	        for(char c: s.toCharArray())
	        {
	            if(c == ')')
	            {
	                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
	                else return false;
	            }
	            else if(c == ']')
	            {
	                if(!stack.isEmpty() && stack.peek() == '[') stack.pop();
	                else return false;
	            }
	            else if(c == '}')
	            {
	                if(!stack.isEmpty() && stack.peek() == '{') stack.pop();
	                else return false;
	            }
	            else stack.push(c);
	        }
	        return stack.isEmpty();
	    }    
	}

//3. Binary search tree minimum sum from root to leaf
	public class BSTMinSumFromRootToLeaf {
		public int DFS(TreeNode root)
		{
		      if(root == null) return 0;
		      else return root.val + Math.min(DFS(root.left), DFS(root.right));
		}
	//sol2
		public class PathSum {
		    public int Solution(TreeNode root) {
		        if (root == null)   return 0;
		        if (root.left != null && root.right == null)
		            return Solution(root.left) + root.val;
		        if (root.left == null && root.right != null)
		            return Solution(root.right) + root.val;
		        return Math.min(Solution(root.left), Solution(root.right)) + root.val;
		    }
		}
	}
	
//4. ReverseSecondHalfOfLinkedList
	public class ReverseSecondHalfOfLL {
		/**
		 * Definition for singly-linked list.
		 */
		  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
//		  
		  
		    public ListNode reverseList(ListNode head) {
		        if(head == null || head.next == null) return head;
		        ListNode slow = head, fast = slow.next;
		        while(fast.next != null && fast.next.next != null)
		        {
		            slow = slow.next;
		            fast = fast.next.next;
		        }
		        ListNode pre = null, cur = slow.next;
		        while(cur != null)
		        {
		            ListNode next = cur.next;
		            cur.next = pre;
		            pre = cur;
		            cur = next;
		        }
		        slow.next = pre;
		        return head;
		    }
	}
	
}
