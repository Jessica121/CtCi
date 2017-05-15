package chapter2LL;

class linkedList {

Node head;//why this will solve pointer to head changes problem
	
	static class Node{		//static class?????
	Node next = null;
	int data;
	Node(int d){
		this.data = d;
	}
	}
	
	public void appendToTail(int d){
		Node end = new Node(d);
		Node node = head;
		while(node.next != null){
			node = node.next;
		}
		node.next = end;
	}
	
	public void printList(){
		Node n = head;
		while(n.next != null){
			System.out.println(n.data);
			n = n.next;
		}
		
	}
	
	Node delNode(int d){		//del node with data d
		Node n = head;
		if(n.data == d){
			return head.next;
		}//nil->1->2->3->nil
		while(n.next != null){
			if(n.next.data == d){
				n.next = n.next.next;
			}
			n.next = n;
		}
		return head;
	} 
	
	public static void main (String[] args){
		linkedList ll = new linkedList();
		ll.head = new Node(1);
		ll.appendToTail(2);
		ll.appendToTail(3);
		ll.appendToTail(4);
		ll.printList();
		}
}
