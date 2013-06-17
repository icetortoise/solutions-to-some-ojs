package AddTwoNumbers;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ListNode result = new ListNode(0);
	ListNode node = result;
	ListNode pre = node;
	int running = 0;
	while(l1 != null || l2 != null){
	    int x1 = 0, x2 = 0;
	    if(l1 != null){
		x1 = l1.val;
		l1 = l1.next;
	    }
	    if(l2 != null){
		x2 = l2.val;
		l2 = l2.next;
	    }
	    int r = running + x1 + x2;
	    running = r / 10;
	    int value = r % 10;
	    node.val = value;
	    node.next = new ListNode(running);
	    pre = node;
	    node = node.next;
	}
	if(node.val == 0){
	    pre.next = null;
	}
	return result;
    }
}
