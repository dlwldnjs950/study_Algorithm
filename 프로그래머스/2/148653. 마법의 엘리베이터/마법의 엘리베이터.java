import java.util.*;
/*
민수가 있는 층이 주어졌을 때, 
마법 돌 최소로 써서 0층으로 가는 방법

각 자리 수가 5보다 크면 
    작은 숫자로 가는 것보다 다음 숫자로 키워서 감소시키는게 마법돌 적게 듦

*/
class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey > 0){
            int number = storey %10;
            storey /=10;
            
            if(number == 5){
                // 늘리는 것도 같고 줄이는 것도 같으니까
                // 더큰자리수가 늘어나는게 나은지 줄어드는게 나은지 확인
                if(storey %10 >=5){
                    answer += (10 - number);
                    storey++;
                }else{
                    answer += number;
                }
            // 5보다 크면 커지는게 나으니까
            }else if(number > 5){
                answer += (10 - number);
                storey++;
            // 5보다 작으면 작아지는게 나으니까
            }else{
                answer += number;
            }
        }
        
        return answer;
    }
}