import java.util.*;
/*3진법!*/
class Solution {
    public String solution(int n) {
        
        StringBuilder answer = new StringBuilder();
        
        
        while(n != 0){
            n-=1;
            int extra = n % 3;
            if(extra == 0)
                extra = 1;
            else if(extra == 1)
                extra = 2;
            else if(extra == 2)
                extra = 4;
            
            answer.append(extra);
            
            n /= 3;
            
        }
        
        answer.reverse();
        
        return answer.toString();
    }
}