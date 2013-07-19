package InsertInterval;

import java.util.*;
import Interval.Interval;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Interval> nis = new ArrayList<Interval>();
	boolean inserted = false;
	for(Interval i : intervals){
	    if(i.start >= newInterval.start){
		nis.add(newInterval);
		inserted = true;
	    }
	    nis.add(i);
	}
	if(!inserted)
	    nis.add(newInterval);
	return merge(nis);
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Interval> result = new ArrayList<Interval>();
	for(Interval i : intervals){
	    Interval last = null;
	    if(result.size() != 0){
		last = result.get(result.size() - 1);
	    }
	    if(last == null){
		result.add(i);
	    }else{
		if(i.start > last.end){
		    result.add(i);
		}else{
		    result.set(result.size() - 1, 
			       new Interval(last.start, last.end<i.end?i.end:last.end));
		}
	    }
	}
	return result;
    }

}
