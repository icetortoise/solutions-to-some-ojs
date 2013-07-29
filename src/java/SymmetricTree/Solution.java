package SymmetricTree;

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
import java.util.*;
public class Solution {

    public boolean isSymmetric(TreeNode root) {
	if(root == null)return true;
	if(root.left == null && root.right == null){
	    return true;
	}else if(root.left == null || root.right == null){
	    return false;
	}
	Stack<TreeNode> s1 = new Stack<TreeNode>();
	Stack<TreeNode> s2 = new Stack<TreeNode>();
	TreeNode left = root.left;
	TreeNode right = root.right;
	s1.push(left);
	s2.push(right);
	while(!s1.empty() && !s2.empty()){
	    left = s1.pop();
	    right = s2.pop();
	    if(left.val != right.val){
		return false;
	    }else{
		if(left.left == null || right.right == null){
		    if(left.left != right.right){
			return false;
		    }
		}
		if(left.right == null || right.left == null){
		    if(left.right != right.left){
			return false;
		    }
		}
		if(left.left != null)
		    s1.push(left.left);
		if(left.right != null)
		    s1.push(left.right);
		if(right.right != null)
		    s2.push(right.right);
		if(right.left != null)
		    s2.push(right.right);
	    }
	}
	return (s1.size() == s2.size());
    }

    public boolean isSymmetricRec(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(root == null)return true;

	return isMirror(root.left, root.right);
    }
    
    public boolean isMirror(TreeNode n1, TreeNode n2){
	if(n1 == null && n2 == null){
	    return true;
	}else if(n1 == null || n2 == null){
	    return false;
	}else{
	    return (n1.val == n2.val && isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left));
	}
    }
}
