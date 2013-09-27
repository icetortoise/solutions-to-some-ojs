package Grep;

import java.util.*;

public class Grep{
    private char[] regex = null;
    private ArrayList<ArrayList<Integer>> epsilonTrans = null;
    
    public Grep(String reg){
	assert(reg!=null);
	reg = preCompile(reg);
	regex = reg.toCharArray();
	epsilonTrans = buildEpsilonTrans(regex);
    }

    public String preCompile(String reg){
	StringBuffer sb = new StringBuffer();
	char[] regA = reg.toCharArray();
	Stack<Integer> s = new Stack<Integer>();
	Stack<Integer> setStack = new Stack<Integer>();
	for(int i = 0; i < regA.length; i++){
	    int lp = i;
	    if(regA[i] == '('){
		s.push(i);
	    }else if(regA[i] == ')'){
		lp = s.pop();
	    }
	    if(regA[i] == '['){
		setStack.push(i);
	    }else if(regA[i] == ']'){
		lp = setStack.pop();
	    }
	    sb.append(regA[i]);
	    if(i < regA.length-1 && regA[i+1] == '+'){
		for(int j = lp; j <= i; j++){
		    sb.append(regA[j]);
		}
		sb.append('*');
		i++;
	    }
	}
	return sb.toString();
    }

    public ArrayList<ArrayList<Integer>> buildEpsilonTrans(char[] cs){
	ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	for(int i = 0; i <= cs.length; i++){
	    adjList.add(new ArrayList<Integer>());
	}
	Stack<Integer> s = new Stack<Integer>();
	for(int i = 0; i < cs.length; i++){
	    int lp = i;
	    if(cs[i] == '(' || cs[i] == '|'){
		s.push(i);
	    }else if(cs[i] == ')'){
		ArrayList<Integer> ors = new ArrayList<Integer>();
		int pop = s.pop();
		while(cs[pop] == '|'){
		    adjList.get(pop).add(i);
		    ors.add(pop);
		    pop = s.pop();
		}
		lp = pop;
		for(int o : ors){
		    adjList.get(lp).add(o+1);
		}
	    }
	    if(i < cs.length-1 && cs[i+1] == '*'){//looking ahead
		adjList.get(lp).add(i+1);
		adjList.get(i+1).add(lp);
	    }
	    if(cs[i] == '(' || cs[i] == ')' || cs[i] =='*'){
		adjList.get(i).add(i+1);
	    }
	}
	return adjList;
    }

    public boolean recognizes(String text){
	HashSet<Integer> sts = new HashSet<Integer>();
	sts = dfs(0, sts);
	for(int i = 0; i<text.length(); i++){
	    HashSet<Integer> matches = new HashSet<Integer>();
	    for(int s : sts){
		if(s < regex.length && (regex[s] == '.' || regex[s] == text.charAt(i))){
		    matches.add(s+1);
		}
	    }
	    if(matches.size() == 0) return false;
	    HashSet<Integer> newSts = new HashSet<Integer>();
	    for(int m : matches){
		newSts.addAll(dfs(m, new HashSet<Integer>()));
	    }
	    sts = newSts;
	}
	return sts.contains(regex.length);
    }
    
    private HashSet<Integer> dfs(int v, HashSet<Integer> visited){ 
	visited.add(v);
	for(int adj : epsilonTrans.get(v)){
	    if(!visited.contains(adj)){
		dfs(adj, visited);
	    }
	}
	return visited;
    }
}
