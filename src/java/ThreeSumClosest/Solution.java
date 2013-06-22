package ThreeSumClosest;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
	Arrays.sort(num);
	return nSumClosest(3, num, 0, target);
    }

    public int nSumClosest(int n, int[] num, int idx, int target){
	int closest = Integer.MAX_VALUE;
	int distance = Integer.MAX_VALUE;
	if(n>=2){
	    for(int i = idx; i < num.length - n + 1; i++){
		int c = nSumClosest(n-1, num, i+1, target - num[i]);
		if(Math.abs(c + num[i] - target) < distance){
		    closest = c + num[i];
		    distance = Math.abs(c + num[i] - target);
		}
	    }
	}else{
	    closest = closest(num, idx, target);
	}
	return closest;
    }

    public int closest(int[] num, int idx, int target){
	int i = idx, j = num.length - 1;
	while(j - i > 1){
	    int mid = (i + j) / 2;
	    if(target > num[mid]){
		i = mid;
	    }else if (target < num[mid]){
		j = mid;
	    }else{
		return target;
	    }
	}
	if(Math.abs(num[i] - target) < Math.abs(num[j] - target)){
	    return num[i];
	}else{
	    return num[j];
	}
    }
}
