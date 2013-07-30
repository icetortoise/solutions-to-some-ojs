package FlattenBinaryTreeToLinkedList;
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
public class Solution {
    public void flatten(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null) return;
	flattenWithTail(root);
    }
    public TreeNode flattenWithTail(TreeNode current){
	if(current.left == null && current.right == null){
	    return current;
	}else {
	    TreeNode leftTail = null, rightTail = null;
	    if(current.left != null){
		leftTail = flattenWithTail(current.left);
	    }
	    if(current.right != null){
		rightTail = flattenWithTail(current.right);
	    }
	    if(leftTail == null){
		return rightTail == null? current : rightTail;
	    }else{
		leftTail.right = current.right;
		current.right = current.left;
		current.left = null;
		return rightTail == null? leftTail : rightTail;
	    }
	}
    }
}
