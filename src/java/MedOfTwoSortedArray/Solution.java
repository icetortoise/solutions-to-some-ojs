package MedOfTwoSortedArray;

public class Solution {
    public double findMedianSortedArraysLinear(int A[], int B[]) {
        // Start typing your Java solution below
        // DO NOT write main() function
        assert A != null && B != null;
	int m = (A.length + B.length - 1)/2;
	if(A.length == 0 && B.length != 0){
	    return B[m];
	}else if(B.length == 0 && A.length != 0){
	    return A[m];
	}
	assert m >= 0;
	int a=0,b = 0;
	int result = 0;
	for(int i = 0; i <= m; i++){
	    if(A[a] >= B[b]){
		if(b == B.length - 1) {result = A[a]; a++; }
		else {result = B[b]; b++;}
	    }else{
		if(a == A.length - 1) {result = B[b]; b++;}
		else {result = A[a]; a++;}
	    }
	}
	if((A.length + B.length)%2 == 0){
	    return A[a]<B[b]?(result + A[a])/2:(result + B[b])/2;
	}else {
	    return result;
	}
    }
}
