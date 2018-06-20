package cpter2;

public class SecondPass_PartitionLinkedList {
    /*
    stable: end left and start right. append the end left and start right and return the head.
    if(node.val < p) {
        if end left == null, update head.
            set end left
        else save end left next, insert, update end left.
    else 
        if right start == null, set right start
        else save right start next insert, set right start
        
        afterwards set the end left next = right start return head.
    }
    
    */
    public ListNode partition(ListNode head, int x) {
        ListNode leftStart = null, leftEnd = null, rightStart = null, rightEnd = rightStart;
        while(head != null) {
            ListNode next = head.next;
            head.next = null;
            if(head.val < x) {
                if(leftEnd == null) leftStart = head;
                else leftEnd.next = head;
                leftEnd = head;
            } else {
                if(rightStart == null) {
                    rightStart = head;
                    rightEnd = rightStart;
                } else rightEnd.next = head;
                rightEnd = head;
            }
            head = next;
        }
        if(leftStart == null) return rightStart;
        leftEnd.next = rightStart;
        return leftStart;
    }
}
