import java.util.*;
/*
우선순위 큐를 활용한다
현재 적을 일단 처리해보고
처리 할 수 없었던 상태라면
다시 롤백하고 무적권을 사용해보는거지
*/
class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        int skillCnt = 0;
        
        for(int idx=0; idx < enemy.length; idx++){
            n -= enemy[idx];
            pq.add(enemy[idx]);
            
            // 처리할 수 없는 적이었다면
            if(n < 0){
                // 무적권을 쓸건지
                if(skillCnt < k && !pq.isEmpty()){
                    n += pq.poll();
                    skillCnt++;
                }else{
                    return idx;
                }
            }
        }
        
        return enemy.length;
    }
}