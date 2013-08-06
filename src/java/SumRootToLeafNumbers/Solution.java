package SumRootToLeafNumbers;

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
    public int sumNumbers(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(root == null) return 0;
        ArrayList<TreeNode> path = new ArrayList<TreeNode>();
	return sumImpl(root, path);
    }
    
    public int sumImpl(TreeNode root, ArrayList<TreeNode> path){
	path.add(root);
	if(root.left == null && root.right == null){
	    return valueOf(path);
	}else if(root.left == null){
	    return sumImpl(root.right, path);
	}else if(root.right == null){
	    return sumImpl(root.left, path);
	}else{
	    ArrayList newPath = new ArrayList<TreeNode>(path);
	    return sumImpl(root.left, path) + sumImpl(root.right, newPath);
	}
    }

    public int valueOf(ArrayList<TreeNode> path){
	int m = 1;
	int val = 0;
	for(int i = path.size()-1; i >= 0; i--){
	    val += path.get(i).val * m;
	    m *= 10;
	}
	return val;
    }
}
