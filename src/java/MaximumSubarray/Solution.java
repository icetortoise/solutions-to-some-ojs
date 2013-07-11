package MaximumSubarray;

public class Solution {
    public int maxSubArray(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(A.length == 0) return 0;
	int maxEnd = 0;
	int max = A[0];
	int runningSum = 0;
	for(int i = 0; i < A.length; i++){
	    if(A[i] <= 0){
		runningSum += A[i];
		if(max < runningSum)
		    max = runningSum;
		if(runningSum < 0){
		    runningSum = 0;
		}
	    }else{
		runningSum += A[i];
		if(runningSum <= A[i]){
		    runningSum = A[i];
		}
		if(max < runningSum)
		    max = runningSum;
	    }
	}
	return max;
    }
}
