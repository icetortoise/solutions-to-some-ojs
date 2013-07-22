package LargestRectangleInHistogram;

public class Solution {
    public int largestRectangleArea(int[] height) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(height.length == 0) return 0;
	int[] left = new int[height.length], right = new int[height.length];
	fillLeft(height, left);
	fillRight(height, right);
	int area = 0;
	for(int i = 0; i<height.length;i++){
	    int newArea = height[i] * (right[i] - left[i] + 1);
	    if(newArea > area)
		area = newArea;
	}
	return area;
    }

    public void fillLeft(int[] height, int[] left){
	for(int i = 0; i < height.length; i++){
	    int j = i -1;
	    while(true){
		if(j<0){
		    left[i] = 0;
		    break;
		}else{
		    if(height[i] == height[j]){
			left[i] = left[j];
			break;
		    }else if(height[i] > height[j]){
			left[i] = j+1;
			break;
		    }else{
			j = left[j] - 1;
			continue;
		    }
		}
	    }
	}
    }

    public void fillRight(int[] height, int[] right){
	for(int i = height.length - 1; i >= 0; i--){
	    int j = i + 1;
	    while(true){
		if(j>= height.length){
		    right[i] = height.length - 1;
		    break;
		}else{
		    if(height[i] == height[j]){
			right[i] = right[j];
			break;
		    }else if(height[i] > height[j]){
			right[i] = j - 1;
			break;
		    }else{
			j = right[j] + 1;
			continue;
		    }
		}
	    }
	}
    }


    // A O(nlogn) solution, with worst case of O(n^2), therefore fails the OJ.
    // public int largestRectangleArea(int[] height) {
    //     // Start typing your Java solution below
    //     // DO NOT write main() function
    //     if(height.length == 0) return 0;
    // 	return helper(height, 0, height.length-1);
    // }
    // public int helper(int[] height, int start, int end){
    // 	if(start == end) return height[start];
    // 	int minIdx = min(height, start, end);
    // 	int area = (end - start + 1) * height[minIdx];
    // 	int al = 0, ar = 0;
    // 	if(minIdx > start)
    // 	    al = helper(height, start, minIdx-1);
    // 	if(minIdx < end)
    // 	    ar = helper(height, minIdx+1, end);
    // 	if(area > al){
    // 	    return area > ar? area : ar;
    // 	}else{
    // 	    return al > ar ? al : ar; 
    // 	}
    // }
    // public int min(int[] height, int start, int end){
    // 	int val = Integer.MAX_VALUE;
    // 	int idx = -1;
    // 	for(int i = start ; i <= end; i++){
    // 	    if(height[i] < val){
    // 		val = height[i];
    // 		idx = i;
    // 	    }
    // 	}
    // 	return idx;
    // }
}
