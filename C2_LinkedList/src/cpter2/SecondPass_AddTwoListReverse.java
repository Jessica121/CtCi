package cpter2;

public class SecondPass_AddTwoListReverse {
    /*
    add two, if both none null, add flag. update flag. check or node.
    then if out of loop the flag is 1, append one more one.
    append from a head. 
    
    (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
         ListNode head = null, iterater = head;
        int flag = 0;
        while(l1 != null || l2 != null) {
            int sum = flag;
            if(l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            ListNode node = new ListNode(sum % 10);
            if(head == null) {
                head = node;
                iterater = head;
            } else {
                iterater.next = node;
                iterater = node;
            }
            flag = sum / 10;
        }
        if(flag == 1) iterater.next = new ListNode(1);
        return head;
    }
}
