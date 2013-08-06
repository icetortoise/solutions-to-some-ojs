package LongestConsecutiveSequence;

import java.util.*;
public class Solution {
    // solution copied online
    // the key is to realize that for any new a[i], it's either part of an existing consecutive range, or forms/extends
    // a new range. If we make sure when it forms/extends a new range, it is recorded as used, then when it is in an 
    // existing range, we can skip it, because it musted have been visited before!
    public int longestConsecutive(int[] a) {
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	int max = 1;
	for (int i : a) {
	    if (map.containsKey(i)) continue;
	    map.put(i, 1);
	    if (map.containsKey(i - 1)) {
		max = Math.max(max, merge(map, i-1, i));
	    }
	    if (map.containsKey(i + 1)) {
		max = Math.max(max, merge(map, i, i+1));
	    }
	}
	return max;
    }

    private int merge(HashMap<Integer, Integer> map, int left, int right) {
	int upper = right + map.get(right) - 1;
	int lower = left - map.get(left) + 1;
	int len = upper - lower + 1;
	map.put(upper, len);
	map.put(lower, len);
	return len;
    }

    // O(n) based on hashmap...which is no big diff to O(nlogn)...
    public HashMap<Integer, Integer> longestConsecutiveSlow(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
	HashMap<Integer, Integer> lowToHigh = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> highToLow = new HashMap<Integer, Integer>();
	for(int i = 0; i<num.length; i++){
	    int lower = num[i] - 1, higher = num[i] + 1;
	    if(highToLow.containsKey(lower) && lowToHigh.containsKey(higher)){
		int newL = highToLow.get(lower);
		int newH = lowToHigh.get(higher);
		highToLow.remove(lower); lowToHigh.remove(higher);
		highToLow.put(newH, newL); lowToHigh.put(newL, newH);
	    }
	    else if(highToLow.containsKey(lower)){
		int r1 = lower, r2 = highToLow.get(lower);
		highToLow.remove(r1);
		lowToHigh.remove(r2);
		lowToHigh.put(r2, num[i]);
		highToLow.put(num[i], r2);
	    }else if(lowToHigh.containsKey(higher)){
		int r1 = lowToHigh.get(higher), r2 = higher;
		lowToHigh.remove(r2);
		highToLow.remove(r1);
		lowToHigh.put(num[i], r1);
		highToLow.put(r1, num[i]);
	    }else{
		if(lowToHigh.containsKey(lower) || highToLow.containsKey(higher) || 
		   lowToHigh.containsKey(num[i]) || highToLow.containsKey(num[i])){
		    continue;
		}else{
		    highToLow.put(num[i], num[i]);
		    lowToHigh.put(num[i], num[i]);
		}
	    }
	}
	return lowToHigh;
	// int result = 0;
	// for (Map.Entry<Integer, Integer> entry : lowToHigh.entrySet()) {
	//     if(result < entry.getValue() - entry.getKey() + 1){
	// 	result = entry.getValue() - entry.getKey() + 1;
	//     }
	// }
	// return result;
    }
}
