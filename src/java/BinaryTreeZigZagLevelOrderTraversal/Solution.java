package BinaryTreeZigZagLevelOrderTraversal;

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
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	ArrayList<TreeNode> last = new ArrayList<TreeNode>();
	if(root == null) return result;
	ArrayList<Integer> l = new ArrayList<Integer>(); l.add(root.val);
	last.add(root);
	result.add(l);
	boolean zig = false;
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
		if(!zig){
		    Collections.reverse(curVals);
		}
		result.add(curVals);
		last = curNodes;
		zig = !zig;
	    }
	}
	return result;
    }
}
