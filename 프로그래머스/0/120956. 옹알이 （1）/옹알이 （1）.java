import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        String[] tongues = { "aya", "ye", "woo", "ma"};
        
        for(String bab : babbling){
            String tmp = bab;
            for(String tongue : tongues){
                if(bab.contains(tongue))
                    tmp = tmp.replaceFirst(tongue, "");
            }
            if(tmp.length() == 0)
                answer++;
        }
        
        return answer;
    }
}