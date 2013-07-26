package BinaryTreeInorderTranversal;

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
import java.util.Stack;
public class Solution {

    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Integer> result = new ArrayList<Integer>();
	if(root == null) 
	    return result;
	Stack<TreeNode> s = new Stack<TreeNode>();
	s.push(root);
	while(!s.empty()){
	    TreeNode n = s.peek();
	    if(n.left != null){
		s.push(n.left);
		n.left = null;
	    }else if(n.right != null){
		s.pop();
		result.add(n.val);
		s.push(n.right);
	    }else{
		s.pop();
		result.add(n.val);
	    }
	}
	return result;
    }


    public ArrayList<Integer> inorderTraversalRec(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Integer> result = new ArrayList<Integer>();
        inorderImpl(root, result);
	return result;
    }

    public void inorderImpl(TreeNode node, ArrayList<Integer> result){
	if(node == null){
	    return;
	}else{
	    inorderImpl(node.left, result);
	    result.add(node.val);
	    inorderImpl(node.right, result);
	}
    }
}
