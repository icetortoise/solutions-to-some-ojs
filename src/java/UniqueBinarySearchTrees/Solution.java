package UniqueBinarySearchTrees;


public class Solution {
    public int numTrees(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(n == 0 ) return 1;
        int[] nums = new int[n+1];
	nums[0] = 1;
	for(int i = 1; i <=n ; i++){
	    int num = 0;
	    for(int j = 0; j < i; j++){
		num += nums[j]*nums[i-j-1];
	    }
	    nums[i] = num;
	}
	return nums[n];
    }
}
