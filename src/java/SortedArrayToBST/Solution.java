package SortedArrayToBST;

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
    public TreeNode sortedArrayToBST(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(num.length == 0) return null;
        return helper(num, 0, num.length - 1);
    }

    public TreeNode helper(int[] num, int s, int e){
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
