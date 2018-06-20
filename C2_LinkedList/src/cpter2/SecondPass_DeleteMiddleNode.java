package cpter2;

public class SecondPass_DeleteMiddleNode {
    /*
    modify the current node's value into next's value. delete next.
    cur.next = nur.next.next;
    if cur.next.next = null, will be null also.
    
    */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
