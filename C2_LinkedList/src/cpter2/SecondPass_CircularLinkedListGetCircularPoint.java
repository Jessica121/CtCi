package cpter2;

import cpter2.linkedList.Node;

public class SecondPass_CircularLinkedListGetCircularPoint {
	/*
	 * Given a linked list which contains a circle, return at which point it collides. not necessary the head.
	 * 
	 * Loop detection problems requires math analysis, at least know to the fact that, 
	 * if using slow pointer moving 1 unit at a time and fast 2 units at a time.
	 * and if both start at the same starting point and moved k steps, the fast will be exactly ahead of slow by k steps. (not 2k, thats its total steps, slow moved k as well)
	 * so start from the head, we dont know that when the entry point of the circle starts, but we can use math to derive relationships between slow and fast.
	 * so imagine after k steps, the slow pointer is at the begin of the circular point, then the fast pointer will be k more steps already inside the loop.
	 * or circle loop size - k steps behind the loop. since it is a loop, everything is relative.
	 * it takes the slow pointer loop size - k more steps for the fast pointer to catch up with the slow pointer.
	 * so the slow pointer at collision, made loop size steps in total.
	 * the start of the loop backwards to the collision point distance equals the head of the list to the distance.
	 * so when meet, have another slow pointer, move two slow at the same time, the node they meet is the start of loop point.
	 * 
	 * when meet, set slow to its next, and advance the head at same speed.
	 * 
	 * */
	
	public static ListNode circularStartingPoint(ListNode head) {
		ListNode slow = head, fast = head;
		do {
			slow = slow.next;
			fast = fast.next.next;
		} while(slow != fast);
		while(slow != head) {
			slow = slow.next;
			head = head.next;
		}
		return slow;
	}
	
	public static void main (String[] args){
		ListNode head = new ListNode(1), startPoint = null;
		ListNode node = head;
		for(int i = 2; i < 7; i++) {
			node.next = new ListNode(i);
			node = node.next;
			if(i == 3) startPoint = node;
		}
		node.next = startPoint;
		System.out.println(circularStartingPoint(head).val);
	}
}
