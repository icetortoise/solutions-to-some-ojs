package JumpGame2;

public class Solution {
    public int jump(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
	assert A != null;
	if(A.length == 0) return -1;
	int[] jumps = new int[A.length];
	for(int i = A.length - 1; i >= 0;  i--){
	    if(i ==  A.length - 1){
		jumps[i] = 0;
	    }
	    else{
		int min = A.length;
		for(int j = Math.min(i + A[i], A.length - 1); j > i; j--){
		    if(jumps[j] + 1 < min)
			min = jumps[j] + 1;
		}
		jumps[i] = min;
	    }
	}
	return jumps[0];
    }
}
