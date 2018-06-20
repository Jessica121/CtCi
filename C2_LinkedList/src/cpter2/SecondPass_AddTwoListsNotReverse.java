package cpter2;

public class SecondPass_AddTwoListsNotReverse {
    /*
    Append the shorter ones with zero, recursively add.
    for each level, add two nodes value and modify directly on the next recursion call's head, which will be the flag.
    then append the flag and return the flag.
    out of the function, check if flag == 0, return its next.
    if two nodes are null, return null. then precious recursion check before use the result. if next == null, then dont use it.
    
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int diff = checkDiff(l1, l2);
        if(diff < 0) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
            diff = -diff;
        }
        while(diff > 0) {
            ListNode zero = new ListNode(0);
            zero.next = l2;
            l2 = zero;
            diff--;
        }
        ListNode head = add(l1, l2);
        if(head == null) return null;
        if(head.val == 0) return head.next;
        else return head;
    }
    
    private ListNode add(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        ListNode flag = new ListNode(0);
        ListNode next = add(l1.next, l2.next);
        if(next == null) next = new ListNode(0);
        int sum = l1.val + l2.val + next.val;
        next.val = sum % 10;
        flag.val = sum / 10;
        flag.next = next;
        return flag;
    }
    
    private int checkDiff(ListNode l1, ListNode l2) {
        // if > 0, then l1 longer. < 0 l2.
        int cnt1 = 0, cnt2 = 0;
        ListNode node1 = l1, node2 = l2;
        while(node1 != null || node2 != null) {
            if(node1 != null) {
                cnt1++;
                node1 = node1.next;
            }
            if(node2 != null) {
                cnt2++;
                node2 = node2.next;
            }
        }
        return cnt1 - cnt2;
    }
}
