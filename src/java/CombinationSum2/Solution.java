package CombinationSum2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;

public class Solution{
public ArrayList<ArrayList<Integer>> combinationSum2(int[] candidates, int target) {
    if(candidates == null || target <= 0) return null;
    Arrays.sort(candidates);
    return combine(candidates, 0, target);
}
public ArrayList<ArrayList<Integer>> combine(int[] set, int start, int target){
    ArrayList<ArrayList<Integer>> rl = new ArrayList<ArrayList<Integer>>();
    HashSet<ArrayList<Integer>> rll = new HashSet<ArrayList<Integer>>();
    if(target <= 0){
        if(target == 0) rl.add(new ArrayList<Integer>());
        return rl;
    }
    for(int i = start; i < set.length; i++)
        for(ArrayList<Integer> x : combine(set, i + 1, target - set[i])){
            x.add(0, set[i]);
            rll.add(x);
        }
    rl.addAll(rll);
    return rl;
}
}
