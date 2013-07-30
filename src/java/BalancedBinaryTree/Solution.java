package BalancedBinaryTree;

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
    public boolean isBalanced(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(root == null)return true;
	int leftH = height(root.left);
	int rightH = height(root.right);
	if(leftH >= 0 && rightH >=0 && Math.abs(leftH - rightH) <= 1){
	    return true;
	}else{
	    return false;
	}
    }
    
    public int height(TreeNode root){
	if(root == null) return 0;
	int leftH = height(root.left);
	int rightH = height(root.right);
	if(leftH >= 0 && rightH >=0 && Math.abs(leftH - rightH) <= 1){
	    return leftH>rightH? leftH+1 : rightH+1;
	}else{
	    return -1;
	}
    }
}
