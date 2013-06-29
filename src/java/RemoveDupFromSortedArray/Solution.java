package RemoveDupFromSortedArray;

public class Solution {
    public int removeDuplicates(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int offset = 0;
	Integer previous = null;
	for(int i = 0; i < A.length; i++){
	    if(previous == null){
		previous = A[i];
	    }else{
		if(A[i] == previous){
		    offset++;
		}else{
		    previous = A[i];
		    A[i - offset] = A[i];
		}
	    }
	}
	return A.length - offset;
    }
}
