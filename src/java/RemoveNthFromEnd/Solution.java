package RemoveNthFromEnd;

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
    // public ListNode removeNthFromEnd(ListNode head, int n) {
    //     // Start typing your Java solution below
    //     // DO NOT write main() function
    // 	ListNode cur = head, a = null, b = null;
    // 	int count = 1;
    // 	while(cur != null){
    // 	    if(count == n){
    // 		b = head;
    // 	    }else if(count > n){
    // 		a = b;
    // 		b = b.next;
    // 	    }
    // 	    cur = cur.next;
    // 	    count++;
    // 	}
    // 	if(a == null && b != null){
    // 	    head = b.next;
    // 	}else if(a != null){
    // 	    a.next = b.next;
    // 	}
    // 	return head;
    // }
}
