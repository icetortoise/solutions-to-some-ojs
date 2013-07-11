package TrappingRainWater;

public class Solution {
    public int trap(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
	assert A!= null;
	if(A.length == 0) return 0;
	int leftIdx = 0, rightIdx = A.length-1;
	int left = A[leftIdx], right = A[rightIdx];
	int min = Math.min(left, right);
	int water = 0;
	while(leftIdx < rightIdx){
	    if(left <= right){
		water += min - A[leftIdx]>0?min - A[leftIdx]:0;
		if(A[leftIdx] > min){
		    left = A[leftIdx];
		    min = Math.min(left, right);
		}else{
		    leftIdx++;
		}
	    }else{
		water += min - A[rightIdx]>0?min - A[rightIdx]:0;
		if(A[rightIdx] > min){
		    right = A[rightIdx];
		    min = Math.min(left, right);
		}else{
		    rightIdx--;
		}
	    }
	}
	return water;
    }
}
