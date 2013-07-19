package SortColor;

public class Solution {
    public void sortColors(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int zIdx = -1, oIdx=-1, tIdx=-1;
	for(int i = 0; i<A.length; i++){
	    if(A[i] == 0){
		if(zIdx == -1){
		    zIdx = oIdx == -1? (tIdx == -1? i : tIdx) : oIdx;
		}
		if(oIdx != -1){
		    A[i] = 1;
		    A[oIdx] = 0;
		    oIdx++;
		}
		if(tIdx != -1){
		    int temp = A[i];
		    A[i] = A[tIdx];
		    A[tIdx] = temp;
		    tIdx++;
		}
	    }else if(A[i] == 1){
		if(oIdx == -1){
		    oIdx = tIdx == -1? i : tIdx;
		}
		if(tIdx != -1){
		    A[i] = 2;
		    A[tIdx] = 1;
		    tIdx++;
		}
	    }else{
		if(tIdx == -1){
		    tIdx = i;
		}
	    }
	}
    }
}
