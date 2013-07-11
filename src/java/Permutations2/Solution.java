package Permutations2;

import java.util.ArrayList;
import java.util.HashSet;
public class Solution {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
	HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>(permuteImpl(num, 0));
	return new ArrayList<ArrayList<Integer>>(set);
    }

    public ArrayList<ArrayList<Integer>> permuteImpl(int[] num, int idx) {
	if(idx == num.length-1){
	    ArrayList<Integer> x = new ArrayList<Integer>();
	    x.add(num[idx]);
	    ArrayList<ArrayList<Integer>> r = new ArrayList<ArrayList<Integer>>();
	    r.add(x);
	    return r; 
	}else{
	    ArrayList<ArrayList<Integer>> pre = permuteImpl(num, idx + 1);
	    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	    for(ArrayList<Integer> r : pre){
		HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
		for(int i = 0; i <= r.size(); i++){
		    ArrayList<Integer> rclone = (ArrayList<Integer>)r.clone();
		    rclone.add(i, num[idx]);
		    set.add(rclone);
		}
		result.addAll(set);
	    }
	    return result;
	}
    }
}
