package SearchInRotatedSortedArray2;

public class Solution {
    public boolean search(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
	return searchImpl(A, 0, A.length-1, target);
    }
    public boolean searchImpl(int[] A, int start, int end, int target){
	if(start > end) return false;
	if(start == end){
	    return target == A[start];
	}else{
	    int mid = (start+end)/2;
	    if(A[mid] == target){
		return true;
	    }else if(A[mid] < target){
		if(A[mid] > A[end]){
		    return searchImpl(A, mid+1, end, target);
		}else{
		    if(searchImpl(A, mid+1, end, target)){
			return true;
		    }else{
			return searchImpl(A, start, mid-1, target);
		    }
		}
	    }else{
		if(A[mid] < A[start]){
		    return searchImpl(A, start, mid-1, target);
		}else{
		    if(searchImpl(A, start, mid-1, target)){
			return true;
		    }else{
			return searchImpl(A, mid+1, end, target);
		    }
		}
	    }
	}
    }
}
