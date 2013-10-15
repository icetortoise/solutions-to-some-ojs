package BinarySearchTreeST;

import java.util.List;
import java.util.ArrayList;

public class BinarySearchTreeST<K extends Comparable,V>{
    // put; get; del; max; min; succ; pred; range
    public enum Color {RED, BLACK}

    public class TreeNode{
	public K key;
	public V val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	public Color c = Color.BLACK;
	public String toString(){
	    return (key + " : "+ val);
	}

	public TreeNode(K key, V val, Color c){
	    this.key = key;
	    this.val = val;
	    this.c = c;
	}
	public TreeNode(){
	    //pass
	}

    }
    
    public TreeNode root;
    
    public BinarySearchTreeST(){
	root = null;
    }

    public void put(K key, V val){
	put(key, val, root);
    }
    
    private void put(K key, V val, TreeNode root){
	if(root == null){
	    root = new TreeNode();
	    root.key = key; root.val = val; 
	    this.root = root;
	}else{
	    if(root.key.compareTo(key) < 0){
		if(root.right == null){
		    root.right = new TreeNode();
		    root.right.key = key;
		    root.right.val = val;
		    root.right.parent = root;
		}else{
		    put(key, val, root.right);
		}
	    }else if(root.key.compareTo(key) > 0){
		if(root.left == null){
		    root.left = new TreeNode();
		    root.left.key = key;
		    root.left.val = val;
		    root.left.parent = root;
		}else{
		    put(key, val, root.left);
		}
	    }else{
		root.val = val;
	    }
	}
    }

    private int recursion = 0;
    public V get(K key){
	recursion = 0;
	TreeNode n = getNode(key, root);
	System.out.println("recursion: " + recursion);
	return n == null ? null : n.val;
    }
    
    public TreeNode getNode(K key, TreeNode node){
	recursion++;
	if(node == null){
	    return null;
	}else{
	    if(node.key.compareTo(key) < 0){
		return getNode(key, node.right);
	    }
	    else if(node.key.compareTo(key) > 0){
		return getNode(key, node.left);
	    }
	    else{
		return node;
	    }
	}
    }
    
    public boolean delete(K key){
	return delete(key, root);
    }
    
    public boolean delete(K key, TreeNode node){
	if(node == null){
	    return false;
	}else{
	    if(node.key.compareTo(key) < 0){
		return delete(key, node.right);
	    }
	    else if(node.key.compareTo(key) > 0){
		return delete(key, node.left);
	    }
	    else{
		TreeNode forParent = null;
		TreeNode rightDeepest = null;
		if(node.left != null){
		    forParent = node.left;
		    if(node.right != null){
			rightDeepest = node.left;
			while(rightDeepest.right != null){
			    rightDeepest = rightDeepest.right;
			}
			rightDeepest.right = node.right;
		    }
		}else if(node.right != null){
		    forParent = node.right;
		}
		if(forParent != null){
		    forParent.parent = node.parent;
		}
		if(node.parent == null){
		    this.root = forParent;
		}else{
		    if(node == node.parent.left){
			node.parent.left = forParent;
		    }else{
			node.parent.right = forParent;
		    }
		}
		return true;
	    }
	}
    }
    
    public TreeNode max(){
	return max(root);
    }
    public TreeNode max(TreeNode n){
	if(n == null){
	    return n;
	}else{
	    while(n.right != null){
		n = n.right;
	    }
	    return n;
	}
    }
    public TreeNode min(){
	return min(root);
    }
    public TreeNode min(TreeNode n){
	if(n == null){
	    return n;
	}else{
	    while(n.left != null){
		n = n.left;
	    }
	    return n;
	}
    }

    public TreeNode succ(K key){
	return succ(key, root, null, true);
    }
    public TreeNode pred(K key){
	return pred(key, root, null ,true);
    }
    
    public TreeNode succ(K key, TreeNode node, TreeNode parent, boolean leftC){
	TreeNode p = parent;
	TreeNode n = node;
	if(n == null){
	    if(parent == null){
		return null;
	    }else if(leftC){
		return parent;
	    }else{
		return succ(parent.key);
	    }
	}else{
	    if(n.key.compareTo(key) < 0){
		return succ(key, n.right, n, false);
	    }else if(n.key.compareTo(key) > 0){
		return succ(key, n.left, n, true);
	    }else{
		if(n.right!=null){
		    return min(n.right);
		}else{
		    while(n != null && n.parent != null && n.parent.left != n){
			n = n.parent;
		    }
		    return n == null ? n : n.parent;
		}
	    }
	}	
    }

    public TreeNode pred(K key, TreeNode node, TreeNode parent, boolean leftC){
	TreeNode p = parent;
	TreeNode n = node;
	if(n == null){
	    if(parent == null){
		return null;
	    }else if(leftC){
		return pred(parent.key);
	    }else{
		return parent;
	    }
	}else{
	    if(n.key.compareTo(key) < 0){
		return pred(key, n.right, n, false);
	    }else if(n.key.compareTo(key) > 0){
		return pred(key, n.left, n, true);
	    }else{
		if(n.left!=null){
		    return max(n.left);
		}else{
		    while(n != null && n.parent != null && n.parent.right != n){
			n = n.parent;
		    }
		    return n == null ? n : n.parent;
		}
	    }
	}	
    }

    public List<V> range(K keyS, K keyE){
	return range(keyS, keyE, root);
    }

    public List<V> range(K keyS, K keyE, TreeNode n){
	assert keyS.compareTo(keyE) <= 0;
	TreeNode keyNode = getNode(keyS, n);
	if(keyNode == null){
	    keyNode = succ(keyS);
	}
	if(keyNode == null){
	    return null;
	}
	ArrayList<V> result = new ArrayList<V>();
	while(keyNode != null && keyNode.key.compareTo(keyE) <= 0){
	    result.add(keyNode.val);
	    keyNode = succ(keyNode.key);
	}
	return result;
    }
}
