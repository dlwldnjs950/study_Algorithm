import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
		Stack<Character> stck = new Stack<>();
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if(c=='(') {
				stck.push(c);
			}else {
				if(stck.empty()) answer=false;
				else stck.pop();
			}
		}
		if(!stck.empty()) answer=false;

        return answer;
    }
}