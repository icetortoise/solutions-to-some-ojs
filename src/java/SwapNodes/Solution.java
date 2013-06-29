package SwapNodes;

import ListNode.ListNode;

public class Solution {
    public ListNode swapPairs(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ListNode result = head;
        ListNode cursor = head;
	ListNode precursor = null;
	ListNode temp = null;
	while(cursor != null){
	    if(cursor.next != null){
		if(precursor != null){
		    precursor.next = cursor.next;
		}else{
		    result = cursor.next;
		}
		temp = cursor.next.next;
		cursor.next.next = cursor;
		cursor.next = temp;
		precursor = cursor;
		cursor = cursor.next;
	    }else{
		cursor = cursor.next;
	    }
	}
	return result;
    }
}
