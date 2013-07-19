package RemoveDuplicatesFromSortedList;

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
        ListNode toBeCon = head, cur = head;
	int val = head.val;
	while(cur.next != null){
	    cur = cur.next;
	    if(cur.val != val){
		val = cur.val;
		toBeCon.next = cur;
		toBeCon = cur;
	    }else{
		continue;
	    }
	}
	toBeCon.next = null;
	return head;
    }
}
