package CombinationSum;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
	Arrays.sort(candidates);
	for(int i = 0, j = candidates.length - 1; i < j; i++, j--){
	    int temp = candidates[i];
	    candidates[i] = candidates[j];
	    candidates[j] = temp;
	}
	if(candidates.length == 0)
	    return new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> result = sumImpl(candidates, 0, target);
	if(result == null){
	    return new ArrayList<ArrayList<Integer>>();
	}else{
	    return result;
	}
    }

    public ArrayList<ArrayList<Integer>> sumImpl(int[] candidates, int idx, int target){
	if(idx == candidates.length && target != 0){
	    return null;
	}else if(idx == candidates.length && target == 0){
	    return new ArrayList<ArrayList<Integer>>();
	}else{
	    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	    int val = candidates[idx];
	    int count = 0;
	    while(count*val <= target){
		ArrayList<ArrayList<Integer>> res = 
		    sumImpl(candidates, idx+1, target - count*val);
		if(res == null){
		    count++;
		    continue;
		}
		if(res.size() == 0){
		    ArrayList<Integer> temp = new ArrayList<Integer>();
		    for(int i = 0; i < count; i++){
			temp.add(val);
		    }
		    res.add(temp);
		}else{
		    for(ArrayList<Integer> ai : res){
			for(int i = 0; i < count; i++){
			    ai.add(val);
			}
		    }
		}
		result.addAll(res);
		count++;
	    }
	    if(result.size() == 0)
		return null;
	    else
		return result;
	}
    }
}
