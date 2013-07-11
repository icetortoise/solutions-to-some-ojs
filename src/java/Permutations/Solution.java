package Permutations;

import java.util.ArrayList;
public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
	return permuteImpl(num, 0);
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
		for(int i = 0; i <= r.size(); i++){
		    ArrayList<Integer> rclone = (ArrayList<Integer>)r.clone();
		    rclone.add(i, num[idx]);
		    result.add(rclone);
		}
	    }
	    return result;
	}
    }
}
