package ReverseNodesInK;

import ListNode.ListNode;

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
    public ListNode reverseKGroup(ListNode head, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
	assert k >= 0;
	if(k == 1) return head;
	ListNode dummy = new ListNode(0);
	ListNode tail = null;
	dummy.next = head;
	head = dummy;
	tail = dummy;
	int i = 0;
	while(tail != null){
	    i++;
	    tail = tail.next;
	    if(i%k == 0 && tail != null){
		head = reverse(head, tail);
		tail = head;
	    }
	}
	return dummy.next;
    }
    private ListNode reverse(ListNode head, ListNode tail){
	ListNode result = head.next;
	ListNode cur = head.next.next;
	ListNode pre = head.next;
	result.next  = tail.next;
	ListNode temp = null;
	while(cur != tail){
	    temp = cur.next;
	    cur.next = pre;
	    pre = cur;
	    cur = temp;
	}
	tail.next = pre;
	head.next = tail;
	return result;
    }
}
