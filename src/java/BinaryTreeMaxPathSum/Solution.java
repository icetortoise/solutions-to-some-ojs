package BinaryTreeMaxPathSum;

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
    public int maxPathSum(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[] result = pathSumImpl(root);
	return result[0];
    }
    
    public int[] pathSumImpl(TreeNode root){
	int[] result = new int[2];
	result[0] = Integer.MIN_VALUE; result[1] = Integer.MIN_VALUE;
	if(root == null){
	    return result;
	}else{
	    int[] lr = pathSumImpl(root.left);
	    int[] rr = pathSumImpl(root.right);
	    if(lr[1] > rr[1]){
		result[1] = lr[1]>0? lr[1] + root.val : root.val;
	    }else{
		result[1] = rr[1]>0? rr[1] + root.val : root.val;
	    }
	    // result[0] = max(result[1], lr[1] + root.val + rr[1], lr[0], rr[0])
	    int turn = (lr[1] > 0 && rr[1] > 0) ? lr[1] + root.val + rr[1] : result[1];
	    if(turn > result[1]){
		if(turn > lr[0]){
		    result[0] = java.lang.Math.max(turn, rr[0]);
		}else{
		    result[0] = java.lang.Math.max(lr[0], rr[0]);
		}
	    }else{
		if(result[1] > lr[0]){
		    result[0] = java.lang.Math.max(result[1], rr[0]);
		}else{
		    result[0] = java.lang.Math.max(lr[0], rr[0]);
		}
	    }
	    
	}
	return result;
    }
}
