package MergeSortedArray;

public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(B.length == 0){
	    return;
	}
	for(int i = m; i < A.length; i++){
	    A[i] = Integer.MAX_VALUE;
	}
	int ai = 0, bi = 0;
	int tail = A.length - 1;
	boolean tailed = false;
	for(int i = 0; i < m + n; i++){
	    if(!tailed){
		if(A[ai] <= B[bi]){
		    ai++;
		}else{
		    tailed = true;
		    A[tail] = A[ai];
		    A[ai] = B[bi];
		    bi++;
		    ai++;
		}
	    }else{
	    if(A[ai] <= B[bi] && A[ai] <= A[tail]){
		ai++;
	    }else if(B[bi] <= A[ai] && B[bi] <= A[tail]){
		int temp = A[ai];
		A[ai] = B[bi];
		B[bi] = temp;
		if(temp == Integer.MAX_VALUE){
		    bi++;
		}
		ai++;
	    }else{
		int temp = A[ai];
		A[ai] = A[tail];
		A[tail] = temp;
		ai++;
	    }
	    }
	}
    }
}
