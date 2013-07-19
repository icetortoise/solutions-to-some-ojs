package MergeTwoSortedList;
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ListNode head = new ListNode(-1);
	ListNode cur = head;
	while(l1!=null && l2!=null){
	    if(l1.val <= l2.val){
		cur.next = l1;
		cur = l1;
		l1 = l1.next;
	    }else{
		cur.next = l2;
		cur = l2;
		l2 = l2.next;
	    }
	}
	if(l1 == null){
	    cur.next = l2;
	}else{
	    cur.next = l1;
	}
	return head.next;
    }
}
