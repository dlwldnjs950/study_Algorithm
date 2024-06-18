import java.util.*;
/*
바로 뒷 숫자라...
이거 그건데 탑에서 총쏘던 그거
뒤에서부터 확인
*/
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        Stack<Integer> stack = new Stack<>();
        
        for(int idx = numbers.length -1 ; idx >= 0; idx--){
            int nowNum = numbers[idx];
            
            while(!stack.isEmpty()){
                if(stack.peek() > nowNum){
                    answer[idx] = stack.peek();
                    break;
                }else{
                    stack.pop();
                }
            }
            
            if(stack.isEmpty()){
                answer[idx] = -1;
            }
            stack.add(nowNum);
        }
        
        return answer;
    }
}