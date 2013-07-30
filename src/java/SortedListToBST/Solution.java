package SortedListToBST;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import TreeNode.TreeNode;
import ListNode.ListNode;
import java.util.ArrayList;

public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> buf = new ArrayList<Integer>();
	while(head != null){
	    buf.add(head.val);
	    head = head.next;
	}
	return sortedArrayToBST(buf.toArray(new Integer[0]));
    }

    public TreeNode sortedArrayToBST(Integer[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(num.length == 0) return null;
        return helper(num, 0, num.length - 1);
    }

    public TreeNode helper(Integer[] num, int s, int e){
	if(s > e){return null;}
	if(s == e){
	    return new TreeNode(num[s]);
	}
	int mid = (e - s + 1)/2;
	TreeNode left = helper(num, s, s + mid - 1);
	TreeNode right = helper(num, s+mid+1, e);
	TreeNode root = new TreeNode(num[s+mid]);
	root.left = left; root.right = right;
	return root;
    }

}
