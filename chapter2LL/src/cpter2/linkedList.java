package cpter2;

import java.util.HashSet;

class linkedList {
	Node head;//why this will solve pointer to head changes problem
		
		static class Node{		//static class so main func can use it.
		Node next = null;
		int data;
		Node(int d){
			this.data = d;
		}
		}
		
		public void appendToTail(int d){
			//1->2->3->null;
			Node end = new Node(d);
			Node n = head;
			if(n == null){
				n = end;
			}else{
			while(n.next != null){
				n = n.next;
			}
			n.next = end;
			//System.out.println(n.next.data);
			}
		}
		
		
		public void printList(){
			Node n = head;
			while(n != null){
				System.out.println(n.data);//要操作的是n（print操作），∴判断是n；
				n = n.next;		
			}
			
		}
		
		public Node delNode(int d){		//del node with data d
			Node n = head;
			if(n.data == d){
				return head.next;
			}//nil->1->2->3->nil
			while(n.next != null){
				if(n.next.data == d){
					n.next = n.next.next;
				}
				n = n.next;
			}
			return head;
		} 
		
		public static void deletDup(Node head){
			HashSet<Integer> set = new HashSet<Integer>();
			Node n = head;
			while(n.next != null){		//不是data = null, 因为data是int型
				if(set.contains(n.next.data)){
					n.next = n.next.next;
				}else{
					set.add(n.next.data);
					n = n.next;
				}
			}	
		}
		
		
		
		public static void main (String[] args){
			linkedList ll = new linkedList();
			ll.head = new Node(1);
			ll.appendToTail(2);		//是谁的property一定要看清
			ll.appendToTail(3);
			ll.appendToTail(4);
			ll.printList();
			ll.deletDup(ll.head);
			ll.printList();
			}
	}

