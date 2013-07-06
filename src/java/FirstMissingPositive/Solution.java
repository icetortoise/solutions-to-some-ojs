package FirstMissingPositive;

public class Solution {
    public int firstMissingPositive(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function

	// the key is to realized the first missing positive p must have 1 <= p <= A.length+1
	// we can then use A[0] to tell if 1 is present, A[1] for 2...A(A.lengh-1) for A.length

	for(int i = 0; i < A.length;){
	    if(A[i]>0 && A[i] <= A.length && A[i] != i+1){
		if(A[A[i]-1] != A[i]){
		    int temp = A[A[i]-1];
		    A[A[i]-1] = A[i];
		    A[i] = temp;
		    continue;
		}
	    }
	    i++;
	}
	
	int i = 0;
	for(; i < A.length; i++){
	    if(A[i] != i+1)
		return i+1;
	}
	return i + 1;
    }
}
