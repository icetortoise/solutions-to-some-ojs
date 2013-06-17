package TwoSum;

import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if (numbers == null){
	    return null;
	}
	HashMap<Integer, Integer> t = new HashMap<Integer,Integer>();
	for(int i = 0; i < numbers.length; i++){
	    int key = target - numbers[i];
	    if(t.containsKey(key)){
		return new int[] {t.get(key)+1, i+1};
	    }else{
		t.put(numbers[i], i);
	    }
	}
	return null;
    }
    
    public static void main(){
	Solution s = new Solution();
	int[] result = s.twoSum(new int[] {5,75,25}, 100);
	System.out.println(result[0]);
	System.out.println(result[1]);
    }
}
