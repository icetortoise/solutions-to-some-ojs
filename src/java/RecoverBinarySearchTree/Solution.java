package RecoverBinarySearchTree;

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
    private TreeNode last = null;
    public void recoverTree(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(root == null) return;
        last = new TreeNode(Integer.MIN_VALUE);
	ArrayList<Integer> vals = new ArrayList<Integer>();
	ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
	recoverHelper(root, vals, nodes);
	Collections.sort(vals);
	for(int i = 0; i < vals.size(); i++){
	    nodes.get(i).val = vals.get(i);
	}
    }
    
    public void recoverHelper(TreeNode root, ArrayList<Integer> vals, ArrayList<TreeNode> nodes){
	if(root == null) return;
	recoverHelper(root.left, vals, nodes);
	if(root.val < last.val){
	    vals.add(last.val);
	    vals.add(root.val);
	    nodes.add(last);
	    nodes.add(root);
	}
	last = root;
	recoverHelper(root.right, vals, nodes);
    }
}
