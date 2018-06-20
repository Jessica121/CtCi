package cpter2;

public class SecondPass_IntersectionOfTwoLists {
    /*
    go to the point with same length.
    recursively check.
    check if this two nodes are same and next recursion result is itself.next then return itself.
    if node is null, return null.
    else if not same, return next recursion call result, pass on in function stack.
    
    */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int diff = getDiff(headA, headB);
        // make the first one longer
        if(diff < 0) {
            ListNode temp = headA;
            headA = headB;
            headB = temp;
            diff = -diff;
        }
        while(diff-- > 0) headA = headA.next;
        return find(headA, headB);
    }
    
//    private ListNode find(ListNode l1, ListNode l2) {
//        if(l1 == null && l2 == null) return null;
//        ListNode next = find(l1.next, l2.next);
//        if(l1 == l2 && next == l1.next) return l1;
//        else return next;
//    }
    
    /*
    Since it is a single linked list, so cannot branch out further if already meet.
    
    */
    private ListNode find(ListNode l1, ListNode l2) {
        while(l1 != l2) {
            if(l1 == null || l2 == null) return null;
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }
    
    private int getDiff(ListNode node1, ListNode node2) {
        int cnt1 = 0, cnt2 = 0;
        ListNode l1 = node1, l2 = node2;
        while(l1 != null || l2 != null) {
            if(l1 != null) {
                cnt1++;
                l1 = l1.next;
            }
            if(l2 != null) {
                cnt2++;
                l2 = l2.next;
            }
        }
        return cnt1 - cnt2;
    }
}
