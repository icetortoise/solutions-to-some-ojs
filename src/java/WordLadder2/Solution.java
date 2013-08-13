package WordLadder2;

import java.util.*;
public class Solution {
    
    public HashMap<String, ArrayList<String>> genParents(String start, String end, HashSet<String> dict){
	HashMap<String, ArrayList<String>> parents = new HashMap<String, ArrayList<String>>();
	HashSet<String> visited = new HashSet<String>();
	ArrayList<String> q = new ArrayList<String>();
	q.add(start);
	visited.add(start);
	while(q.size() > 0){
	    HashSet<String> inThisLevel = new HashSet<String>();
	    ArrayList<String> newq = new ArrayList<String>();
	    boolean found = false;
	    for(String s : q){
		ArrayList<String> cans = getCandidates(s, dict, end);
		for(String c : cans){
		    if(visited.contains(c) && inThisLevel.contains(c)){
			parents.get(c).add(s);
		    }else if (!visited.contains(c)){
			ArrayList<String> t = new ArrayList<String>();
			t.add(s);
			parents.put(c, t);
			inThisLevel.add(c);
			visited.add(c);
			newq.add(c);
			if(c.equals(end)){
			    found = true;
			}
		    }
		}
	    }
	    if(found){
		break;
	    }else{
		q = newq;
	    }
	}
	return parents;

    }

    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
	return buildResult(genParents(start, end, dict), end, start);
    }
    

    public ArrayList<ArrayList<String>> buildResult(HashMap<String, ArrayList<String>> parents, String end, String start){
	ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
	if(end.equals(start)){
	    ArrayList<String> sl = new ArrayList<String>();
	    sl.add(start);
	    result.add(sl);
	    return result;
	}
	if(parents.containsKey(end)){
	    for(String s : parents.get(end)){
		ArrayList<ArrayList<String>> x = buildResult(parents, s, start);
		for (ArrayList<String> path : x){
		    path.add(end);
		}
		result.addAll(x);
	    }
	}
	return result;
    }

// this didnt pass the large tests. From online discuss, this is the 'right' algorithm to use though, 
// the reason this is not passing is probably because the use of hashmap(as graph), an 
// array-based algorithm that work on indexes only would run faster.
    public ArrayList<ArrayList<String>> findLaddersSndTry(String start, String end, HashSet<String> dict) {
	HashMap<String, ArrayList<String>> graph = buildGraph(start, end, dict);
	ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
	ArrayList<String> path = new ArrayList<String>();
	path.add(start);
	paths.add(path);
	while(true){
	    ArrayList<ArrayList<String>> newPaths = new ArrayList<ArrayList<String>>();
	    ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
	    boolean found = false;
	    for(ArrayList<String> p : paths){
		if(p.get(p.size()-1).equals(end)){
		    found = true;
		    result.add(p);
		}else{
		    for(String n : graph.get(p.get(p.size()-1))){
			ArrayList<String> newP = new ArrayList<String>(p);
			newP.add(n);
			newPaths.add(newP);
		    }
		}
	    }
	    if(found){
		return result;
	    }else if(newPaths.size() == 0){
		return newPaths;
	    }else{
		paths = newPaths;
	    }
	}
    }

    public HashMap<String, ArrayList<String>> buildGraph(String start, String end, 
							HashSet<String> dict){
	HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
	for(String d : dict){
	    ArrayList<String> candidates = getCandidates(d, dict, end);
	    graph.put(d, candidates);
	}
	graph.put(start, getCandidates(start, dict, end));
	return graph;
    }

    public ArrayList<String> getCandidates(String from, HashSet<String> dict, String end){
	ArrayList<String> candidates = new ArrayList<String>();
	char[] chars = from.toCharArray();
	for(int i = 0; i < chars.length; i++){
	    for(char c = 'a' ; c <= 'z'; c++){
		if(c != chars[i]){
		    char[] newChars = (char[])chars.clone();
		    newChars[i] = c;
		    String newStr = new String(newChars);
		    if(dict.contains(newStr) || newStr.equals(end)){
			candidates.add(newStr);
		    }
		}
	    }
	}
	return candidates;
    }


    public ArrayList<ArrayList<String>> findLaddersSlow(String start, String end, HashSet<String> dict) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Queue<ArrayList<String>> q = new ArrayDeque<ArrayList<String>>();
	ArrayList<String> p = new ArrayList<String>();
	p.add(start);
	q.offer(p);
	int shortestSoFar = Integer.MAX_VALUE;
	while(q.peek() != null){
	    ArrayList<String> e = q.poll();
	    char[] chars = e.get(e.size()-1).toCharArray();
	    for(int i = 0; i<chars.length; i++){
		for(char c = 'a' ; c <= 'z'; c++){
		    if(c != chars[i]){
			char[] newChars = (char[])chars.clone();
			newChars[i] = c;
			String newStr = new String(newChars);
			if(end.equals(newStr)){
			    ArrayList<String> path = new ArrayList<String>(e);
			    path.add(newStr);
			    if(path.size() < shortestSoFar){
				result.clear();
				shortestSoFar = path.size();
				result.add(path);
			    }else if(path.size() == shortestSoFar){
				result.add(path);
			    }
			}else if(dict.contains(newStr)){
			    ArrayList<String> path = new ArrayList<String>(e);
			    if(path.size() < shortestSoFar){
				path.add(newStr);
				q.offer(path);
			    }
			}
		    }
		}
	    }

	}
	return result;
    }

}
