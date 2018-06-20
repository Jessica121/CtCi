package cpter2;

public class SecondPass_PalidromLinkedList {
    /*
    1. reverse the whole list and check if equal.
    2. push first half into stack and pop and check. skip the middle if odd
    3. reverse the later half only and check if two halves are equal
    4. fancy recursion check*
    
    consider the No.3 method first: if odd, fast pointer next is null. slow pointer exactly at the middle, advance by one to get the start of the later half.
                                    if even, fast pointer itself is null, slow pointer at the begin of the later half.
    save the original head.
    reverse the slow and return new head. 
    check untill one of them is null. if the other one is not null, means the list is even. not need to check.
    
    */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        ListNode middle = checkMid(slow, fast);
        ListNode laterHalfHead = reverseLaterHalf(middle);
        return isSame(head, laterHalfHead);
    }
    
    private ListNode checkMid(ListNode l1, ListNode l2) {
        while(l2 != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next.next;
        }
        if(l2 == null) return l1;  // even
        return l1.next;  // odd
    }
    
    private ListNode reverseLaterHalf(ListNode node) {
        ListNode sudo = new ListNode(-1);
        while(node != null) {
            ListNode sudoNext = sudo.next, next = node.next;
            sudo.next = node;
            node.next = sudoNext;
            node = next;
        }
        return sudo.next;
    }
    
    private boolean isSame(ListNode l1, ListNode l2) {
        while(l1 != null && l2 != null) {
            if(l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
    
    /*
    4. fancy recursion check*
    the point is to check current match, then if it does, return the next to be checked by previous recursion call. 
    since it is palidro, the node needs to check its opponent part, which needs to recursion to pass on.
    so iterate thru till middle, in the middle point, return its next if odd, if even return itself.
    then previous function stack can check itself with the return value of the recursion call.
    if match, return recursion node's next.
    for its previous to check.
    if does not match, return null.
    so if previou check the recursion is null, it pass on null. means not match.
    
    */
    public boolean isPalindromeRecursion(ListNode head) {
        Ret mid = getMid(head);
        return check(head, mid).bool;
    }
    
    private Ret check(ListNode head, Ret mid) {
        if(head == mid.node) return new Ret(mid.bool ? mid.node : mid.node.next, true);
        Ret next = check(head.next, mid);
        if(!next.bool || head.val != next.node.val) return new Ret(head, false);
        return new Ret(next.node.next, true);
    }
    
    private class Ret {
        ListNode node;
        boolean bool;
        public Ret(ListNode node, boolean bool) {
            this.bool = bool;
            this.node = node;
        }
    }
    
    private Ret getMid(ListNode node) {
        ListNode slow = node, fast = node;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast == null) return new Ret(slow, true);
        return new Ret(slow, false);
    }
}
