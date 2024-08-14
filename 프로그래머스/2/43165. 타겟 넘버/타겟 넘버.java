import java.util.*;

class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        makeTarget(0, 0, numbers, target);
        return answer;
    }
    
    void makeTarget(int cnt, int midResult, int[] numbers, int target){
        
        // 마지막 숫자까지 고려했을 때, target과 비교
        if(cnt == numbers.length){
            if(midResult == target){
                answer++;
            }
            return;
        }
        
        // 해당 숫자 더하는 경우
        makeTarget(cnt +1, midResult + numbers[cnt], numbers, target);
        // 해당 숫자 빼는 경우
        makeTarget(cnt +1, midResult - numbers[cnt], numbers, target);
    }
}