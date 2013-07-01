package SearchInRotatedSortedArray;

public class Solution {
    public int search(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if (A.length == 0) return -1;
	int ele = A[0];
	int i = 0;
	while(i < A.length){
	    if(A[i] < ele){
		break;
	    }else{
		ele = A[i];
	    }
	    i++;
	}
	return bSearch(A, i, i + A.length - 1, target);
    }
    
    public int bSearch(int[] A, int s, int e, int target){
	int n = A.length;
	if(s == e){
	    if(A[idx(s, n)] == target){
		return idx(s, n);
	    }else {return -1;}
	}else if(s > e){
	    return -1;
	}else{
	    int m = (s + e)/2;
	    if(A[idx(m, n)] > target){
		return bSearch(A, s, m - 1, target);
	    }else if(A[idx(m,n)] < target){
		return bSearch(A, m+1,e, target);
	    }else{
		return idx(m,n);
	    }
	}
    }
    
    public int idx(int i, int n){
	if(i >= n){return i - n;}
	return i;
    }

}
