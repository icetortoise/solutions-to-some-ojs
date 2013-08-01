package PopulatingNextRightPointersInEachNode2;

import TreeLinkNode.TreeLinkNode;

public class Solution {
    public void connect(TreeLinkNode root){
	connectLeftRight(root);
	connectImpl(root);
    }
    public void connectLeftRight(TreeLinkNode root){
	if(root == null) return;
	if(root.left != null && root.right != null){
	    root.left.next = root.right;
	}
	connectLeftRight(root.left);
	connectLeftRight(root.right);
    }
    public void connectImpl(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(root == null) return;
	TreeLinkNode onRight = null;
	TreeLinkNode n = root.next;
	while(n != null){
	    onRight = n.left != null ? n.left : (n.right != null ? n.right : null);
	    if(onRight!=null){
		break;
	    }else{
		n = n.next;
	    }
	}
	if(root.left != null){
	    root.left.next = root.right != null? root.right : onRight;
	}
	if(root.right != null){
	    root.right.next = onRight;
	}
	connectImpl(root.right);
	connectImpl(root.left);
    }

    // public void connectImpl(TreeLinkNode cur, TreeLinkNode parent){
	
    // }
}
