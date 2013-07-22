package PartitionList;

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

import ListNode.ListNode;

public class Solution {
    public ListNode partition(ListNode head, int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ListNode left = new ListNode(-1);
        ListNode right = new ListNode(-1);
	ListNode lTail = left, rTail = right;
	while(head != null){
	    if(head.val < x){
		lTail.next = head;
		lTail = head;
		head = head.next;
		lTail.next = null;
	    }else{
		rTail.next = head;
		rTail = head;
		head = head.next;
		rTail.next = null;
	    }
	}
	lTail.next = right.next;
	return left.next;
    }
}
