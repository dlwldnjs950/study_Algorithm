import java.util.*;
/*
모든 음식의 스코빌 지수를 K이상으로
스코빌 지수가 가장 낮은 두 개의 음식으로 새로운 음식
    새로운 음식 = 가장 낮은 + 두번째로 낮은 *2
    (음식의 개수는 줄어듦)
모든 음식의 스코빌 지수가 K 이상될 때까지 반복
*/
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> scovilles = new PriorityQueue<>();
        for(int sc : scoville){
            scovilles.add(sc);
        }
        
        while(scovilles.peek() < K){
            if(scovilles.size() == 1)
                return -1;
            answer++;
            int newFood = scovilles.poll() + scovilles.poll()*2;
            scovilles.add(newFood);
        }
        
        return answer;
    }
}