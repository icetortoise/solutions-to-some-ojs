package PathSum;

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
    public boolean hasPathSum(TreeNode root, int sum) {
	if(root == null) return false;
	return hasPathSumImpl(root, sum);
    }

    public boolean hasPathSumImpl(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root.left == null && root.right == null && sum == root.val){
	    return true;
	}else if(root.left == null && root.right == null && sum != root.val){
	    return false;
	}else{
	    return (root.left != null ? hasPathSumImpl(root.left , sum - root.val) : false) || 
		(root.right != null ? hasPathSumImpl(root.right , sum - root.val) : false);
	}
    }
}
