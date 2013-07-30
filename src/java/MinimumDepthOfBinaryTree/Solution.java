package MinimumDepthOfBinaryTree;

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
    public int minDepth(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int depth = 0;
	if(root == null)return depth;
        ArrayList<TreeNode> last = new ArrayList<TreeNode>();
	last.add(root);
	while(true){
	    depth++;
	    ArrayList<TreeNode> newLast = new ArrayList<TreeNode>();
	    for(TreeNode n : last){
		if(n.left == null && n.right == null){
		    return depth;
		}
		if(n.left != null){
		    newLast.add(n.left);
		}
		if(n.right != null){
		    newLast.add(n.right);
		}
	    }
	    last = newLast;
	}
    }
}
