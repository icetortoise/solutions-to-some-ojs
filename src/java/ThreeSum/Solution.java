package ThreeSum;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
	Arrays.sort(num);
	return nSum(num, 0, 0, 3);
    }
    
    private ArrayList<ArrayList<Integer>> nSum(int[] num, int idx, int target, int n){
	assert n >= 2;
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	if(n > 2){
	    Integer processed = Integer.MIN_VALUE;
	    for(int i = idx; i < num.length; i++){
		if(processed >= num[i]){
		    continue;
		}else{
		    processed = num[i];
		    ArrayList<ArrayList<Integer>> tempResult = nSum(num, i+1, target - num[i], n - 1);
		    for(ArrayList<Integer> l : tempResult){
			l.add(0, num[i]);
			result.add(l);
		    }
		}
	    }
	}else{
	    HashMap<Integer, Boolean> m = new HashMap<Integer, Boolean>();
	    for(int i = idx; i < num.length; i++){
		int key = target - num[i];
		if(m.containsKey(key) && m.get(key)){
		    ArrayList x = new ArrayList<Integer>();
		    x.add(key);
		    x.add(num[i]);
		    result.add(0, x);
		    m.put(key, false);
		}else if(!m.containsKey(key)){
		    m.put(num[i], true);
		}
	    }
	}
	return result;
    }
}
