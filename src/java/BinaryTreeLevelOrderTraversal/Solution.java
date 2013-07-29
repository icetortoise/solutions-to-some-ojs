package BinaryTreeLevelOrderTraversal;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.*;
import TreeNode.TreeNode;
public class Solution {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	ArrayList<TreeNode> last = new ArrayList<TreeNode>();
	if(root == null) return result;
	ArrayList<Integer> l = new ArrayList<Integer>(); l.add(root.val);
	last.add(root);
	result.add(l);
	while(true){
	    ArrayList<TreeNode> curNodes = new ArrayList<TreeNode>();
	    ArrayList<Integer> curVals = new ArrayList<Integer>();
	    for(TreeNode upper : last){
		if(upper.left != null){
		    curNodes.add(upper.left);
		    curVals.add(upper.left.val);
		}
		if(upper.right != null){
		    curNodes.add(upper.right);
		    curVals.add(upper.right.val);
		}
	    }
	    if(curNodes.size() == 0){
		break;
	    }else{
		result.add(curVals);
		last = curNodes;
	    }
	}
	return result;
    }
}
