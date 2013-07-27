package UniqueBinarySearchTrees2;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */

import TreeNode.TreeNode;
import java.util.ArrayList;

public class Solution {
    public ArrayList<TreeNode> generateTrees(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<TreeNode>> trees = new ArrayList<ArrayList<TreeNode>>();
	ArrayList<TreeNode> mzero = new ArrayList<TreeNode>();
	mzero.add(null);
	trees.add(mzero);
	for(int i = 1; i <= n; i++){
	    ArrayList<TreeNode> treesOfN = new ArrayList<TreeNode>();
	    for(int j = 0; j < i; j++){
		for(TreeNode nodeL : trees.get(j)){
		    for(TreeNode nodeR : trees.get(i - j - 1)){
			TreeNode root = new TreeNode(j+1);
			root.left = nodeL;
			root.right = cloneAndAdd(nodeR, j+1);
			treesOfN.add(root);
		    }
		}
	    }
	    trees.add(treesOfN);
	}
	return trees.get(n);
    }
    // to make sure in-order will be 1..n
    public TreeNode cloneAndAdd(TreeNode root, int n){
	if(root == null) return null;
	TreeNode newRoot = new TreeNode(root.val + n);
	newRoot.left = cloneAndAdd(root.left, n);
	newRoot.right = cloneAndAdd(root.right, n);
	return newRoot;
    }
}
