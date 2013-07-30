package PathSum2;

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
    private ArrayList<ArrayList<Integer>> result = null;
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
	result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
	if(root == null)return result;
	path.add(root.val);
	pathSumImpl(root, sum, path);
	return result;
    }
    public void pathSumImpl(TreeNode root, int sum, 
						     ArrayList<Integer> path) {
        if(root.left == null && root.right == null && sum == root.val){
	    result.add(path);
	}else if(root.left == null && root.right == null && sum != root.val){
	    return;
	}else{
	    if(root.left != null){
		ArrayList<Integer> np = new ArrayList<Integer>(path);
		np.add(root.left.val);
		pathSumImpl(root.left , sum - root.val, np);
	    }
	    if(root.right != null){
		ArrayList<Integer> np = new ArrayList<Integer>(path);
		np.add(root.right.val);
		pathSumImpl(root.right , sum - root.val, np);
	    }
	}
    }
}
