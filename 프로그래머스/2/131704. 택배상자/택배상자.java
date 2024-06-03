import java.util.*;
/*
메인 컨베이어벨트는 큐
보조 컨베이어벨트는 스택

order : 택배 기사님이 원하는 상자 순서
메인 컨베이어 벨트는 1번부터 차례대로 상자가 나옴
*/
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Queue<Integer> mainBelt = new ArrayDeque<>();
        Stack<Integer> subBelt = new Stack<>();
        
        int nowBoxIdx = 0;
        for(int boxNum = 1; boxNum<=order.length;){
            // 지금 차에 싣을 번호면 카운트
            if(boxNum == order[nowBoxIdx]){
                answer++;
                nowBoxIdx++;
                boxNum++;
            }else{
                // 메인 벨트랑 넣어야할 상자랑 달라서 보조 벨트 보러 왔어
                
                if(subBelt.empty()){
                    subBelt.push(boxNum++);
                }else{
                    if(subBelt.peek() == order[nowBoxIdx]){
                        answer++;
                        nowBoxIdx++;
                        subBelt.pop();
                    }else{
                        subBelt.push(boxNum++);
                    }
                }
            }
        }
        // 메인 벨트 확인은 끝났고 보조 벨트
        while(!subBelt.empty() && subBelt.peek() == order[nowBoxIdx]){
            answer++;
            nowBoxIdx++;
            subBelt.pop();
        }
        
        return answer;
    }
}