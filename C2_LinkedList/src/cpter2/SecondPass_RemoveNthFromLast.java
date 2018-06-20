package cpter2;

public class SecondPass_RemoveNthFromLast {
    /*
    move the first one n steps frist: 0 .. n, if the node reached null, return null. not valid.
    then move two pointers together, record the prev of the first pointer
    when the second next is null, current of first is the first element, remove the prev element
    if prev is null, means delete the head. return head.next.
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, prev = null, slow = head;
        for(int i = 1; i < n; i++) {
            if(fast == null) return null;
            fast = fast.next;
        }
        while(fast != null && fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        if(prev == null) return head.next;
        prev.next = slow.next;
        return head;
    }
}
