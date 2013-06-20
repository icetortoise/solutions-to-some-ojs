package ContainerWithMostWater;

public class Solution {
    public int maxArea(int[] height) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int start = 0, end = height.length -1;
	int area = 0;
	while(start < end){
	    int current = calcArea(start, end, height);
	    if(current > area) {area = current;}
	    if(height[start] > height[end]){
		end--;
	    }else{
		start++;
	    }
	}
	return area;
    }
    
    private int calcArea(int s, int e, int[] height){
	if(height[s] > height[e]){
	    return height[e] * (e - s);
	}else{
	    return height[s] * (e - s);
	}
    }
}
