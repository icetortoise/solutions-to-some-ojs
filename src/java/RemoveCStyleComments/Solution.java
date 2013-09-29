package RemoveCStyleComments;

public class Solution{
    public enum Status{
	Normal, LineComment, BlockComment, DoubleQuoted
    }
    public String removeComments(String cSource){
	Status s = Status.Normal;
	StringBuffer sb = new StringBuffer();
	char[] ca = cSource.toCharArray();
	for(int i = 0; i < ca.length; i++){
	    switch(s){
	    case Normal:
		if(ca[i] == '\\'){
		    sb.append(ca[i]);
		    if(i < ca.length-1) i++;
		}else if(ca[i] == '"'){
		    s = Status.DoubleQuoted;
		}else if(ca[i] == '/'){
		    if(i < ca.length-1 && ca[i+1] == '/'){//line comment
			s = Status.LineComment;
		    }else if (i < ca.length-1 && ca[i+1] == '*'){//block comment
			s = Status.BlockComment;
		    }
		}
		if(s != Status.LineComment && s != Status.BlockComment){
		    sb.append(ca[i]);
		}
		break;
		
	    case DoubleQuoted:
		if(ca[i] == '\\'){
		    sb.append(ca[i]);
		    if(i < ca.length-1) i++;
		}else if(ca[i] == '"'){
		    s = Status.Normal;
		}
		sb.append(ca[i]);
		break;

	    case LineComment:
		if(ca[i] == '\n'){
		    s = Status.Normal;
		}
		break;
		
	    case BlockComment:
		if(ca[i] == '*' && i < ca.length-1 && ca[i+1] == '/'){
		    s = Status.Normal;
		    i++;
		}
	    }
	}
	return sb.toString();
    }
}
