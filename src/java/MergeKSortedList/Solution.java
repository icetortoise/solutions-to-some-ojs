package MergeKSortedList;

import java.util.ArrayList;
import java.util.Comparator;

import ListNode.ListNode;

public class Solution {
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        // Start typing your Java solution below
        // DO NOT write main() function
	MyPriorityQueue q = new MyPriorityQueue(1000, new ListNodeComparator());
	ListNode result = null, cur = null;
	for(ListNode l : lists){
	    if(l != null)
	        q.add(l);   
	}
	result = q.poll();
	cur = result;
	if(result == null){
	    return result;
	}
	if(cur.next != null)
	    q.add(cur.next);
	while(q.size() > 0){
	    cur.next = q.poll();
	    if(cur.next.next != null)
	        q.add(cur.next.next);
	    cur = cur.next;
	}
	return result;
    }


    class ListNodeComparator implements Comparator<ListNode>{
	public int compare(ListNode x, ListNode y){
	    return x.val - y.val;
	}
    }
    class MyPriorityQueue{
	ListNode[] heap = null;
	ListNodeComparator c = null;
	int idx = -1;
	public MyPriorityQueue(int capacity, ListNodeComparator c){
	    heap = new ListNode[capacity];
	    this.c = c;
	}
	
	public void add(ListNode n){
	    idx++;
	    heap[idx] = n;
	    int i = idx;
	    while(i > 0){
		int parent = (i-1)/2;
		if(c.compare(heap[parent], heap[i]) > 0){
		    ListNode temp = heap[parent];
		    heap[parent] = heap[i];
		    heap[i] = temp;
		    i = parent;
		}else{
		    break;
		}
	    }
	}

	public ListNode poll(){
        if (idx < 0) return null;
	    ListNode result = heap[0];
	    heap[0] = heap[idx];
	    idx--;
        int i = 0;
	    while(2*i + 1 <= idx){
		int left = 2*i + 1;
		int right = 2*i + 2;
		int smallest = i;
		if(c.compare(heap[left], heap[i]) < 0){
		    smallest = left;
		}
		if(c.compare(heap[right], heap[smallest]) < 0 && right <= idx){
		    smallest = right;
		}
		if(smallest == i){
		    break;
		}else{
		    ListNode temp = heap[i];
		    heap[i] = heap[smallest];
		    heap[smallest] = temp;
		    i = smallest;
		}
	    }
        return result;
	}

	public int size(){
	    return idx + 1;
	}
    }


}
