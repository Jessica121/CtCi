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
		public void appendToTail(int d){
			//1->2->3->null;
			Node end = new Node(d);
			Node n = this;
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
		
		public void setNext(int a){
			Node n = this;
			Node next = new Node(a);
			n.next = next;
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
		
		public void deletDup(Node head){
			HashSet<Integer> set = new HashSet<Integer>();
			Node n = head;
			set.add(head.data);
			while(n.next != null){		//不是data = null, 因为data是int型
				if(set.contains(n.next.data)){
					n.next = n.next.next;
				}else{
					set.add(n.next.data);
					n = n.next;
				}
			}	
		}
		
		public void deletDupNoBuffer(Node head){
			Node cur = head;
			while( cur != null )	{//1->2->3->null
				Node runner = cur;
				while(runner.next != null){
					if(runner.next.data == cur.data){
						runner.next = runner.next.next;
					}
					else{runner = runner.next;}
				}
				cur = cur.next;
			}	
		}
		
		public static int returnKthLazy(Node n, int k){
			if(n == null){
				return 0;
			}
			int i = returnKthLazy(n.next, k) + 1;//1,2,3,null
			if(i == k){
				System.out.println("got cha-> "+n.data);
			}
			return i;
		}
		
		public static Node returnKthTwoPtrs(Node head, int k){
			Node p1 = head; 
			Node p2 = head;
			for (int i = 0; i<k; i++){
				if(p1 == null) return null;
				p1 = p1.next;
			}
			
			while(p1 != null){
				p1 = p1.next;
				p2 = p2.next;
			}
			return p2;
		}
	
		public static boolean delMid(Node n){
			if(n == null || n.next == null) 
				return false;
			n.data = n.next.data;
			n.next = n.next.next;
			return true;
		}
		
		public static Node partitionStable(Node head, int n){
			Node bs = null;//before start
			Node be = null;//befr end
			Node as = null;//aftr strt
			Node ae = null;
			Node run = head;
			if(run == null) return null;
			
			while(run != null){
				Node next = run.next;
				run.next = null;
				//System.out.println(run.data+"->run.data");
			if(run.data < n){
				
				if(bs == null){
					bs = run;
					be = bs;
				}else{
					be.next = run;
					be = run;
				}
			}else {	// if(run.data >= n.data)
				
				if(as == null){
					as = run;
					ae = as;
					//System.out.println(as.data+"->as.data");
				}else{
					//System.out.println(ae.data+"->ae.data");
					ae.next = run;
					ae = run;
					//System.out.println(as.next.data+"->as.next.data");
				}
			}
			run = next;
			}
			
			if(bs == null){
//				n.next = as;
//				System.out.println(as.next.data+"->as.next.data");
				return as;
			}
				
			be.next = as;
//			System.out.println(be.data+"->be.data");
//			System.out.println(be.next.data+"->be.next.data");
//			System.out.println(as.data+"->as.data");
//			System.out.println(as.next.data+"->as.data");
//			System.out.println(as.next.next.data+"->as.data");
			
		//	n.next = as;
			
			return bs;
		}
		
		public static Node partitionIntuitive(Node n, int i){
			Node head = n;
			Node tail = n;
			if(n == null) return null;
			while(n != null){
				Node next = n.next;//1,2,3,4,5
				//System.out.println(n.data+"->n.data");
				if(n.data < i){
					n.next = head;
					head = n;
				}else{
					tail.next = n;
					System.out.println(tail.data+"->tail.data");
					System.out.println(tail.next.data+"->tail.next.data");
					tail = n;
				}
				n = next;
			}
			tail.next = null;
			return head;
		}
		
		public static Node addListsBackwards(Node l1, Node l2, int carry){
			//1-2-3-null
			//4-5-7-null
			//5-7-0-1
			if ((l1 == null) && (l2 == null) && (carry == 0)){
				return null;
			}
			int sum = carry;
			//Node head = new Node();
			System.out.println("sum "+sum);
			if(l1 != null){
				sum = l1.data +sum;
				System.out.println("sum1 "+sum);
			}
			if(l2 != null){
				sum = l2.data +sum;
				System.out.println("sum2 "+sum);
			}
			carry = sum >= 10?1:0;
			Node head = new Node(sum%10);
			System.out.println("head.data "+head.data);
			if ((l1 != null) || (l2 != null) ){
				System.out.println("--");
				Node next = addListsBackwards(l1==null ? null : l1.next, l2 == null ? null : l2.next, carry);
				head.setNext(next.data);
				System.out.println("next.data "+next.data);
			}
		    return head;
		}
		
		public static Node addLists(Node l1, Node l2, int carry) {
			if (l1 == null && l2 == null && carry == 0) {
	             return null;
			}
				
			int value = carry;
			if (l1 != null) {
				value += l1.data;
			}
			if (l2 != null) {
				value += l2.data;
			}
			Node result = new Node(value % 10);
			if (l1 != null || l2 != null) {
				Node more = addLists(l1 == null ? null : l1.next, 
											   l2 == null ? null : l2.next, 
											   value >= 10 ? 1 : 0);
				result.setNext(more.data);
			}
			return result;
		}
		
		
		static class partialSum{		//when recursive call need more than one resulst from last session, wrapper class needed.
			int carry = 0;
			Node sum = null;
		}
		public static int len(Node l){
			int i = 0;
			while(l!=null){
				i++;
				l = l.next;
			}
			return i;
		}
		
		public static Node padding(Node l, int i){
			Node head = l;
			for(int j = 0; j<i ;j++){
				head = addToFront(head,0);
			}
			return head;
		}
		
		public static Node addToFront(Node head, int i){
			Node add = new Node(i);
			add.next = head;
			return add;
		}
		
		public static partialSum addListsForward(Node l1, Node l2){
			if(l1 == null && l2 == null) return new partialSum();
			partialSum next = addListsForward(l1.next, l2.next);
			int totalThisTime = next.carry + l1.data + l2.data;
			int sum = totalThisTime%10;
			int carry = totalThisTime/10;
			partialSum res = new partialSum();
			res.carry = carry;
			Node sumNode = new Node(sum);
			sumNode =  addToFront(next.sum,sum);
			res.sum = sumNode;
			return res;
		}
		
		public static Node addListsMain(Node l1, Node l2){
			if(len(l1)>len(l2)){
				l2 = padding(l2, len(l1)-len(l2));
			}
			
			if(len(l2)>len(l1)){
				l1 = padding(l1, len(l2)-len(l1));
			}
			
			partialSum sum = addListsForward(l1, l2);
			Node res = sum.sum;
			if (sum.carry == 1){
				res = addToFront(sum.sum,1);
			}
			return res;
		}
		
		public static boolean palioReverse(Node l){
			Node rev = reverse(l);
			return isSame(l, rev);
		}
		
		public static Node reverse(Node l){
			if(l == null) return null;
			Node head = null;
			while (l != null){
				Node copy = new Node(l.data);
				copy.next = head; 
				head = copy;
				l = l.next;
			}
			return head;
		}
		
		public static boolean isSame(Node l1, Node l2){
			if(l1==null || l2 == null){
				return false;
			}
			while (l1 != null && l2 != null){
				if(l1.data != l2.data)
					return false;
				l1 = l1.next;
				l2 = l2.next;
			}
			return ((l1==null) && (l2 == null)) ;
		}
		
		public static void main (String[] args){
			linkedList ll = new linkedList();
			Node head = new Node(3);
//			head.appendToTail(2);
//			head.appendToTail(3);
//			head.appendToTail(4);
//			head.appendToTail(3);
//			head.appendToTail(2);
//			head.appendToTail(1);
			
//			Node forth = new Node(4);
//			third.next = forth;
//			Node fifth = new Node(5);
//			forth.next = fifth;
			Node anotherHead = new Node(3);
//			anotherHead.appendToTail(2);
//			anotherHead.appendToTail(8);
			Node newHead = addListsMain(head, anotherHead);
			if(isSame(head, anotherHead)){//palioReverse(head) == true){
				System.out.println("yay");
			}else
				System.out.println("nahh");
//			System.out.println(padding(anotherHead, len(head)-len(anotherHead)).data);

//			Node newHead = addLists(head, anotherHead, 0);
//			System.out.println(newHead.data);
//			System.out.println(newHead.next.data);
//			System.out.println(newHead.next.next.data);

//			ll.appendToTail(1);		//是谁的property一定要看清
//			ll.appendToTail(2);
//			ll.appendToTail(3234);
//			ll.appendToTail(5);
//			ll.appendToTail(3);
//			ll.appendToTail(4);
			//ll.printList();
			//ll.deletDupNoBuffer(ll.head);
			//ll.printList();
//			int a = 0;
//			a = returnKthLazy(ll.head, 2);
//			System.out.println(a);
			//Node res = returnKthTwoPtrs(ll.head, 4);
			//System.out.println(res.data);
			
//		if(delMid(ll.head))
//			ll.printList();
			
			//Node newHead = partitionStable(ll.head, 43);
//			Node newHead = partitionIntuitive(ll.head, 5);
//			//ll.printList();
//			System.out.println(newHead.data);
//			System.out.println(newHead.next.data);
//			System.out.println(newHead.next.next.data+"middle");
//			System.out.println(newHead.next.next.next.data);
//			System.out.println(newHead.next.next.next.next.data);
			
//			Node test = new Node(2);
//			Node temp = test;
//			temp.next = test;
//			System.out.println(temp+" "+temp.next);
			
	}
}
