package PopulatingNextRightPointersInEachNode;

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
import TreeLinkNode.TreeLinkNode;

public class Solution {
    public void connect(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(root == null) return;
	if(root.left != null && root.right != null){
	    root.left.next = root.right;
	    root.right.next = root.next == null? null : root.next.left;
	    connect(root.left);
	    connect(root.right);
	}
    }

    // public void connectImpl(TreeLinkNode cur, TreeLinkNode parent){
	
    // }
}
