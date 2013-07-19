package RemoveDuplicatesFromSortedList2;

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
    public ListNode deleteDuplicates(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(head == null)return null;
	ListNode dummy = new ListNode(0);
	dummy.next = head;
        ListNode lastNonDup = dummy, cur = head, start = head;
	int val = head.val; boolean inDup = false;
	while(cur.next != null){
	    cur = cur.next;
	    if(cur.val != val){
		if(!inDup){
		    lastNonDup.next = start;
		    lastNonDup = start;
		}
		inDup = false;
		start = cur;
		val = cur.val;
	    }else{
		inDup = true;
	    }
	}
	if(inDup){
	    lastNonDup.next = null;
	}else{
	    lastNonDup.next = cur;
	}
	return dummy.next;
    }
}
