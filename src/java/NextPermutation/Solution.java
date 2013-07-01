package NextPermutation;

public class Solution {
    public void nextPermutation(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(num.length == 0) return;
	int lastSeen = num[num.length-1];
	int toSwap = -1;
	for(int i = num.length-1; i>=0; i--){
	    if(num[i] >= lastSeen){
		lastSeen = num[i];
	    }else{
		toSwap = i;
		break;
	    }
	}
	if(toSwap == -1){
	    reverse(num, 0);
	}else{
	    int target = search(num, toSwap + 1, num.length-1, num[toSwap]);
	    int temp = num[target];
	    num[target] = num[toSwap];
	    num[toSwap] = temp;
	    reverse(num, toSwap+1);
	}
    }
    public int search(int[] num, int bIdx, int eIdx, int candidate){
	// return the idx of the element in num[bIdx..] that is just above the candidate
	int m = (bIdx + eIdx)/2;
	if(bIdx == eIdx){
	    if(num[bIdx] == candidate){return bIdx - 1;}
	    else{return bIdx;}
	}else if(eIdx - bIdx == 1){
	    if(num[eIdx] > candidate){
		return eIdx;
	    }else if (num[bIdx] > candidate){
		return bIdx;
	    }else{
		return bIdx - 1;
	    }
	}
	if(num[m] > candidate){
	    return search(num, m, eIdx, candidate);
	}else if(num[m] < candidate){
	    return search(num, bIdx, m - 1, candidate);
	}else{
	    return m-1;
	}
    }
    public void reverse(int[] num, int bIdx){
	int i = bIdx, j = num.length - 1;
	while(i < j){
	    int temp = num[i];
	    num[i]=num[j];
	    num[j]=temp;
	    i++;
	    j--;
	}
    }
}
