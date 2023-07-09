class Solution {
    public String solution(String my_string) {
        String answer = "";
        for(int i=0; i<my_string.length();i++) {
			char tmp = my_string.charAt(i);
			if(!answer.contains(tmp+""))
				answer+=tmp;
		}
        return answer;
    }
}