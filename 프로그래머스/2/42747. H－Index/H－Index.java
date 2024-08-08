import java.util.*;
/*
n편 중 h번 이상 인용된 논문이 h편 이상이고
나머지 논문이 h번 이하 인용되었을 때,
h의 최댓값이 H-Index

과학자의 논문 인용 횟수가 주어질 때, H-Index를 구하시오

정렬하고

*/
class Solution {
    public int solution(int[] citations) {
        
        int length = citations.length;
        
        Arrays.sort(citations);
        int answer = 0;
        
        for(int idx=0; idx< length; idx++){            
            // 인용된 값이 h 지수보다 커야함
            if(length - idx <= citations[idx])
                return length - idx;
        }
        
        return answer;
    }
}