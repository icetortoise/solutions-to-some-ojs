package RemoveElement;

public class Solution {
    public int removeElement(int[] A, int elem) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int offset = 0;
	for(int i = 0; i < A.length; i++){
	    if(elem == A[i]){
		offset++;
	    }else{
		A[i - offset] = A[i];
	    }
	}
	return A.length - offset;
    }
}
