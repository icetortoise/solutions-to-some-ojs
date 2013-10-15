package RedBlackTreeST;

import java.util.List;
import java.util.ArrayList;
import BinarySearchTreeST.BinarySearchTreeST;

public class RedBlackTreeST<K extends Comparable,V> extends BinarySearchTreeST{
    public boolean isRed(TreeNode n ){
	if(n == null) return false;
	return n.c == Color.RED;
    }
    public boolean isBlack(TreeNode n ){
	if(n == null) return true;
	return n.c == Color.BLACK;
    }

    public void putRB(K key, V val)
    {  // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
        root.c = Color.BLACK;
    }
    private TreeNode put(TreeNode h, K key, V val)
    {
        if (h == null)  // Do standard insert, with red link to parent.
	    return new TreeNode(key, val, Color.RED);
        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left,  key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        if (isRed(h.right) && !isRed(h.left))    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);
	return h; 
    }
    
    public TreeNode rotateLeft(TreeNode n){
	TreeNode x = n.right;
	n.right = x.left;
	x.left = n;
	x.c = n.c;
	n.c = Color.RED;
	return x;
    }

    public TreeNode rotateRight(TreeNode n){
	TreeNode x = n.left;
	n.left = x.right;
	x.right = n;
	x.c = n.c;
	n.c = Color.RED;
	return x;
    }
    
    public void flipColors(TreeNode n){
	n.c = Color.RED;
	n.left.c = Color.BLACK;
	n.right.c = Color.BLACK;
    }
}
