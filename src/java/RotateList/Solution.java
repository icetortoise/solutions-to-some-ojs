package RotateList;

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
    public ListNode rotateRight(ListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ListNode cur = head;
	ListNode newTail = head;
	if(head == null || n == 0){
	    return head;
	}
	for(int i = 0; i < n; i++){
	    if(cur.next == null){
		cur = head;
	    }else{
		cur = cur.next;
	    }
	}
	while(cur.next != null){
	    cur = cur.next;
	    newTail = newTail.next;
	}
	if(newTail.next == null){
	    return head;
	}else{
	    cur.next = head;
	    head = newTail.next;
	    newTail.next = null;
	}
	return head;
    }
}
