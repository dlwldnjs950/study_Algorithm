import java.util.*;
/*

*/
class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0){
            int number = storey %10;
            storey = storey /10;
            
            if(number == 5){
                if(storey %10 >=5){
                    answer += (10 - number);
                    storey++;
                }else{
                    answer += number;
                }
            }else if(number > 5){
                answer += (10 - number);
                storey++;
            }else{
                answer += number;
            }
        }
        
        return answer;
    }
}