package ValidateBinarySearchTree;

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
import java.util.ArrayList;
public class Solution {
    public boolean isValidBST(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Integer> ll = new ArrayList<Integer>();
	return isValidImpl(root, ll);
    }

    public boolean isValidImpl(TreeNode root, ArrayList<Integer> ll){
	if(root == null) return true;
	boolean left = isValidImpl(root.left, ll);
	boolean current = true;
	if(ll.size() > 0){
	    current = root.val > ll.get(ll.size()-1);
	}
	ll.add(root.val);
	if(current){
	    return left && isValidImpl(root.right, ll);
	}else{
	    return false;
	}
    }
}
